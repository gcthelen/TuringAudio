package main.editor;

import java.awt.event.*;

public class ModuleEditorController implements MouseListener, MouseMotionListener, ActionListener {

	private ModuleEditor parent;
	
	ModuleEditorController(ModuleEditor parent) {
		this.parent = parent;
	}

	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseClicked(MouseEvent e){
	}
	
	public void mouseMoved(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("All".equals(e.getActionCommand())) return;
		if ("Used".equals(e.getActionCommand())) return;
	}
	
}
