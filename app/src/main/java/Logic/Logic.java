package Logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Machine.Filler.Filler;
import Machine.Intake.Intake;
import Machine.Machine;
import Machine.Packer.Packer;
import Machine.VolumeHandling;

/**
 * Logic
 *
 * The calculator. Given a desired output, an available floor area and a
 * budget, it searches the machine catalogue for the cheapest line that
 * reaches that output without breaking the constraints.
 *
 * A line is one machine per stage: an Intake, a Filler and a Packer. The
 * line can only run as fast as its slowest stage, so its output is the
 * minimum of the three. To reach a target higher than a single line can
 * deliver, the same line is repeated in parallel.
 */
public class Logic {

    private final List<Machine> catalogue;
    private final Scanner in;

    public Logic(List<Machine> catalogue, Scanner in) {
        this.catalogue = catalogue;
        this.in = in;
    }

    /**
     * A complete proposal: one machine per stage plus how many copies of
     * that line run in parallel.
     */
    private record Setup(
        Intake intake,
        Filler filler,
        Packer packer,
        int lines
    ) {

        float unitOutput() {
            return Math.min(
                intake.getOutputPerSecond(),
                Math.min(filler.getOutputPerSecond(), packer.getOutputPerSecond())
            );
        }

        float unitPrice() {
            return intake.getPrice() + filler.getPrice() + packer.getPrice();
        }

        float unitArea() {
            return intake.getSurfaceArea() + filler.getSurfaceArea() + packer.getSurfaceArea();
        }

        float totalOutput() {
            return unitOutput() * lines;
        }

        float totalPrice() {
            return unitPrice() * lines;
        }

        float totalArea() {
            return unitArea() * lines;
        }
    }

