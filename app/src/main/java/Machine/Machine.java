package Machine;

/**
 * Machine
 */
public abstract class Machine {

    private String name;
    private float surfaceArea;
    private float price;
    private float outputPerSecond;

    protected Machine(String name, float surfaceArea, float price, float outputPerSecond) {
        this.name = name;
        this.surfaceArea = surfaceArea;
        this.price = price;
        this.outputPerSecond = outputPerSecond;
    }

    public String getName() {
        return name;
    }

    public float getSurfaceArea() {
        return surfaceArea;
    }

    public float getPrice() {
        return price;
    }

    public float getOutputPerSecond() {
        return outputPerSecond;
    }

    /**
     * The production stage this machine belongs to. Every concrete machine
     * supplies its own value, so iterating a mixed collection and calling
     * this runs the right implementation for each object.
     */
    public abstract String category();

    /**
     * One-line, human-readable description specific to the machine type.
     */
    public abstract String describe();
}
