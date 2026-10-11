package Logic.CLI;

import Logic.Logic;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Handling user input and styling.
 */
public class CLI {

    private final Logic logic;
    private final Map<Integer, MenuEntry> menu;
    private final Scanner in = new Scanner(System.in);

    private void drawMenu(Map<Integer, MenuEntry> menu, int padding) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= menu.size(); i++) {
            MenuEntry value = menu.get(i);
            sb.append(i + ". ");

            int toPad = padding - value.getName().length();
            if (toPad < 1) {
                throw new IllegalArgumentException("Padding too Small!");
            }

            sb.append(value.getName() + ".".repeat(toPad));
            sb.append(value.getDescription());
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private void menuChooser(Scanner scanner) {
        System.out.print("Command to run: ");
        String input = null;

        while (input == null || input != "q") {
            String token = null;

            // Try catch for the scanner
            try {
                token = scanner.next();
            } catch (NoSuchElementException e) {
                System.err.println("No input!");
            } catch (IllegalStateException e) {
                System.err.println("Scanner was closed??");
            }

            try {
                Integer.parseInt(token);
            } catch (NumberFormatException e) {
                System.err.println("Input is not a number!");
                //TODO: Add help message
            }

            input = token;

        }
    }

    public CLI(Logic logic, Map<Integer, MenuEntry> menu) {
        this.logic = logic;
        this.menu = menu;
    }

    private String banner = """
     ____        _   _   _      _____  _             _
    |  _ \\      | | | | | |    |  __ \\| |           | |
    | |_) | ___ | |_| |_| | ___| |__) | | __ _ _ __ | |_
    |  _ < / _ \\| __| __| |/ _ \\  ___/| |/ _` | '_ \\| __|
    | |_) | (_) | |_| |_| |  __/ |    | | (_| | | | | |_
    |____/_\\___/ \\__|\\__|_|\\___|_|    |_|\\__,_|_| |_|\\__|
    |  \\/  |                                        /_ |
    | \\  / | __ _ _ __   __ _  __ _  ___ _ __  __   _| |
    | |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '__| \\ \\ / / |
    | |  | | (_| | | | | (_| | (_| |  __/ |     \\ V /| |
    |_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_|      \\_/ |_|
                               __/ |
                              |___/
     """;

    /**
     * Entry point for the CLI
     */
    public void init() {
        System.out.println(banner);
        drawMenu(menu, 20);
    }
}
