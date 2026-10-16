package Machine.Packer;

/**
 * WrapAround
 */
public class WrapAroundPacker extends Packer {

    public WrapAroundPacker(
        String name,
        float surfaceArea,
        float price,
        float outputPerSecond
    ) {
        super(name, surfaceArea, price, outputPerSecond, false);
    }

    @Override
    public String category() {
        return "Packer";
    }

    @Override
    public String describe() {
        return "Wrap-around packer (fixed size)";
    }
}
