package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.TreeSet;

public class Input implements Connector {
	
		private Output connectedTo;
		private Rectangle bounds;
		
		public Input() {}
		
		
		public void setInvisible() {
			bounds = null;
		}
		public void draw(Graphics2D g2, Rectangle bounds) {
			this.bounds = bounds;
			g2.setColor(Color.GREEN);
			g2.fill(bounds);
		}
		
		protected Rectangle getBounds() {
			return bounds;
		}
		
		public boolean pointIsInside(Point p) {
			return bounds.contains(p);
		}
		
		public double getValue(TreeSet<Module> visited) {
			if(connectedTo == null) return 0;
			return connectedTo.getValue(visited);
		}
		
		public void setConnection(Output output) {
			if(connectedTo != null) connectedTo.setConnection(null);
			this.connectedTo = output;
		}
		
		public boolean hasConnection() {
			return connectedTo != null;
		}

		public void disconnect() {
			if(connectedTo != null) connectedTo.setConnection(null);
			connectedTo = null;
		}

}
