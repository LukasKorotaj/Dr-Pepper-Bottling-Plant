package Machine.Intake;

/**
 * Blower
 */
public class Blower extends Intake {

    protected Blower(
        String name,
        float surfaceArea,
        float price,
        float outputPerSecond
    ) {
        super(name, surfaceArea, price, outputPerSecond, MaterialType.PET);
    }
}
