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

    @Override
    public String category() {
        return "Filler";
    }

    @Override
    public String describe() {
        return String.format(
            "Gravity filler for still liquids - %d ml at %.1f ml/s",
            getSupportedVolume(), getFillSpeed());
    }
}
