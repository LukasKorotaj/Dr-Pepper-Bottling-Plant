package Machine.Filler;

import Machine.Machine;
import Machine.VolumeHandling;

/**
 * Filler
 */
public abstract class Filler extends Machine implements VolumeHandling {

    private LiquidProperty liquidProperty;

    /**
     *
     * LiquidProperty describes if the liquid to be
     * put into a container is carbonated or flat.
     */
    protected enum LiquidProperty {
        CARBONATED,
        FLAT,
    }

    // VolumeHandling
    int supportedVolume;
    float fillSpeed;

    protected Filler(
        String name,
        float surfaceArea,
        float price,
        float outputPerSecond,
        LiquidProperty liquidProperty,
        int supportedVolume,
        float fillSpeed
    ) {
        super(name, surfaceArea, price, outputPerSecond);
        this.liquidProperty = liquidProperty;
        this.supportedVolume = supportedVolume;
        this.fillSpeed = fillSpeed;
    }

    public LiquidProperty getLiquidProperty() {
        return liquidProperty;
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
