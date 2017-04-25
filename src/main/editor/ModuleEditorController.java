package main.editor;

import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

import main.Connector;
import main.Input;
import main.Module;
import main.Output;

public class ModuleEditorController implements MouseListener, MouseMotionListener, ActionListener {

	private ModuleEditor parent;
	
	ModuleEditorController(ModuleEditor parent) {
		this.parent = parent;
	}

	public void mouseReleased(MouseEvent e) {
		// stop dragging
		parent.moduleSelected = null;
	}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	
	public void mousePressed(MouseEvent e){
		Point p = new Point(e.getX(), e.getY());
		for(Module module: parent.getModules()) {
			// check if we are over a connection
			// if so handle connection
			for(Connector connector: module.getConnectors()) {
				if(connector.pointIsInside(p)) {
					if(parent.connectorSelected == null) {
						// start new connection
						connector.disconnect();
						parent.connectorSelected = connector;
						return;
					} else {
						// return if trying to connect Input to Input or Output to Output
						if(connector.getClass() == parent.connectorSelected.getClass()) return;
						// connect input to output
						if(connector.getClass() == main.Input.class) {
							Input input = (Input) connector;
							Output output = (Output) parent.connectorSelected;
							input.setConnection(output);
							parent.connectorSelected = null;
							return;
						} else {
							// connect output to input
							Input input = (Input) parent.connectorSelected;
							Output output = (Output) connector;
							input.setConnection(output);
							parent.connectorSelected = null;
							return;
						}
					}
				}
			}
			// at this point not over a connection, check if over a module
			if(module.pointIsInside(p)) {
				parent.moduleSelected = module;
				return;
			}
		}
		// cancel connection
		parent.connectorSelected = null;
	}
	
	public void mouseClicked(MouseEvent e){
		Point p = new Point(e.getX(), e.getY());
		if(e.getClickCount() == 2) {
			// check if we're over a module
			for(Module module: parent.getModules()) {
				if(module.pointIsInside(p)) {
					// if so handle click
					module.mouseClicked(p);
					return;
				}
			}
			// not over a module so add one
			parent.addModule(p);
			return;
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		parent.mousePosition = new Point(e.getX(), e.getY());
	}

	public void mouseDragged(MouseEvent e) {
		Point p = new Point(e.getX(), e.getY());
		if(parent.moduleSelected != null) {
			parent.moduleDragged(p);
		}
		parent.mousePosition = p;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
