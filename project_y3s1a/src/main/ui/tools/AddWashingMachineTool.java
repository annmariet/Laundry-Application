package ui.tools;

import model.Machine;
import ui.ImageCreator;
import ui.LaundryAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

// from SimpleDrawingPlayer
// creates washer tool to add washer image to laundry room
public class AddWashingMachineTool extends Tool {

    public AddWashingMachineTool(LaundryAppGUI gui, JComponent parent) {
        super(gui, parent);
    }

    // MODIFIES: this
    // EFFECTS:  creates new button and adds to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Add Washing Machine");
        button = customizeButton(button);
    }

    // MODIFIES: this
    // EFFECTS:  associate button with new ClickHandler
    @Override
    protected void addListener() {
        button.addActionListener(new WashingMachineClickHandler());
    }

    // MODIFIES: this
    // EFFECTS: associate click at point with adding a new washing machine
    @Override
    public void mouseClickedInDrawingArea(MouseEvent e) {
        Machine machine = new
                Machine("washer", "washer", 0, false, false, e.getX(), e.getY());
        gui.addToPanel(machine);
    }

    // creates an action listener for this tool
    private class WashingMachineClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the laundry machine tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.setActiveTool(AddWashingMachineTool.this);
        }
    }
}
