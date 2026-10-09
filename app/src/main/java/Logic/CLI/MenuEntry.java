package Logic.CLI;

import java.util.List;

/**
 * Describes a menu entry
 */
public abstract class MenuEntry {

    private String name;
    private List<String> options;
    private String description;

    protected MenuEntry(String name, String description, List<String> options) {
        this.name = name;
        this.options = options;
        this.description = description;
    }
}
