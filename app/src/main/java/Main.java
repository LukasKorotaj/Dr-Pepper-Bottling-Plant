import Data.MachineLoader;
import Logic.CLI.CLI;
import Logic.CLI.MenuEntry;
import Logic.Logic;

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

        Logic logic = new Logic(MachineLoader.load(dataFile()), in);

        List<MenuEntry> menuEntries = List.of(
            new MenuEntry(
                "Calculate",
                "Find the ideal setup for your output, area and budget.",
                logic::run
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
