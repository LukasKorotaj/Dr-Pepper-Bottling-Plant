package Machine.Intake;

/**
 * Blower
 */
public class Blower extends Intake {

    protected Blower(
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
}
