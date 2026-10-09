package Logic.CLI;

import java.util.List;

/**
 * Describes a menu entry
 */
public abstract class MenuEntry {

    private String name;
    private String description;

    protected MenuEntry(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
