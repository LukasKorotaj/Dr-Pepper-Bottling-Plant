package Machine.Filler;

/**
 * Pressure
 */
public class PressureFiller extends Filler {

    public PressureFiller(
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
            LiquidProperty.CARBONATED,
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
            "Pressure filler for carbonated drinks - %d ml at %.1f ml/s",
            getSupportedVolume(), getFillSpeed());
    }
}
