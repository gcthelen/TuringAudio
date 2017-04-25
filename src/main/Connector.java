package main;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.TreeSet;

public interface Connector {
	
	public void draw(Graphics2D g2, Rectangle bounds);
	public boolean pointIsInside(Point p);
	public double getValue(TreeSet<Module> visited);
	public boolean hasConnection();
	public void disconnect();
}
