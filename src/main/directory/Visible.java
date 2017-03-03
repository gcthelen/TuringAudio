package main.directory;

import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;

// Essentially extends Java File
public class Visible {

	private FileBrowser parent;
	private File file;
	private ArrayList<Visible> children;
	private boolean isOpen = false;
	private int depth;
	private Rectangle mouseArea;

	public Visible(FileBrowser parent, File file, int depth) {
		this.parent = parent;
		this.file = file;
		this.depth = depth;
		if(!file.isDirectory()) return;
		children = new ArrayList<Visible>();
		File[] filesList = file.listFiles();
		for (File child : filesList) children.add(new Visible(parent, child, depth + 1));
	}

	public String getName() {
		return file.getName();
	}
	
	public boolean isDirectory() {
		return file.isDirectory();
	}
	
	public int getDepth() {
		return depth;
	}
	
	public boolean isOpen() {
		if(file.isDirectory()) return isOpen;
		return false;
	}

	public boolean isMouseInside(int x, int y) {
		return mouseArea.contains(x, y);
	}
	
	public void setMouseArea(Rectangle rect) {
		this.mouseArea = rect;
	}
	
	public void handleClick() {
		if(file.isDirectory()) {
			isOpen = !isOpen;
			parent.getView().repaint();
		} else {
			return;
		}
	}
	
	public ArrayList<Visible> getVisibleChildren() {
		ArrayList<Visible> returnVal = new ArrayList<Visible>();
		for(Visible child: children) {
			returnVal.add(child);
			if(child.isOpen()) returnVal.addAll(child.getVisibleChildren());
		}
		return returnVal;
	}

}
