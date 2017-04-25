package main.editor;
import javax.swing.*;

import main.Module;

import java.awt.*;

public class ModuleEditorView extends JPanel{

	private static final long serialVersionUID = 460120758786887240L;
	private ModuleEditor parent;

	ModuleEditorView(ModuleEditor parent) {
		this.parent = parent;
	}
	
    protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
    	super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for(Module module: parent.getModules()) {
			module.draw(g2, parent.getLocation(module));
		}
    }

}
