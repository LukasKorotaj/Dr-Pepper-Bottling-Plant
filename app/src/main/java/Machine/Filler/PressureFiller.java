package Machine.Filler;

/**
 * Pressure
 */
public class PressureFiller extends Filler {

    protected PressureFiller(
        String name,
        float surfaceArea,
        float price,
        float outputPerSecond
    ) {
        super(name, surfaceArea, price, outputPerSecond, LiquidProperty.CARBONATED);
    }
}
