package Machine.Packer;

import Machine.Machine;

/**
 * Packer
 */
public abstract class Packer extends Machine {

    private boolean easySwitch;

    protected Packer(
        String name,
        float surfaceArea,
        float price,
        float outputPerSecond,
        boolean easySwitch
    ) {
        super(name, surfaceArea, price, outputPerSecond);
        this.easySwitch = easySwitch;
    }

    /**
    * Easy Switch describes if the packer machine is capable
    * of easily switching between different size packages.
    */
    public boolean isEasySwitch() {
        return easySwitch;
    }
}
