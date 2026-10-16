package Logic.CLI;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Handling user input and stylinglogic, .
 */
public class CLI {

    private final Map<Integer, MenuEntry> menuEntries;
    private final Scanner in = new Scanner(System.in);

    public CLI(List<MenuEntry> menuList) {
        this.menuEntries = menu(menuList);
    }

    private void drawMenu(int padding) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= menuEntries.size(); i++) {
            MenuEntry value = menuEntries.get(i);
            sb.append(i + ". ");

            int toPad = padding - value.getName().length();
            if (toPad < 1) {
                throw new IllegalArgumentException("Padding too Small!");
            }

            sb.append(value.getName() + ".".repeat(toPad));
            sb.append(value.getDescription());
            sb.append("\n");

        }

        sb.append("Type 'q' to quit\n");
        System.out.println(sb);
    }

    private String menuHelp = """
    Type the number of the desired command to choose it.
    Type "q" to quit the program.
    """;

    private void menuChooser() {
        while (true) {
            System.out.print("Command to run: ");

            String line;
            try {
                line = in.nextLine().trim();
            } catch (NoSuchElementException e) {
                System.out.println("No input!");
                return;
            }

            if (line.isEmpty()) {
                continue;
            }

            if (line.equalsIgnoreCase("q")) {
                return;
            }

            int choice;
            try {
                choice = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Input is not a number!");
                System.out.println(menuHelp);
                continue;
            }

            MenuEntry entry = menuEntries.get(choice);

            if (entry == null) {
                System.out.println("No such option: " + choice);
                System.out.println(menuHelp);
                continue;
            }

            entry.execute();
        }
    }

    /**
     *
     * @return Map of menu entries.
     * The order DOES matter.
     */
    private Map<Integer, MenuEntry> menu(List<MenuEntry> menuEntries) {
        Map<Integer, MenuEntry> map = new LinkedHashMap<>();

        for (int i = 0; i < menuEntries.size(); i++) {
            map.put(i + 1, menuEntries.get(i));
        }

        return map;
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
        drawMenu(20);
        menuChooser();
    }
}
