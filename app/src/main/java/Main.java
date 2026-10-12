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

    private static void importShoppingList() {
        System.out.println("""
        This option will be able to import a shopping list for editing
        or calculating if its optimal.
        """);
    }

    private static void calculate() {
        System.out.println("""
        Calculates the best machine composition
        given the users constraints.
        """);
    }

    private static void newBottlePlant() {
        System.out.println("""
        Creates a shopping list.
        """);
    }

    /**
     *
     * @return Map of menu entries.
     * The order DOES matter.
     */
    private static Map<Integer, MenuEntry> menu() {
        Map<Integer, MenuEntry> map = new LinkedHashMap<>();

        List<MenuEntry> menuEntries = List.of(
            new MenuEntry("Calculate", "Finds the cheapest viable option", Main::calculate),
            new MenuEntry("Import", "Imports an already existing config", Main::importShoppingList),
            new MenuEntry("New", "Begin a new project", Main::newBottlePlant)
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
