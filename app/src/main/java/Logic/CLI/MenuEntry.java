package Logic.CLI;

/**
 * One entry of a menu that the CLI is able to render and run.
 *
 * @param name label printed on the menu line.
 * @param description longer text printed after the label.
 * @param run the action performed when the entry is chosen.
 */
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
