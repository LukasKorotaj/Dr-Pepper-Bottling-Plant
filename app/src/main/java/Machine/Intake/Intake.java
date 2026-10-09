package Machine.Intake;

import Machine.Machine;
import Machine.VolumeHandling;

/**
 * Intake
 */
public abstract class Intake extends Machine implements VolumeHandling {

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

    // VolumeHandling
    private int supportedVolume;
    private float fillSpeed;

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
        MaterialType materialType,
        int supportedVolume,
        float fillSpeed
    ) {
        super(name, surfaceArea, price, outputPerSecond);
        this.materialType = materialType;
        this.supportedVolume = supportedVolume;
        this.fillSpeed = fillSpeed;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    @Override
    public int getSupportedVolume() {
        return supportedVolume;
    }

    @Override
    public void setSupportedVolume(int ml) {
        this.supportedVolume = ml;
    }

    @Override
    public float getFillSpeed() {
        return fillSpeed;
    }

    @Override
    public void setFillSpeed(float mlPerSecond) {
        this.fillSpeed = mlPerSecond;
    }
}
