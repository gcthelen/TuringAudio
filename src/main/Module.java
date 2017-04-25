package main;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;


public interface Module {
	
	public enum ConnectorType {
		INPUT,
		OUTPUT,
	}
	
	// Build in modules
	public static enum Types {
		ADD,
		SINE,
		MULTIPLY,
		DIVIDE,
		RIGHT,
		LEFT
	}
	
	abstract void mouseClicked(Point p);
	abstract boolean pointIsInside(Point p);
	abstract void draw(Graphics g, Point upperLeft);
	abstract double getOutput(TreeSet<Module> visited);
	abstract ArrayList<Connector> getConnectors();
	
	/*
	abstract Integer getModuleId();
	abstract void setModuleId(Integer moduleID);
	abstract void loadModuleInfo(BufferedReader in);
	abstract void saveModuleInfo(BufferedWriter out);
	*/
	/*
	public interface Connector {
		
		abstract Rectangle getSelectArea();
		abstract Integer getConnectorID();
		abstract ConnectorType getConnectorType();
		abstract void setConnection(Integer connectionID);
		abstract void setConnectorID(Integer id);
		abstract Integer getConnection();
		abstract void removeConnection();
		abstract Module getParent();
	}
	
	public abstract class Output implements Connector {
		
		Rectangle selectArea = null;
		Integer connectorID = null;
		ConnectorType connectionType = ConnectorType.OUTPUT;
		Integer connectedTo = null;
		Module parent = null;
		
		public Output(Module parent, Rectangle selectArea) {
			this.parent = parent;
			this.selectArea = selectArea;
		}
		
		public Rectangle getSelectArea() {
			return selectArea;
		}
		
		public void setConnectorID(Integer id) {
			this.connectorID = id;
		}
		
		public Integer getConnectorID() {
			return connectorID;
		}
		
		public ConnectorType getConnectorType() {
			return connectionType;
		}
		
		public void setConnection(Integer connectedTo) {
			this.connectedTo = connectedTo;
		}
		
		public Integer getConnection() {
			return this.connectedTo;
		}
		
		public void removeConnection() {
			this.connectedTo = null;
		}
		
		public Module getParent() {
			return parent;
		}
		
		public abstract double[] getSamples(HashSet<Integer> waitingForModuleIDs, double[] control);
		
		public abstract void clearSamples();
		
	}
	
	public abstract class Input implements Connector {
		
		Rectangle selectArea = null;
		Integer connectorID = null;
		ConnectorType connectionType = ConnectorType.INPUT;
		Integer connectedFrom = null;
		Module parent = null;
		
		public Input(Module parent, Rectangle selectArea) {
			this.parent = parent;
			this.selectArea = selectArea;
		}
		
		public Rectangle getSelectArea() {
			return selectArea;
		}
		
		public void setConnectorID(Integer id) {
			this.connectorID = id;
		}
		
		public Integer getConnectorID() {
			return connectorID;
		}
		
		public ConnectorType getConnectorType() {
			return connectionType;
		}
		
		public void setConnection(Integer connectedFrom) {
			this.connectedFrom = connectedFrom;
		}
		
		public Integer getConnection() {
			return this.connectedFrom;
		}
		
		public void removeConnection() {
			this.connectedFrom = null;
		}
		
		public Module getParent() {
			return parent;
		}
	} */
}
