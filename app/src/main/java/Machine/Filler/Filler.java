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

    protected Filler(
        String name,
        float surfaceArea,
        float price,
        float outputPerSecond,
        LiquidProperty liquidProperty
    ) {
        super(name, surfaceArea, price, outputPerSecond);
        this.liquidProperty = liquidProperty;
    }

    public LiquidProperty getLiquidProperty() {
        return liquidProperty;
    }
}
