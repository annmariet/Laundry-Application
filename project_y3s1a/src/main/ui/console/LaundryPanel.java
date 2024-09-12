package ui.console;

import model.Laundry;
import model.Machine;
import ui.ImageCreator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// from SimpleDrawingPlayer
// creates a JPanel for the laundry to be displayed
public class LaundryPanel extends JPanel {
    private final List<ImageCreator> images;
    private Laundry laundryRoom;
    private ImageIcon image;

    public LaundryPanel() {
        super();
        images = new ArrayList<>();
        laundryRoom = new Laundry();
        setBackground(Color.DARK_GRAY);
    }

    // EFFECTS: returns the images
    public List<ImageCreator> getImages() {
        return this.images;
    }

    // MODIFIES: this
    // EFFECTS: paints each of the machines on the JPanel
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        List<Machine> dryers = laundryRoom.getDryerMachine();
        List<Machine> washers = laundryRoom.getWasherMachine();
        String sep = System.getProperty("file.separator");
        for (Machine dryer : dryers) {
            image = new ImageIcon(System.getProperty("user.dir") + sep
                    + "images" + sep + "dryer.jpg");
            g.drawImage(image.getImage(), dryer.getPointx(), dryer.getPointy(), null);
        }
        for (Machine washer : washers) {
            image = new ImageIcon(System.getProperty("user.dir") + sep
                    + "images" + sep + "washer.jpg");
            g.drawImage(image.getImage(), washer.getPointx(), washer.getPointy(), null);
        }
    }

    // MODIFIES: this
    // EFFECTS:  removes shape from the drawing
    public void removeMachine(Machine machine) {
        if (machine.getType().equalsIgnoreCase("washer")) {
            laundryRoom.removeWasher(machine);
        } else {
            laundryRoom.removeDryer(machine);
        }
        repaint();
    }

    //EFFECTS: returns the machine at a point on the JPanel
    public Machine getMachinesAtPoint(Point point) {
        List<Machine> dryers = laundryRoom.getDryerMachine();
        List<Machine> washers = laundryRoom.getWasherMachine();
        for (Machine dryer : dryers) {
            if (dryer.contains(point)) {
                return dryer;
            }
        }
        for (Machine washer : washers) {
            if (washer.contains(point)) {
                return washer;
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: adds a machine to the drawing
    public void addMachineImage(Laundry laundry) {
        this.laundryRoom = laundry;
    }
}
