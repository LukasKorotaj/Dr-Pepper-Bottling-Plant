package Machine.Packer;

/**
 * TopLoad
 */
public class TopLoadPacker extends Packer {

    public TopLoadPacker(
        String name,
        float surfaceArea,
        float price,
        float outputPerSecond
    ) {
        super(name, surfaceArea, price, outputPerSecond, true);
    }
}
