package ui;


import model.EventLog;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.console.LaundryPanel;
import ui.tools.*;
import model.Laundry;
import model.Machine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

// from SimpleDrawingPlayer
// creates a GUI for the laundry app
public class LaundryAppGUI extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    private List<Tool> tools;
    private Tool activeTool;
    private Laundry laundryRoom;

    private LaundryPanel currentPanel;

    private static final String JSON_STORE = "./data/laundry.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public LaundryAppGUI() {
        super("Laundry Room");
        initializeFields();
        initializeGraphics();
        initializeInteraction();
    }

    // EFFECTS: returns current panel
    public LaundryPanel getCurrentPanel() {
        return currentPanel;
    }

    // MODIFIES: this
    // EFFECTS:  initializes a DrawingMouseListener to be used in the JFrame
    private void initializeInteraction() {
        DrawingMouseListener dml = new DrawingMouseListener();
        addMouseListener(dml);
        addMouseMotionListener(dml);
    }

    // MODIFIES: this
    // EFFECTS: initializes each of the graphics, as well as the tools to be
    //          displayed in the JFrame
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createTools();
        addNewPanel();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                printLog(EventLog.getInstance());
                System.exit(0);
            }
        });
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // code from AlarmSystem
    // EFFECTS: represents action to be taken when user wants to print event log
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }

    // MODIFIES: this
    // EFFECTS:  sets activeTool, currentDrawing to null, and instantiates drawings and tools with ArrayList
    //           this method is called by the DrawingEditor constructor
    private void initializeFields() {
        laundryRoom = new Laundry();
        activeTool = null;
        currentPanel = null;
        tools = new ArrayList<Tool>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: initializes a new laundry panel and adds to the JFrame
    private void addNewPanel() {
        LaundryPanel newPanel = new LaundryPanel();
        currentPanel = newPanel;
        add(newPanel, BorderLayout.CENTER);
        validate();
    }

    // MODIFIES: this
    // EFFECTS: adds a laundry machine to the JFrame
    public void addToPanel(Machine machine) {
        laundryRoom.addMachine(machine);
        currentPanel.addMachineImage(laundryRoom);
    }

    // EFFECTS: if activeTool != null, then mouseClickedInDrawingArea is invoked on activeTool, depends on the
    //          type of the tool which is currently activeTool
    private void handleMouseClicked(MouseEvent e) {
        if (activeTool != null) {
            activeTool.mouseClickedInDrawingArea(e);
        }
        repaint();
    }

    // MODIFIES: this
    // EFFECTS:  sets the given tool as the activeTool
    public void setActiveTool(Tool tool) {
        if (activeTool != null) {
            activeTool.deactivate();
        }
        tool.activate();
        activeTool = tool;
    }

    // MODIFIES: this
    // EFFECTS:  a helper method which declares and instantiates all tools
    private void createTools() {
        JPanel toolArea = new JPanel();
        toolArea.setLayout(new GridLayout(0,1));
        toolArea.setSize(new Dimension(0, 0));
        add(toolArea, BorderLayout.SOUTH);

        AddWashingMachineTool washingMachine = new AddWashingMachineTool(this, toolArea);
        tools.add(washingMachine);

        AddDryerTool dryerTool = new AddDryerTool(this, toolArea);
        tools.add(dryerTool);

        SaveTool save = new SaveTool(this, toolArea);
        tools.add(save);

        LoadTool load = new LoadTool(this, toolArea);
        tools.add(load);

        DeleteTool delete = new DeleteTool(this, toolArea);
        tools.add(delete);

        setActiveTool(washingMachine);
    }

    // MODIFIES: this
    // EFFECTS: saves the current laundry room to JSON file
    public void saveLaundry() {
        try {
            jsonWriter.open();
            jsonWriter.write(laundryRoom);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads laundry room from JSON file
    public void loadLaundry() {
        try {
            laundryRoom = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        currentPanel.addMachineImage(laundryRoom);
        repaint();
    }

    // MODIFIES: this
    // EFFECTS:  removes given Shape from currentDrawing
    public void removeFromPanel(Machine machine) {
        currentPanel.removeMachine(machine);
    }

    // EFFECTS: returns machines at a point on JFrame
    public Machine getMachineInPanel(Point point) {
        return currentPanel.getMachinesAtPoint(point);
    }


    // creates a mouse listener for the laundryRoom
    private class DrawingMouseListener extends MouseAdapter {
        // EFFECTS:Forward mouse clicked event to the active tool
        public void mouseClicked(MouseEvent e) {
            handleMouseClicked(e);
        }
    }


    // runs the GUI
    public static void main(String[] args) {
        new LaundryAppGUI();
    }
}
