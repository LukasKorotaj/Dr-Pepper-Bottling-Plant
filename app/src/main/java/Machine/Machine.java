package Machine;

import Interface.Input;
import Interface.MachineError;
import Interface.MttrMtbf;
import Interface.Output;
import Interface.Repairable;

/**
 * Machine
 */
public class Machine implements Input, MttrMtbf, MachineError, Output, Repairable
{

    @Override
    public int maxInput() {
        return 0;
    }

    @Override
    public float mttr() {
        return 0;
    }

    @Override
    public float mtbf() {
        return 0;
    }
}
