package persistence;

import model.Laundry;
import model.Machine;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// from Json Serialization Demo
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Laundry l = new Laundry();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException expected");
        } catch (IOException e) {
            // exception expected - pass
        }
    }

    @Test
    void testWriterEmptyLaundry() {
        try {
            Laundry l = new Laundry();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLaundry.json");
            writer.open();
            writer.write(l);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLaundry.json");
            l = reader.read();
            assertEquals(0, l.getWashers().size());
            assertEquals(0,l.getDryers().size());
        } catch (IOException e) {
            fail("No exception expected");
        }
    }

    @Test
    void testWriterGeneralLaundry() {
        try {
            Laundry l = new Laundry();
            l.addMachine(new Machine("washer", "washer1", 2, false, true, 0, 0));
            l.addMachine(new Machine("dryer", "dryer1", 1, true, false, 0, 0));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLaundry.json");
            writer.open();
            writer.write(l);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLaundry.json");
            l = reader.read();
            List<String> dryers = l.getDryers();
            List<String> washers = l.getWashers();
            assertEquals(1, dryers.size());
            assertEquals(1, washers.size());
            checkMachine("washer1", "washer", true, false, 2, l.getWasher(washers.get(0)));
            checkMachine("dryer1", "dryer", false, true, 1, l.getDryer(dryers.get(0)));
        } catch (IOException e) {
            fail("No exception expected");
        }
    }
}
