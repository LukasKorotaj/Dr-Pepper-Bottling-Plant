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
}
