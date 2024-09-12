package model;

import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;

// represents either a washer or dryer and its uses in a laundry room
public class Machine implements Writable {
    private final String name;
    private final String type;
    private boolean inUse;
    private boolean broken;
    private int uses;
    private final int pointx;
    private final int pointy;

    // EFFECTS: constructs a machine with type washer or dryer, 0 uses
    //          and that is not broken or in use and with name of user's choosing
    public Machine(String type, String name, int uses, boolean broken, boolean inUse, int x, int y) {
        this.name = name;
        this.type = type;
        this.uses = uses;
        this.broken = broken;
        this.inUse = inUse;
        this.pointx = x;
        this.pointy = y;
    }

    // MODIFIES: this
    // EFFECTS: toggles the broken status from true to false or from false to true
    public void setBrokenStatus() {
        broken = !broken;
    }

    // MODIFIES: this
    // EFFECTS: toggles use status from true to false or from false to true
    //          and increments the use if the machine is being used
    public void setUseStatus() {
        if (!inUse) {
            inUse = true;
            uses++;
        } else {
            inUse = false;
        }
    }

    // EFFECTS: returns use status
    public boolean getStatus() {
        return inUse;
    }

    // EFFECTS: returns broken status
    public boolean getIfBroken() {
        return broken;
    }

    // REQUIRES: string must be either washer or dryer
    // EFFECTS: returns type of machine
    public String getType() {
        return type;
    }

    // EFFECTS: returns the user given name of the machine
    public String getName() {
        return name;
    }

    // EFFECTS: returns the amount of times a machine has been used
    public int getUses() {
        return uses;
    }

    // from SimpleDrawingPlayer
    // EFFECTS: return true iff the given x value is within the bounds of the Shape
    public boolean containsX(int x) {
        return (this.pointx <= x) && (x <= this.pointx + 100);
    }

    // from SimpleDrawingPlayer
    // EFFECTS: return true iff the given y value is within the bounds of the Shape
    public boolean containsY(int y) {
        return (this.pointy <= y) && (y <= this.pointy + 138);
    }

    // from SimpleDrawingPlayer
    // EFFECTS: return true if the given Point (x,y) is contained within the bounds of this Shape
    public boolean contains(Point point) {
        int pointx = point.x;
        int pointy = point.y;

        return containsX(pointx) && containsY(pointy);
    }

    // from SimpleDrawingPlayer
    // EFFECTS: returns the x point of the machine
    public int getPointx() {
        return pointx;
    }

    // from SimpleDrawingPlayer
    // EFFECTS: returns the y point of the machine
    public int getPointy() {
        return pointy;
    }

    // from Json Serialization Demo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("type", type);
        json.put("uses", uses);
        json.put("broken", broken);
        json.put("inUse", inUse);
        json.put("x", pointx);
        json.put("y", pointy);
        return json;
    }
}
