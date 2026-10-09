package Machine.Filler;

/**
 * Gravity
 */
public class GravityFiller extends Filler {

    public GravityFiller(
        String name,
        float surfaceArea,
        float price,
        float outputPerSecond
    ) {
        super(name, surfaceArea, price, outputPerSecond, LiquidProperty.FLAT);
    }
}
