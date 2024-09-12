package ui.tools;

import model.Machine;
import ui.LaundryAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

// from SimpleDrawingPlayer
// creates a dryer tool to add dryer image to laundry room
public class AddDryerTool extends Tool {

    public AddDryerTool(LaundryAppGUI gui, JComponent parent) {
        super(gui, parent);
    }

    // MODIFIES: this
    // EFFECTS:  creates new button and adds to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Add Dryer");
        button = customizeButton(button);
    }

    // MODIFIES: this
    // EFFECTS:  associate button with new ClickHandler
    @Override
    protected void addListener() {
        button.addActionListener(new DryerClickHandler());
    }

    // MODIFIES: this
    // EFFECTS: associate click at point with adding machine
    @Override
    public void mouseClickedInDrawingArea(MouseEvent e) {
        Machine machine =
                new Machine("dryer", "dryer", 0, false, false, e.getX(), e.getY());
        gui.addToPanel(machine);
    }

    // creates an action listener for this tool
    private class DryerClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the laundry machine tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.setActiveTool(AddDryerTool.this);
        }
    }
}
