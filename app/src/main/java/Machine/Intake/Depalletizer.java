package Machine.Intake;

/**
 * Depalletizer
 */
public class Depalletizer extends Intake {

    public Depalletizer(
        String name,
        float surfaceArea,
        float price,
        float outputPerSecond,
        MaterialType materialType,
        int supportedVolume,
        float fillSpeed
    ) {
        super(
            name,
            surfaceArea,
            price,
            outputPerSecond,
            validateMaterial(materialType),
            supportedVolume,
            fillSpeed
        );
    }

    private static MaterialType validateMaterial(MaterialType materialType) {
        if (materialType == MaterialType.PET) {
            throw new IllegalArgumentException(
                "Should not use depalletizer for PET bottles."
            );
        }
        return materialType;
    }
}
