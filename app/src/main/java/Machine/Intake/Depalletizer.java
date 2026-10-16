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

    @Override
    public String category() {
        return "Intake";
    }

    @Override
    public String describe() {
        return String.format(
            "Depalletizer unloading ready-made %s containers", getMaterialType());
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
