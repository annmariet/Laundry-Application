package ui.tools;

import ui.LaundryAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// from SimpleDrawingPlayer
// creates a tool that can save laundry room to Json file
public class SaveTool extends Tool {

    public SaveTool(LaundryAppGUI gui, JComponent parent) {
        super(gui, parent);
    }

    // MODIFIES: this
    // EFFECTS:  creates new button and adds to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Save Laundry Room");
        button = customizeButton(button);
    }

    // MODIFIES: this
    // EFFECTS:  associate button with new ClickHandler
    @Override
    protected void addListener() {
        button.addActionListener(new SaveTool.SaveToolClickHandler());
    }

    // creates an action listener for this tool
    private class SaveToolClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the laundry machine tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.saveLaundry();
            gui.setActiveTool(SaveTool.this);
        }
    }
}
