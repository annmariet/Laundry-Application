package ui;

import model.Laundry;
import model.Machine;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Laundry application
public class LaundryApp extends JFrame {
    private static final String JSON_STORE = "./data/laundry.json";
    private Scanner input;
    private Laundry laundryRoom;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;


    // from Json Serialization Demo
    // EFFECTS: runs laundry app
    public LaundryApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runLaundry();
    }

    // code from TellerApp
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runLaundry() {
        boolean keepGoing = true;
        String command = null;
        initialize();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");

    }

    // EFFECTS: displays options for user to select from
    private void displayMenu() {
        System.out.println("Select from");
        System.out.println("Add Machine - a");
        System.out.println("Remove Machine - r");
        System.out.println("Use Washer - w");
        System.out.println("Use Dryer - d");
        System.out.println("Empty Washer - dw");
        System.out.println("Empty Dryer - dd");
        System.out.println("Broken Washer - bw");
        System.out.println("Broken Dryer - bd");
        System.out.println("Get Washer Uses - wu");
        System.out.println("Get Dryer Uses - du");
        System.out.println("See Machines - s");
        System.out.println("Load Laundry From File - l");
        System.out.println("Save Laundry To File - sl");
    }

    // MODIFIES: this
    // EFFECTS: constructs a laundry room and scanner
    private void initialize() {
        laundryRoom = new Laundry();
        input = new Scanner(System.in);
    }

    // MODIFIES: this
    // EFFECTS: process user command
    @SuppressWarnings("methodlength")
    private void processCommand(String command) {
        if (command.equals("a")) {
            addTheMachine();
        } else if (command.equals("r")) {
            removeTheMachine();
        } else if (command.equals("w")) {
            useWasher();
        } else if (command.equals("d")) {
            useDryer();
        } else if (command.equals("dw")) {
            emptyWasher();
        } else if (command.equals("dd")) {
            emptyDryer();
        } else if (command.equals("bw")) {
            brokenWasher();
        } else if (command.equals("bd")) {
            brokenDryer();
        } else if (command.equals("wu")) {
            usesWasher();
        } else if (command.equals("du")) {
            usesDryer();
        } else if (command.equals("s")) {
            seeMachine();
        } else if (command.equals("l")) {
            loadLaundry();
        } else if (command.equals("sl")) {
            saveLaundry();
        } else {
            System.out.println("Not an option!");
        }
    }

    // MODIFIES: this, m
    // EFFECTS: adds a machine to the user's laundry room
    public void addTheMachine() {
        System.out.println("What type of machine? Washer or dryer");
        String type = input.next();
        System.out.println("What would you like to name your machine?");
        String name = input.next();
        Machine m = new Machine(type, name, 0, false, false, 0, 0);
        laundryRoom.addMachine(m);
        System.out.println("Machine " + name + " was added!");
    }

    // MODIFIES: this
    // EFFECTS: removes a machine from the user's laundry room
    public void removeTheMachine() {
        System.out.println("What machine would you like to remove? Washer or dryer");
        String type = input.next();
        if (type.equalsIgnoreCase("washer")) {
            System.out.println("What washer would you like to remove " + laundryRoom.getWashers() + "?");
            String name = input.next();
            Machine m = laundryRoom.getWasher(name);
            laundryRoom.removeWasher(m);
            System.out.println("The washer has been removed");
        } else if (type.equalsIgnoreCase("dryer")) {
            System.out.println("What dryer would you like to remove " + laundryRoom.getDryers() + "?");
            String name = input.next();
            Machine m = laundryRoom.getDryer(name);
            laundryRoom.removeDryer(m);
        } else {
            System.out.println("Not an option!");
        }
    }

    // MODIFIES: this
    // EFFECTS: toggles selected washer to being used
    public void useWasher() {
        System.out.println("What washer would you like to use " + laundryRoom.getWashers() + "?");
        String name = input.next();
        Machine m = laundryRoom.getWasher(name);
        if (m.getStatus()) {
            System.out.println("Washer " + name + " is already being used!");
        } else {
            m.setUseStatus();
            System.out.println("Washer " + name + " is in use!");
        }
    }

    // MODIFIES: this
    // EFFECTS: toggles selected dryer to being used
    public void useDryer() {
        System.out.println("What dryer would you like to use " + laundryRoom.getDryers() + "?");
        String name = input.next();
        Machine m = laundryRoom.getDryer(name);
        if (m.getStatus()) {
            System.out.println("Dryer " + name + " is already being used!");
        } else {
            m.setUseStatus();
            System.out.println("Dryer " + name + " is in use!");
        }
    }

    // MODIFIES: this
    // EFFECTS: toggles selected washer to empty
    public void emptyWasher() {
        System.out.println("What washer would you like to empty " + laundryRoom.getWashers() + "?");
        String name = input.next();
        Machine m = laundryRoom.getWasher(name);
        if (!m.getStatus()) {
            System.out.println("Washer " + name + " is already empty!");
        } else {
            m.setUseStatus();
            System.out.println("Washer " + name + " is now empty!");
        }
    }

    // MODIFIES: this
    // EFFECTS: toggles selected dryer to empty
    public void emptyDryer() {
        System.out.println("What dryer would you like to empty " + laundryRoom.getDryers() + "?");
        String name = input.next();
        Machine m = laundryRoom.getDryer(name);
        if (!m.getStatus()) {
            System.out.println("Dryer " + name + " is already empty!");
        } else {
            m.setUseStatus();
            System.out.println("Dryer " + name + " is now empty");
        }
    }

    // MODIFIES: this
    // EFFECTS: toggles selected washer to broken
    public void brokenWasher() {
        System.out.println("What washer has broken " + laundryRoom.getWashers() + "?");
        String name = input.next();
        Machine m = laundryRoom.getWasher(name);
        if (m.getIfBroken()) {
            System.out.println("Washer " + name + " is already broken!");
        } else {
            m.setBrokenStatus();
            System.out.println("Washer " + name + " is broken!");
        }
    }

    // MODIFIES: this
    // EFFECTS: toggles selected dryer to broken
    public void brokenDryer() {
        System.out.println("What dryer has broken " + laundryRoom.getDryers() + "?");
        String name = input.next();
        Machine m = laundryRoom.getDryer(name);
        if (m.getIfBroken()) {
            System.out.println("Dryer " + name + " is already broken!");
        } else {
            m.setBrokenStatus();
            System.out.println("Dryer " + name + " is broken!");
        }
    }

    // MODIFIES: this
    // EFFECTS: returns statistics about user selected washer
    public void usesWasher() {
        System.out.println("What washer would you like to check the uses for " + laundryRoom.getWashers() + "?");
        String name = input.next();
        Machine m = laundryRoom.getWasher(name);
        String s = laundryRoom.frequencyWash(m);
        System.out.println(s);
    }

    // MODIFIES: this
    // EFFECTS: returns statistics about user selected dryer
    public void usesDryer() {
        System.out.println("What dryer would you like to check the uses for " + laundryRoom.getDryers() + "?");
        String name = input.next();
        Machine m = laundryRoom.getDryer(name);
        String s = laundryRoom.frequencyDry(m);
        System.out.println(s);
    }

    // EFFECTS: shows user added machines
    public void seeMachine() {
        System.out.println("Your washers are " + laundryRoom.getWashers());
        System.out.println("Your dryers are " + laundryRoom.getDryers());
    }

    // from Json Serialization Demo
    // MODIFIES: this
    // EFFECTS: loads laundry from file
    public void loadLaundry() {
        try {
            laundryRoom = jsonReader.read();
            System.out.println("Loaded your laundry room from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // from Json Serialization Demo
    // EFFECTS: saves the laundry to file
    private void saveLaundry() {
        try {
            jsonWriter.open();
            jsonWriter.write(laundryRoom);
            jsonWriter.close();
            System.out.println("Saved your laundry room to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
}
