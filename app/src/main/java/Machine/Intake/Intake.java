package Machine.Intake;

import Machine.Machine;
import Machine.VolumeHandling;

/**
 * Intake
 */
public class Intake extends Machine implements VolumeHandling {

    MaterialType materialType;

    /**
     * Available materials for the intake.
     *
     * The difference between Plastic and PET is
     * that Plastic is pre-blown while PET needs
     * a machine to bring it to volume.
     * MaterialType
     */
    protected enum MaterialType {
        CAN,
        PLASTIC,
        PET,
        GLASS,
    }

    /**
     *
     * @param name
     * @param surfaceArea
     * @param price
     * @param outputPerSecond
     * @param materialType can be one of CAN, PLASTIC, PET, GLASS
     */
    protected Intake(
        String name,
        float surfaceArea,
        float price,
        float outputPerSecond,
        MaterialType materialType
    ) {
        super(name, surfaceArea, price, outputPerSecond);
        this.materialType = materialType;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }
}
