package Logic.CLI;

import java.io.IOException;
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

    /**
     *
     * @param padding the total lenght of the string, including
     * the name.
     * @return the string as represented in the menu
     */
    public String toFormattedString(int padding) {
        String formattedName = name;
        int nameLen = name.length();

        int addEmpty = padding - nameLen;
        if (addEmpty < 1) {
            throw new IllegalArgumentException("Padding to small for the name");
        }

        for (int i = 0; i < addEmpty; i++) {
            formattedName += " ";
        }

        return formattedName + description;
    }
}
