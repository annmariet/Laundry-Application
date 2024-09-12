package ui.tools;

import ui.LaundryAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// from SimpleDrawingPlayer
// creates a tool that can load from Json File
public class LoadTool extends Tool {

    public LoadTool(LaundryAppGUI gui, JComponent parent) {
        super(gui, parent);
    }

    // MODIFIES: this
    // EFFECTS:  creates new button and adds to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Load Laundry Room");
        button = customizeButton(button);
    }

    // MODIFIES: this
    // EFFECTS:  associate button with new ClickHandler
    @Override
    protected void addListener() {
        button.addActionListener(new LoadTool.LoadToolClickHandler());
    }

    // creates an action listener for this tool
    private class LoadToolClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the laundry machine tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.loadLaundry();
            gui.setActiveTool(LoadTool.this);
        }
    }
}
