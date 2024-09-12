package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class MachineTest {
    private Machine testWasher;

    @BeforeEach
    void runBefore() {
        testWasher = new Machine("dryer", "test dryer", 0, false, false, 0, 0);
    }

    @Test
    void testConstructor() {
        assertEquals(testWasher.getType(), "dryer");
        assertEquals(testWasher.getUses(), 0);
        assertFalse(testWasher.getIfBroken());
        assertFalse(testWasher.getStatus());
    }

    @Test
    void testSetBrokenStatus() {
        testWasher.setBrokenStatus();
        assertTrue(testWasher.getIfBroken());
    }

    @Test
    void testSetBrokenStatusMultiple() {
        testWasher.setBrokenStatus();
        testWasher.setBrokenStatus();
        assertFalse(testWasher.getIfBroken());
    }

    @Test
    void testSetUseStatusOnce() {
        testWasher.setUseStatus();
        assertTrue(testWasher.getStatus());
        assertEquals(1, testWasher.getUses());
    }

    @Test
    void testSetUseStatusMultiple() {
        testWasher.setUseStatus();
        testWasher.setUseStatus();
        assertFalse(testWasher.getStatus());
        assertEquals(1, testWasher.getUses());
    }

    @Test
    void testUses() {
        testWasher.setUseStatus();
        testWasher.setUseStatus();
        testWasher.setUseStatus();
        testWasher.setUseStatus();
        testWasher.setUseStatus();
        assertTrue(testWasher.getStatus());
        assertEquals(3, testWasher.getUses());
    }

    @Test
    void testContains() {
        Point p = new Point(0,0);
        Point p2 = new Point(100, 138);
        assertTrue(testWasher.contains(p));
        assertTrue(testWasher.contains(p2));
        assertTrue(testWasher.containsY(0));
        assertTrue(testWasher.containsY(138));
        assertTrue(testWasher.containsX(0));
        assertTrue(testWasher.containsX(100));
        assertEquals(0, testWasher.getPointx());
        assertEquals(0, testWasher.getPointy());
    }


}
