package Logic.CLI;

import Logic.Logic;

import java.awt.Menu;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Handling user input and styling.
 */
public class CLI {

    private final Logic logic;
    private final Scanner in = new Scanner(System.in);

    // Record for describing menu entries.
    public record MenuEntry(String name, String description, Runnable run) {
        // Convenience method.
        // Wanted to name it "run()"
        // but the compiler complains
        public void execute() {
            run.run();
        }

        public String getName() {
            return this.name;
        }

        public String getDescription() {
            return this.description;
        }
    }

    /**
    *
    * @return Map of menu entries.
    * The order DOES matter.
    */
    private Map<Integer, MenuEntry> menu() {
        Map<Integer, MenuEntry> map = new LinkedHashMap<>();

        List<MenuEntry> menuEntries = List.of(
            new MenuEntry("Test", "Test1", null),
            new MenuEntry("Test", "Test2", null),
            new MenuEntry("Test", "Test3", null)
        );

        for (int i = 0; i < menuEntries.size(); i++) {
            map.put(i + 1, menuEntries.get(i));
        }

        return map;
    }

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

    public CLI(Logic logic) {
        this.logic = logic;
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
    }
}
