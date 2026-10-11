import Logic.Logic;
import Logic.CLI.CLI;
import Logic.CLI.MenuEntry;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Main
 */
public class Main {

    /**
     *
     * @return Map of menu entries.
     * The order DOES matter.
     */

    private static void motivationalMessage() {
        System.out.println("""
        “If there is no struggle, there is no progress.”
        — Frederick Douglass
        """);
    }

    private static Map<Integer, MenuEntry> menu() {
        Map<Integer, MenuEntry> map = new LinkedHashMap<>();

        List<MenuEntry> menuEntries = List.of(
            new MenuEntry("Motivate", "Gives you a motivational message", Main::motivationalMessage),
            new MenuEntry("Test", "Test2", null),
            new MenuEntry("Test", "Test3", null)
        );

        for (int i = 0; i < menuEntries.size(); i++) {
            map.put(i + 1, menuEntries.get(i));
        }

        return map;
    }

    public static void main(String[] args) {
        CLI cli = new CLI(menu());

        cli.init();
    }
}
