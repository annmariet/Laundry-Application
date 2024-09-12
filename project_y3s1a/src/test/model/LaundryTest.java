package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LaundryTest {
    Laundry laundryRoom;
    Machine washer1;
    Machine washer2;
    Machine dryer1;
    Machine dryer2;

    @BeforeEach
    void runBefore() {
        laundryRoom = new Laundry();
        washer1 = new Machine("washer", "Washer 1", 0, false, false, 0, 0);
        washer2 = new Machine("WaSher", "washer 2", 0, false, false, 0, 0);
        dryer1 = new Machine("dryER", "dRyEr 1", 0, false, false, 0 ,0);
        dryer2 = new Machine("dryer", "dryer 2", 0, false, false, 0, 0);
    }

    @Test
    void testConstructor() {
        List<String> dryers = laundryRoom.getDryers();
        List<String> washers = laundryRoom.getWashers();
        assertEquals(0,dryers.size());
        assertEquals(0, washers.size());
    }

    @Test
    void testAddMachineWasher() {
        laundryRoom.addMachine(washer1);
        List<String> dryers = laundryRoom.getDryers();
        List<String> washers = laundryRoom.getWashers();
        assertEquals(0, dryers.size());
        assertEquals(1, washers.size());
        assertEquals("Washer 1", washers.get(0));
    }

    @Test
    void testAddMachineDryer() {
        laundryRoom.addMachine(dryer1);
        List<String> dryers = laundryRoom.getDryers();
        List<String> washers = laundryRoom.getWashers();
        assertEquals(1, dryers.size());
        assertEquals(0, washers.size());
        assertEquals("dRyEr 1", dryers.get(0));
    }

    @Test
    void testAddMachineMultiple() {
        laundryRoom.addMachine(dryer1);
        laundryRoom.addMachine(washer1);
        laundryRoom.addMachine(washer2);
        List<String> dryers = laundryRoom.getDryers();
        List<String> washers = laundryRoom.getWashers();
        assertEquals(1, dryers.size());
        assertEquals(2, washers.size());
        assertEquals("dRyEr 1", dryers.get(0));
        assertEquals("Washer 1", washers.get(0));
        assertEquals("washer 2", washers.get(1));
    }

    @Test
    void testRemoveMachineWasher() {
        laundryRoom.addMachine(dryer1);
        laundryRoom.addMachine(washer1);
        laundryRoom.removeWasher(washer1);
        List<String> dryers = laundryRoom.getDryers();
        List<String> washers = laundryRoom.getWashers();
        assertEquals(0, washers.size());
        assertEquals(1, dryers.size());
    }

    @Test
    void testRemoveMachineDryer() {
        laundryRoom.addMachine(dryer1);
        laundryRoom.addMachine(washer1);
        laundryRoom.removeDryer(dryer1);
        List<String> dryers = laundryRoom.getDryers();
        List<String> washers = laundryRoom.getWashers();
        assertEquals(1, washers.size());
        assertEquals(0, dryers.size());
    }

    @Test
    void testRemoveMachineFirstRemoved() {
        laundryRoom.addMachine(dryer1);
        laundryRoom.addMachine(washer1);
        laundryRoom.addMachine(washer2);
        laundryRoom.removeWasher(washer1);
        List<String> dryers = laundryRoom.getDryers();
        List<String> washers = laundryRoom.getWashers();
        assertEquals(1, washers.size());
        assertEquals("washer 2", washers.get(0));
        assertEquals(1, dryers.size());
    }

    @Test
    void testRemoveMachineSecondRemoved() {
        laundryRoom.addMachine(dryer1);
        laundryRoom.addMachine(washer1);
        laundryRoom.addMachine(washer2);
        laundryRoom.removeWasher(washer2);
        List<String> dryers = laundryRoom.getDryers();
        List<String> washers = laundryRoom.getWashers();
        assertEquals(1, washers.size());
        assertEquals("Washer 1", washers.get(0));
        assertEquals(1, dryers.size());
    }

    @Test
    void testRemoveMachineMultiple() {
        laundryRoom.addMachine(dryer1);
        laundryRoom.addMachine(washer1);
        laundryRoom.addMachine(washer2);
        laundryRoom.removeWasher(washer2);
        laundryRoom.removeDryer(dryer1);
        List<String> dryers = laundryRoom.getDryers();
        List<String> washers = laundryRoom.getWashers();
        assertEquals(1, washers.size());
        assertEquals("Washer 1", washers.get(0));
        assertEquals(0, dryers.size());
    }

    @Test
    void testFrequencyWashNone() {
        laundryRoom.addMachine(washer1);
        assertEquals(
                "Washer 1 has been used 0 times out of the 0 times a washer has been used in this laundry room",
                laundryRoom.frequencyWash(washer1));
    }


    @Test
    void testFrequencyWashLots() {
        laundryRoom.addMachine(washer1);
        laundryRoom.addMachine(washer2);
        washer1.setUseStatus();
        washer2.setUseStatus();
        assertEquals(
                "Washer 1 has been used 1 times out of the 2 times a washer has been used in this laundry room",
                laundryRoom.frequencyWash(washer1));
    }

    @Test
    void testFrequencyDryNone() {
        laundryRoom.addMachine(dryer1);
        assertEquals(
                "dRyEr 1 has been used 0 times out of the 0 times a dryer has been used in this laundry room",
                laundryRoom.frequencyDry(dryer1));
    }

    @Test
    void testFrequencyDryOne() {
        laundryRoom.addMachine(dryer1);
        dryer1.setUseStatus();
        assertEquals(
                "dRyEr 1 has been used 1 times out of the 1 times a dryer has been used in this laundry room",
                laundryRoom.frequencyDry(dryer1));
    }

    @Test
    void testFrequencyDryLots() {
        laundryRoom.addMachine(dryer1);
        laundryRoom.addMachine(dryer2);
        dryer1.setUseStatus();
        dryer2.setUseStatus();
        assertEquals(
                "dRyEr 1 has been used 1 times out of the 2 times a dryer has been used in this laundry room",
                laundryRoom.frequencyDry(dryer1));
    }

    @Test
    void testGetWasher() {
        laundryRoom.addMachine(washer1);
        assertEquals(washer1, laundryRoom.getWasher("Washer 1"));
    }

    @Test
    void testGetDryer() {
        laundryRoom.addMachine(dryer1);
        assertEquals(dryer1, laundryRoom.getDryer("dRyEr 1"));
    }

    @Test
    void testAddDryer() {
        laundryRoom.addMachine(dryer2);
        List<String> dryers = laundryRoom.getDryers();
        assertEquals(1, dryers.size());
        assertEquals("dryer 2", dryers.get(0));
    }

    @Test
    void testGetMachine() {
        laundryRoom.addMachine(washer1);
        laundryRoom.addMachine(dryer1);
        List<Machine> washers = laundryRoom.getWasherMachine();
        List<Machine> dryers = laundryRoom.getDryerMachine();
        assertEquals(1, washers.size());
        assertEquals(1, dryers.size());
        assertEquals(washer1, washers.get(0));
        assertEquals(dryer1, dryers.get(0));
    }
}

