package main.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.Connector;
import main.Module.Types;
import main.MultiWindow;
import main.Module;
import main.modules.*;

public class ModuleEditor extends JPanel  {
	
	private MultiWindow parent;
	private ModuleEditorView view;
	private ModuleEditorController controller;
	
	private ArrayList<Module> modules;
	private HashMap<Module, Point> moduleToPosition;
	protected Connector connectorSelected = null;
	protected Module moduleSelected = null;
	protected Point mousePosition = null;
	
	public ModuleEditor(MultiWindow parent) {
		super(new BorderLayout());
		this.parent = parent;
	    view = new ModuleEditorView(this);
	    view.setBackground(Color.black);
	    controller = new ModuleEditorController(this);
	    view.addMouseListener(controller);
	    view.addMouseMotionListener(controller);
	    view.setPreferredSize(new Dimension(1500, 840));
	    JScrollPane scrollPane = new JScrollPane(view);
	    scrollPane.setSize(1500, 840);
	    add(scrollPane, BorderLayout.CENTER);
	    modules = new ArrayList<Module>();
	    moduleToPosition = new HashMap<Module, Point>();
	}

	public void updateView() {
		view.repaint();
	}
	
	
	
	public void addModule(Point p) {
		Module.Types type = (Types) JOptionPane.showInputDialog(
		                    MultiWindow.moduleEditor,
		                    "Select Module Type",
		                    "Available Modules", 
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    Module.Types.values(),
		                    Module.Types.ADD);
		Module newModule = null;
		if(type == Module.Types.ADD) newModule = new Add();
		moduleToPosition.put(newModule, p);
		modules.add(newModule);
		view.repaint();
	}
	
	public ArrayList<Module> getModules() {
		return modules;
	}
	
	public Point getLocation(Module module) {
		return moduleToPosition.get(module);
	}
	
	public void moduleDragged(Point p) {
		int deltaX = mousePosition.x - p.x;
		int deltaY = mousePosition.y - p.y;
		moduleToPosition.get(moduleSelected).x -= deltaX;
		moduleToPosition.get(moduleSelected).y -= deltaY;
		view.repaint();
	}

}
