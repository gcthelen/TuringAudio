package main.directory;

import java.awt.event.*;

public class FileBrowserController implements MouseListener, MouseMotionListener, ActionListener {

	private FileBrowser parent;
	
	FileBrowserController(FileBrowser parent) {
		this.parent = parent;
	}

	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseClicked(MouseEvent e){
		for(Visible visible: parent.getVisibleFiles()) {
			if(visible.isMouseInside(e.getX(), e.getY())) {
				visible.handleClick();
			}
		}
	}
	
	public void mouseMoved(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("All".equals(e.getActionCommand())) parent.showAllSamples();
		if ("Used".equals(e.getActionCommand())) parent.showUsedSamples();
	}
	
}
