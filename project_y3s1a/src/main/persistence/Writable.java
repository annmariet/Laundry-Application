package persistence;

import org.json.JSONObject;

// from Json Serialization Demo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
