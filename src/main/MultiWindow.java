package main;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import main.directory.SampleBrowser;

public class MultiWindow extends WindowAdapter {

	public TabbedFrame tabbedFrame;
	public JTabbedPane pane;
	
	public MultiWindow() {
		tabbedFrame = new TabbedFrame(this);
		tabbedFrame.setLocation(100, 100);
		tabbedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
