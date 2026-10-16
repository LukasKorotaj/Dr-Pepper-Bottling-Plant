package Machine.Intake;

/**
 * Blower
 */
public class Blower extends Intake {

    public Blower(
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
            MaterialType.PET,
            supportedVolume,
            fillSpeed
        );
    }

    @Override
    public String category() {
        return "Intake";
    }

    @Override
    public String describe() {
        return String.format(
            "Blower moulding PET preforms up to %d ml", getSupportedVolume());
    }
}
