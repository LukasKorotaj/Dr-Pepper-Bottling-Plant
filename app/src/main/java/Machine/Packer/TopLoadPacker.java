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

    @Override
    public String category() {
        return "Packer";
    }

    @Override
    public String describe() {
        return "Top-load packer (easy size switching)";
    }
}
