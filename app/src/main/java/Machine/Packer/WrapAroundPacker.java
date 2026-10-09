package Machine.Packer;

/**
 * WrapAround
 */
public class WrapAroundPacker extends Packer {

    protected WrapAroundPacker(
        String name,
        float surfaceArea,
        float price,
        float outputPerSecond
    ) {
        super(name, surfaceArea, price, outputPerSecond, false);
    }
}
