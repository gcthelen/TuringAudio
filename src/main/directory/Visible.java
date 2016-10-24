package main.directory;

import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;

import main.WavFile;

// Essentially extends Java File
public class Visible {

	private File file;
	private ArrayList<Visible> children;
	private boolean isOpen = true;
	private int depth;
	private Rectangle mouseArea;

	public Visible(File file, int depth) {
		this.file = file;
		this.depth = depth;
		if(!file.isDirectory()) return;
		children = new ArrayList<Visible>();
		File[] filesList = file.listFiles();
		for (File child : filesList) children.add(new Visible(child, depth + 1));
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

	public boolean isMouseInside(SampleBrowserView view, int x, int y) {
		return mouseArea.contains(x, y);
	}
	
	public void setMouseArea(Rectangle rect) {
		this.mouseArea = rect;
	}
	
	public void handleClick() {
		if(file.isDirectory()) {
			isOpen = !isOpen;
		} else {
			playWAVFile(file);
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
	
	// returns false if file could not be played
	private boolean playWAVFile(File file) {	
		try
	    {
	       // Open the wav file specified as the first argument
	       WavFile wavFile = WavFile.openWavFile(file);
	
	       // Display information about the wav file
	       wavFile.display();
	
	       // Get the number of audio channels in the wav file
	       int numChannels = wavFile.getNumChannels();
	
	       // Create a buffer of 100 frames
	       double[] buffer = new double[100 * numChannels];
	
	       int framesRead;
	       double min = Double.MAX_VALUE;
	       double max = Double.MIN_VALUE;
	
	       do
	       {
	          // Read frames into buffer
	          framesRead = wavFile.readFrames(buffer, 100);
	
	          // Loop through frames and look for minimum and maximum value
	          for (int s=0 ; s<framesRead * numChannels ; s++)
	          {
	             if (buffer[s] > max) max = buffer[s];
	             if (buffer[s] < min) min = buffer[s];
	          }
	       }
	       while (framesRead != 0);
	
	       // Close the wavFile
	       wavFile.close();
	
	       // Output the minimum and maximum value
	       System.out.printf("Min: %f, Max: %f\n", min, max);
	    } catch (Exception e)
	    {
	       System.err.println(e);
	       return false;
	    }
		return true;
	}
}
