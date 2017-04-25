package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.util.TreeSet;

public class Output implements Connector {
	
		private Module module;
		private Input connectedTo;
		private Rectangle bounds;
		
		public Output(Module module) {
			this.module = module;
		}
		
		public void setInvisible() {
			bounds = null;
		}
		
		public void draw(Graphics2D g2, Rectangle bounds) {
			this.bounds = bounds;
			// draw connector
			g2.setColor(Color.GREEN);
			g2.fill(bounds);
			// draw wire if has connection
			if(connectedTo != null) {
				GradientPaint redtowhite = new GradientPaint(0,0,Color.RED,100, 0,Color.WHITE);
				g2.setStroke(new BasicStroke(2));
				g2.setPaint(redtowhite);
				g2.drawLine((int) bounds.getCenterX(), (int) bounds.getCenterY(), (int) connectedTo.getBounds().getCenterX(), (int) connectedTo.getBounds().getCenterY());
			}
		}
		
		public boolean pointIsInside(Point p) {
			return bounds.contains(p);
		}
		
		public double getValue(TreeSet<Module> visited) {
			return module.getOutput(visited);
		}
		
		public void setConnection(Input input) {
			connectedTo = input;
		}
		
		public boolean hasConnection() {
			return connectedTo != null;
		}

		public void disconnect() {
			if(connectedTo != null) connectedTo.setConnection(null);
			connectedTo = null;
		}


}
