package persistence;

import model.Laundry;
import model.Machine;

import java.io.IOException;
import java.net.Proxy;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// from Json Serialization Demo
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads laundryRoom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Laundry read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLaundry(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses laundry from JSON object and returns it
    private Laundry parseLaundry(JSONObject jsonObject) {
        Laundry l = new Laundry();
        addMachines(l, jsonObject);
        return l;
    }

    // MODIFIES: l
    // EFFECTS: parses machines from JSON object and adds them to laundry
    private void addMachines(Laundry l, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("machines");
        for (Object json : jsonArray) {
            JSONObject nextMachine = (JSONObject) json;
            addMachine(l, nextMachine);
        }
    }

    // MODIFIES: l
    // EFFECTS: parses machine from JSON object and adds it to laundry
    private void addMachine(Laundry l, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String type = jsonObject.getString("type");
        Boolean inUse = jsonObject.getBoolean("inUse");
        Boolean broken = jsonObject.getBoolean("broken");
        int uses = jsonObject.getInt("uses");
        int x = jsonObject.getInt("x");
        int y = jsonObject.getInt("y");
        Machine machine = new Machine(type, name, uses, broken, inUse, x, y);
        l.addMachine(machine);
    }
}
