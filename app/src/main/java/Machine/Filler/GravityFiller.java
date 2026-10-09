package Machine.Filler;

/**
 * Gravity
 */
public class GravityFiller extends Filler {

    public GravityFiller(
        String name,
        float surfaceArea,
        float price,
        float outputPerSecond,
        int supportedVolume,
        float fillSpeed
    ) {
        super(
            name,
            surfaceArea,
            price,
            outputPerSecond,
            LiquidProperty.FLAT,
            supportedVolume,
            fillSpeed
        );
    }
}
