package persistence;

import model.Laundry;
import model.Machine;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// from Json Serialization Demo
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Laundry l = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // exception expected - pass
        }
    }

    @Test
    void testReaderEmptyLaundry() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLaundry.json");
        try {
            Laundry l = reader.read();
            assertEquals(0, l.getDryers().size());
            assertEquals(0, l.getWashers().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralLaundry() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLaundry.json");
        try {
            Laundry l = reader.read();
            List<String> dryers = l.getDryers();
            List<String> washers = l.getWashers();
            assertEquals(2, dryers.size());
            assertEquals(1, washers.size());
            checkMachine("washer1", "washer", true, false, 2, l.getWasher(washers.get(0)));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
