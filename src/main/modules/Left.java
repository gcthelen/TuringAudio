package main.modules;

import java.awt.*;
import java.util.ArrayList;
import java.util.TreeSet;

import main.Connector;
import main.Module;
import main.editor.ModuleEditor;

public class Left implements Module {

	private int cornerX;
	private int cornerY;
	private int width = 100;
	private int height = 10;
	private Connector input;
	
	private ModuleEditor parent;

	Left(ModuleEditor parent, int moduleID, int cornerX, int cornerY) {
		this.parent = parent;
		this.cornerX = cornerX;
		this.cornerY = cornerY;
	}
	
	public void draw(Graphics g) {
		int currentX = cornerX;
		int currentY = cornerY;
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.GRAY);
		g2.setStroke(new BasicStroke(2));
		g2.drawRect(cornerX, cornerY, width, height);
		g2.setColor(Color.WHITE);
		g2.fillRect(cornerX, cornerY, width, height);
		int fontSize = 12;
		int yStep = fontSize + 6;
		g2.setColor(Color.GREEN);
		g2.drawString("LEFT", currentX, currentY);
	}

	@Override
	public void mouseClicked(Point p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean pointIsInside(Point p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw(Graphics g, Point upperLeft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getOutput(TreeSet<Module> visited) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Connector> getConnectors() {
		// TODO Auto-generated method stub
		return null;
	}

}
