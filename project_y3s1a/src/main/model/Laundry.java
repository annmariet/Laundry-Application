package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// represents a laundry room that has washers and dryers
public class Laundry implements Writable {

    private final List<Machine> washers;
    private final List<Machine> dryers;

    // EFFECTS: constructs a Laundry object with two empty lists of washers and dryers
    public Laundry() {
        washers = new ArrayList<>();
        dryers = new ArrayList<>();
    }

    // REQUIRES: machine must be of type washer or dryer
    // MODIFIES: this
    // EFFECTS: sorts added machine into washer list or dryer list
    public void addMachine(Machine machine) {
        if (machine.getType().equalsIgnoreCase("washer")) {
            washers.add(machine);
            EventLog.getInstance().logEvent(new Event("Washer added to laundry room."));
        } else if (machine.getType().equalsIgnoreCase("dryer")) {
            dryers.add(machine);
            EventLog.getInstance().logEvent(new Event("Dryer added to laundry room."));
        }
    }

    // MODIFIES: this
    // EFFECTS: removes given washer from list of it's type
    public void removeWasher(Machine machine) {
        washers.remove(machine);
        EventLog.getInstance().logEvent(new Event("Washer removed from laundry room."));
    }

    // MODIFIES: this
    // EFFECTS: removes given dryer from list of it's type
    public void removeDryer(Machine machine) {
        dryers.remove(machine);
        EventLog.getInstance().logEvent(new Event("Dryer removed from laundry room."));
    }

    // MODIFIES: use, totalUse
    // EFFECTS: returns a statement about the amount of times a washer has been used
    //          relative to the total times every washer in the room has been used
    public String frequencyWash(Machine washer) {
        int use = washer.getUses();
        int totalUse = 0;
        for (Machine w : washers) {
            totalUse += w.getUses();
        }
        return washer.getName() + " has been used " + use + " times out of the " + totalUse
                + " times a washer has been used in this laundry room";
    }

    // MODIFIES: use, totalUse
    // EFFECTS: returns a statement about the times a dryer has been used
    //          relative to the total times every dryer in the room has been used
    public String frequencyDry(Machine dryer) {
        int use = dryer.getUses();
        int totalUse = 0;
        for (Machine d : dryers) {
            totalUse += d.getUses();
        }
        return dryer.getName() + " has been used " + use + " times out of the " + totalUse
                + " times a dryer has been used in this laundry room";
    }

    // EFFECTS: returns the machine that has the same name given by the user
    public Machine getWasher(String machine) {
        Machine m = null;
        for (Machine w : washers) {
            if (w.getName().equals(machine)) {
                m = w;
            }
        }
        return m;
    }

    // EFFECTS: returns the machine that has the same name given by the user
    public Machine getDryer(String machine) {
        Machine m = null;
        for (Machine d : dryers) {
            if (d.getName().equals(machine)) {
                m = d;
            }
        }
        return m;
    }

    // EFFECTS: returns list of washers
    public List<Machine> getWasherMachine() {
        return washers;
    }

    // EFFECTS: returns list of dryers
    public List<Machine> getDryerMachine() {
        return dryers;
    }

    // MODIFIES: washer
    // EFFECTS: returns list of all washer names in list washers
    public List<String> getWashers() {
        List<String> washer = new ArrayList<>();
        for (Machine w : washers) {
            washer.add(w.getName());
        }
        return washer;
    }

    // MODIFIES: dryer
    // EFFECTS: returns list of all dryer names in list dryers
    public List<String> getDryers() {
        List<String> dryer = new ArrayList<>();
        for (Machine d : dryers) {
            dryer.add(d.getName());
        }
        return dryer;
    }

    // from Json Serialization Demo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("machines", machinesToJson());
        return json;
    }

    // EFFECTS: returns things in this Laundry as a JSON array
    private JSONArray machinesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Machine d : dryers) {
            jsonArray.put(d.toJson());
        }
        for (Machine w : washers) {
            jsonArray.put(w.toJson());
        }

        return jsonArray;
    }
}
