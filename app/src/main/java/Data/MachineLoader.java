package Data;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

import Machine.Filler.GravityFiller;
import Machine.Filler.PressureFiller;
import Machine.Intake.Blower;
import Machine.Intake.Depalletizer;
import Machine.Intake.Intake.MaterialType;
import Machine.Machine;
import Machine.Packer.TopLoadPacker;
import Machine.Packer.WrapAroundPacker;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

/**
 * MachineLoader
 *
 * Reads a machine catalogue from json into Machine instances.
 *
 * The machine type is the object key one level above each entry, the
 * category keys above that are only there to keep the file readable:
 *
 * { "filler": { "gravity": [ { "name": ... }, ... ] } }
 */
public class MachineLoader {

    private static final JsonMapper MAPPER = JsonMapper.builder().build();

    public static ArrayList<Machine> load(Path file) {
        ArrayList<Machine> machines = new ArrayList<>();
        for (JsonNode category : MAPPER.readTree(file)) {
            for (Map.Entry<String, JsonNode> type : category.properties()) {
                for (JsonNode entry : type.getValue()) {
                    machines.add(build(type.getKey(), entry));
                }
            }
        }
        return machines;
    }

    private static Machine build(String type, JsonNode entry) {
        String name = entry.get("name").asString();
        float surfaceArea = entry.get("surfacearea").asFloat();
        float price = entry.get("price").asFloat();
        float outputPerSecond = entry.get("outputpersecond").asFloat();

        return switch (type) {
            case "gravity" -> new GravityFiller(
                name, surfaceArea, price, outputPerSecond,
                volume(entry), fillSpeed(entry)
            );
            case "pressure" -> new PressureFiller(
                name, surfaceArea, price, outputPerSecond,
                volume(entry), fillSpeed(entry)
            );
            case "depalletizer" -> new Depalletizer(
                name, surfaceArea, price, outputPerSecond,
                MaterialType.valueOf(entry.get("materialtype").asString()),
                volume(entry), fillSpeed(entry)
            );
            case "blower" -> new Blower(
                name, surfaceArea, price, outputPerSecond,
                volume(entry), fillSpeed(entry)
            );
            case "topload" -> new TopLoadPacker(
                name, surfaceArea, price, outputPerSecond
            );
            case "wraparound" -> new WrapAroundPacker(
                name, surfaceArea, price, outputPerSecond
            );
            default -> throw new IllegalArgumentException(
                "Unknown machine type in json: " + type
            );
        };
    }

    private static int volume(JsonNode entry) {
        return entry.get("supportedvolume").asInt();
    }

    /**
     * Only fillers and blowers fill, so an entry without a fill speed
     * feeds nothing and gets the zero value its volume is irrelevant for.
     */
    private static float fillSpeed(JsonNode entry) {
        JsonNode fillSpeed = entry.get("fillspeed");
        return fillSpeed == null ? 0.0f : fillSpeed.asFloat();
    }
}
