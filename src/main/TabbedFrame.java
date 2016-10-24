package main;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import main.directory.SampleBrowser;

public class TabbedFrame extends JFrame {
	
	private JTabbedPane pane;
	private MultiWindow parent;
	
	public TabbedFrame(MultiWindow parent) {
		this.parent = parent;
		setLocation(100, 100);
		pane = new JTabbedPane();
		pane.add("Sample Browser", (JComponent) new SampleBrowser(this));
		add(pane);
		pack();
		setVisible(true);
		
	}
}