    /**
     * Interactive entry point wired to the menu: asks for the three
     * constraints, prints the best setup and offers to save it.
     */
    public void run() {
        float desired = prompt(in, "Desired output (bottles/second): ");
        float area = prompt(in, "Available area (m^2): ");
        float budget = prompt(in, "Budget: ");

        Setup best = optimise(desired, area, budget);

        if (best == null) {
            System.out.println("\nNo machine combination fits the given area and budget.\n");
            return;
        }

        String report = toText(best, desired, area, budget);
        System.out.println();
        System.out.println(report);

        System.out.print("Save this setup to list.txt? [y/N]: ");
        String answer = in.nextLine().trim();
        if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
            save(best, desired, area, budget);
        }
    }

    /**
     * Lists every machine in the catalogue, iterating the mixed list
     * through the shared Machine reference so category() and describe()
     * dispatch to each concrete subclass.
     */
    public void listMachines() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%nMachine catalogue (%d)%n%n", catalogue.size()));
        appendTableHeader(sb);
        for (Machine m : catalogue) {
            appendMachine(sb, 1, m);
        }
        System.out.println(sb);
    }

    /**
     * Filters the catalogue by stage and by minimum output and maximum
     * price, using values read from the user rather than any fixed key.
     */
    public void searchMachines() {
        System.out.print("Stage [Intake/Filler/Packer] (blank = all): ");
        String stage = in.nextLine().trim();
        float minOutput = prompt(in, "Minimum output (bottles/s, 0 = any): ");
        float maxPrice = prompt(in, "Maximum price (0 = any): ");

        StringBuilder sb = new StringBuilder();
        int found = 0;
        for (Machine m : catalogue) {
            boolean stageMatches = stage.isEmpty() || m.category().equalsIgnoreCase(stage);
            boolean outputMatches = minOutput <= 0 || m.getOutputPerSecond() >= minOutput;
            boolean priceMatches = maxPrice <= 0 || m.getPrice() <= maxPrice;

            if (stageMatches && outputMatches && priceMatches) {
                if (found == 0) {
                    appendTableHeader(sb);
                }
                appendMachine(sb, 1, m);
                found++;
            }
        }

        System.out.println();
        if (found == 0) {
            System.out.println("No machines match the given filters.\n");
        } else {
            System.out.println(sb);
        }
    }

    /**
     * Aggregates the catalogue: per-stage counts, total value, average
     * output and the cheapest, most expensive and fastest machines.
     */
    public void showStatistics() {
        int intakes = 0;
        int fillers = 0;
        int packers = 0;
        float sumPrice = 0.0f;
        float sumOutput = 0.0f;

        Machine cheapest = null;
        Machine dearest = null;
        Machine fastest = null;

        for (Machine m : catalogue) {
            sumPrice += m.getPrice();
            sumOutput += m.getOutputPerSecond();

            if (cheapest == null || m.getPrice() < cheapest.getPrice()) {
                cheapest = m;
            }
            if (dearest == null || m.getPrice() > dearest.getPrice()) {
                dearest = m;
            }
            if (fastest == null || m.getOutputPerSecond() > fastest.getOutputPerSecond()) {
                fastest = m;
            }

            switch (m.category()) {
                case "Intake" -> intakes++;
                case "Filler" -> fillers++;
                case "Packer" -> packers++;
                default -> { }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%nCatalogue statistics (%d)%n", catalogue.size()));
        sb.append(String.format("  By stage      : Intake %d, Filler %d, Packer %d%n",
            intakes, fillers, packers));
        sb.append(String.format("  Total value   : %,.0f%n", sumPrice));

        if (!catalogue.isEmpty()) {
            sb.append(String.format("  Average output: %.1f bottles/s%n",
                sumOutput / catalogue.size()));
            sb.append(String.format("  Cheapest      : %s (%,.0f)%n",
                cheapest.getName(), cheapest.getPrice()));
            sb.append(String.format("  Most expensive: %s (%,.0f)%n",
                dearest.getName(), dearest.getPrice()));
            sb.append(String.format("  Fastest       : %s (%,.1f bottles/s)%n",
                fastest.getName(), fastest.getOutputPerSecond()));
        }

        System.out.println(sb.append("\n"));
    }

    /**
     * Enumerates every line and the interesting line-counts for it,
     * returning the setup that best satisfies the constraints.
     *
     * @return the best setup, or {@code null} if nothing affordable fits.
     */
    private Setup optimise(float desired, float area, float budget) {
        List<Intake> intakes = new ArrayList<>();
        List<Filler> fillers = new ArrayList<>();
        List<Packer> packers = new ArrayList<>();

        for (Machine m : catalogue) {
            if (m instanceof Intake i) {
                intakes.add(i);
            } else if (m instanceof Filler f) {
                fillers.add(f);
            } else if (m instanceof Packer p) {
                packers.add(p);
            }
        }

        Setup best = null;

        for (Intake intake : intakes) {
            for (Filler filler : fillers) {
                for (Packer packer : packers) {
                    Setup unit = new Setup(intake, filler, packer, 1);
                    float unitPrice = unit.unitPrice();
                    float unitArea = unit.unitArea();
                    float unitOutput = unit.unitOutput();

                    // Cannot afford a single line, or it fills nothing.
                    if (unitPrice > budget || unitArea > area || unitOutput <= 0) {
                        continue;
                    }

                    // Most lines the money and the floor can hold.
                    int maxLines = (int) Math.min(
                        Math.floor(budget / unitPrice),
                        Math.floor(area / unitArea)
                    );

                    // Fewest lines that reach the target (clamped to what fits).
                    int toMeet = (int) Math.ceil(desired / unitOutput);
                    if (toMeet > maxLines) {
                        toMeet = maxLines;
                    }

                    // Only these counts can ever be the winner for this line.
                    for (int n : new int[] { 1, toMeet, maxLines }) {
                        Setup candidate = new Setup(intake, filler, packer, n);
                        if (candidate.totalPrice() <= budget
                                && candidate.totalArea() <= area
                                && (best == null || better(candidate, best, desired))) {
                            best = candidate;
                        }
                    }
                }
            }
        }

        return best;
    }

    /**
     * Orders two valid setups: one that reaches the target beats one that
     * does not; among target-meeting setups the cheaper wins, and among
     * target-missing ones the faster wins. Area breaks the remaining ties.
     */
    private static boolean better(Setup a, Setup b, float desired) {
        boolean aMeets = a.totalOutput() >= desired;
        boolean bMeets = b.totalOutput() >= desired;

        if (aMeets != bMeets) {
            return aMeets;
        }

        if (aMeets) {
            if (a.totalPrice() != b.totalPrice()) {
                return a.totalPrice() < b.totalPrice();
            }
            return a.totalArea() < b.totalArea();
        }

        if (a.totalOutput() != b.totalOutput()) {
            return a.totalOutput() > b.totalOutput();
        }
        return a.totalPrice() < b.totalPrice();
    }

    /**
     * Builds the human-readable shopping list, shared by the console
     * report and the saved file.
     */
    private String toText(Setup s, float desired, float area, float budget) {
        String rule = "=".repeat(56);
        String thin = "-".repeat(56);

        StringBuilder sb = new StringBuilder();
        sb.append(rule).append("\n");
        sb.append("Dr Pepper Bottle Plant - Shopping List\n");
        sb.append(rule).append("\n\n");

        sb.append(String.format("Target output : %,.1f bottles/s%n", desired));
        sb.append(String.format("Available area: %,.1f m^2%n", area));
        sb.append(String.format("Budget        : %,.0f%n%n", budget));

        sb.append("Machines to purchase\n\n");
        appendTableHeader(sb);

        // One mixed list of machines handled through the shared Machine
        // reference: category() and describe() dispatch to each concrete
        // subclass, so the printed stage label and detail line are the
        // version that object actually is.
        List<Machine> stages = List.of(s.intake(), s.filler(), s.packer());
        for (Machine stage : stages) {
            appendMachine(sb, s.lines(), stage);
        }

        // Intake and filler both implement VolumeHandling; handled here
        // through that one interface reference regardless of concrete type.
        sb.append("\n");
        for (Machine stage : stages) {
            sb.append("  ").append(stage.category()).append(": ")
                .append(stage.describe()).append("\n");
        }
        int bottleSize = -1;
        for (VolumeHandling vh : List.<VolumeHandling>of(s.intake(), s.filler())) {
            bottleSize = bottleSize < 0
                ? vh.getSupportedVolume()
                : Math.min(bottleSize, vh.getSupportedVolume());
        }
        sb.append(String.format("%n  Line bottle size: %,d ml%n", bottleSize));

        sb.append("\n").append(thin).append("\n");
        sb.append(String.format("Per line     : %,.1f m^2, %,.0f, %,.1f bottles/s%n",
            s.unitArea(), s.unitPrice(), s.unitOutput()));
        sb.append(String.format("Total        : %,.1f m^2, %,.0f, %,.1f bottles/s%n",
            s.totalArea(), s.totalPrice(), s.totalOutput()));
        sb.append(thin).append("\n");

        if (s.totalOutput() >= desired) {
            sb.append(String.format("%nTarget reached (%,.1f >= %,.1f bottles/s).%n",
                s.totalOutput(), desired));
        } else {
            sb.append(String.format(
                "%nTarget NOT reachable within these constraints.%n"
                + "Best possible: %,.1f bottles/s (short by %,.1f).%n",
                s.totalOutput(), desired - s.totalOutput()));
        }

        return sb.toString();
    }

    private void appendMachine(StringBuilder sb, int qty, Machine m) {
        sb.append(String.format("  %-9s %-20s %,12.0f %7.1f m^2 %6.1f /s%n",
            m.category(), qty + "x " + m.getName(),
            m.getPrice(), m.getSurfaceArea(), m.getOutputPerSecond()));
    }

    /**
     * Column headers shared by the shopping list and the browse/search
     * tables.
     */
    private void appendTableHeader(StringBuilder sb) {
        sb.append(String.format("  %-9s %-20s %12s %8s %8s%n",
            "STAGE", "MACHINE", "PRICE", "AREA", "OUT/S"));
    }

    /**
     * Writes the shopping list to list.txt in the current working
     * directory.
     */
    private void save(Setup s, float desired, float area, float budget) {
        Path out = Path.of("list.txt");
        try {
            Files.writeString(out, toText(s, desired, area, budget));
            System.out.println("Saved shopping list to " + out.toAbsolutePath() + "\n");
        } catch (IOException e) {
            System.out.println("Could not write list.txt: " + e.getMessage() + "\n");
        }
    }

    /**
     * Reads a non-negative number, re-asking until the input is valid.
     */
    private float prompt(Scanner in, String label) {
        while (true) {
            System.out.print(label);
            String line = in.nextLine().trim();
            try {
                float value = Float.parseFloat(line);
                if (value < 0) {
                    System.out.println("Please enter a non-negative number.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Not a number, try again.");
            }
        }
    }

}
