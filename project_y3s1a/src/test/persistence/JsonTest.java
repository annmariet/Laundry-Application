package persistence;

import model.Machine;

import static org.junit.jupiter.api.Assertions.*;

// from Json Serialization Demo
public class JsonTest {
    protected void checkMachine(String name, String type, boolean inUse, boolean broken, int uses, Machine machine) {
        assertEquals(name, machine.getName());
        assertEquals(type, machine.getType());
        assertEquals(inUse, machine.getStatus());
        assertEquals(broken, machine.getIfBroken());
        assertEquals(uses, machine.getUses());
    }
}
