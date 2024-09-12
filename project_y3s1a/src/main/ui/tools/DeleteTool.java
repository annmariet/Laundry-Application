package ui.tools;

import model.Machine;
import ui.LaundryAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

// from SimpleDrawingPlayer
// creates a tool that can delete machines in the panel
public class DeleteTool extends Tool {
    private Machine machineToDelete;

    public DeleteTool(LaundryAppGUI gui, JComponent parent) {
        super(gui, parent);
    }

    // MODIFIES: this
    // EFFECTS:  constructs a delete button which is then added to the JComponent (parent)
    //           which is passed in as a parameter
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Delete Machine");
        addToParent(parent);
    }

    // MODIFIES: this
    // EFFECTS:  constructs a new listener object which is added to the JButton
    @Override
    protected void addListener() {
        button.addActionListener(new DeleteToolClickHandler());
    }


    // MODIFIES: this
    // EFFECTS:  unselects the shape being deleted, and removes it from the drawing
    @Override
    public void mouseClickedInDrawingArea(MouseEvent e) {
        machineToDelete = gui.getMachineInPanel(e.getPoint());
        if (machineToDelete != null) {
            gui.removeFromPanel(machineToDelete);
            machineToDelete = null;
        }
    }

    // creates an action listener for this tool
    private class DeleteToolClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the delete tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.setActiveTool(DeleteTool.this);
        }
    }
}
