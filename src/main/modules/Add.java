package main.modules;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.TreeSet;

import main.Connector;
import main.Input;
import main.Module;
import main.Output;
import main.editor.ModuleEditor;

public class Add implements Module {

	private Point upperLeft;
	private Input input1;
	private Input input2;
	private Output output1;
	private Output output2;
	private int width;
	private int height;
	private double prevValue = 0.0;
	private double nextValue;
	
	public Add() {
		input1 = new Input();
		input2 = new Input();
		output1 = new Output(this);
		output2 = new Output(this);
	}
	
	public double getOutput(TreeSet<Module> modulesVisited) {
		if(modulesVisited.contains(this)) return prevValue;
		modulesVisited.add(this);
		double d1 = input1.getValue(modulesVisited);
		double d2 = input2.getValue(modulesVisited);
		nextValue = d1 + d2;
		return nextValue;
	}
	
	public void draw(Graphics g, Point upperLeft) {
		this.upperLeft = upperLeft;
		int currentX = upperLeft.x;
		int currentY = upperLeft.y;
		String row1 = "- x   x+y -";
		String row2 = "- y";
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		g2.setFont(new Font("Arial", Font.ITALIC, 16));
		FontMetrics fm = g2.getFontMetrics(new Font("Arial", Font.ITALIC, 16));
		Rectangle2D row1bounds = fm.getStringBounds(row1, g2);
		width = (int) row1bounds.getWidth();
		height = (int) 25;
		// fill module background
		g2.setColor(Color.GRAY);
		g2.setStroke(new BasicStroke(2));
		g2.drawRect(upperLeft.x, upperLeft.y, width, height);
		g2.setColor(Color.WHITE);
		g2.fillRect(upperLeft.x, upperLeft.y, width, height);
		// draw first row
		currentY += 11;
		g2.setColor(Color.BLACK);
		g2.drawString(row1, currentX, currentY);
		g2.setColor(Color.GREEN);
		input1.draw(g2, new Rectangle(currentX, currentY - 10, 7, 10));
		g2.setColor(Color.BLUE);
		output1.draw(g2, new Rectangle(currentX + width -  7, currentY - 10, 7, 10));
		// draw second row
		currentY += 11;
		g2.setColor(Color.BLACK);
		g2.drawString(row2, currentX, currentY);
		g2.setColor(Color.GREEN);
		input2.draw(g2, new Rectangle(currentX, currentY - 10, 7, 10));
		g2.setColor(Color.BLUE);
		output2.draw(g2, new Rectangle(currentX + width -  7, currentY - 10, 7, 10));
	}
	
	public ArrayList<Connector> getConnectors() {
		ArrayList<Connector> returnVal = new ArrayList<Connector>();
		returnVal.add(input1);
		returnVal.add(input2);
		returnVal.add(output1);
		returnVal.add(output2);
		return returnVal;
	}

	@Override
	public void mouseClicked(Point p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean pointIsInside(Point p) {
		Rectangle bounds = new Rectangle(upperLeft.x, upperLeft.y, width, height);
		return bounds.contains(p);
	}

}
