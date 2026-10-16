import Logic.CLI.CLI;
import Logic.CLI.MenuEntry;

import java.util.List;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        List<MenuEntry> menuEntries = List.of(
            new MenuEntry("PH", "placeholder", null),
            new MenuEntry("PH", "placeholder2", null)
        );

        CLI cli = new CLI(menuEntries);

        cli.init();
    }
}
