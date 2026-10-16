import Data.MachineLoader;
import Logic.CLI.CLI;
import Logic.CLI.MenuEntry;
import Logic.Logic;
import Machine.Machine;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        // One scanner shared by the menu and the calculator so they never
        // fight over the buffered standard input.
        Scanner in = new Scanner(System.in);

        List<Machine> catalogue;
        try {
            catalogue = MachineLoader.load(dataFile());
        } catch (IllegalStateException e) {
            System.out.println("Could not find the machine catalogue: " + e.getMessage());
            return;
        } catch (IllegalArgumentException e) {
            System.out.println("The machine catalogue contains an invalid entry: "
                + e.getMessage());
            return;
        }

        Logic logic = new Logic(catalogue, in);

        List<MenuEntry> menuEntries = List.of(
            new MenuEntry(
                "Calculate",
                "Find the ideal setup for your output, area and budget.",
                logic::run
            ),
            new MenuEntry(
                "Browse",
                "List every machine in the catalogue.",
                logic::listMachines
            ),
            new MenuEntry(
                "Search",
                "Filter machines by stage, minimum output and maximum price.",
                logic::searchMachines
            ),
            new MenuEntry(
                "Statistics",
                "Show totals, per-stage counts and extremes over the catalogue.",
                logic::showStatistics
            )
        );

        CLI cli = new CLI(menuEntries, in);

        cli.init();
    }

    /**
     * Locates the catalogue relative to the project root, tolerating the
     * app/ working directory that Gradle runs the application from.
     */
    private static Path dataFile() {
        for (String candidate : new String[] { "data/default.json", "../data/default.json" }) {
            Path path = Path.of(candidate);
            if (Files.isReadable(path)) {
                return path;
            }
        }
        throw new IllegalStateException("data/default.json not found.");
    }
}
