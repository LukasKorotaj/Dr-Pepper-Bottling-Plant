package Machine;

/**
 * Machine
 */
public abstract class Machine {

    private String machineID;

    private float mttr;
    private float mtbf;

    private class MachineError {

        public MachineError(String error) {}
    }
}
