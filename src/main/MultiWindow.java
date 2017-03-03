package main;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import main.directory.FileBrowser;
import main.editor.ModuleEditor;

public class MultiWindow extends WindowAdapter {

	public JFrame fileBrowser;
	public JFrame moduleEditor;
	public JTabbedPane pane;
	
	public MultiWindow() {
		fileBrowser = new JFrame();
		fileBrowser.add(new FileBrowser(this));
		fileBrowser.setLocation(100, 100);
		fileBrowser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fileBrowser.pack();
		fileBrowser.setVisible(true);
		moduleEditor = new JFrame();
		moduleEditor.add(new ModuleEditor(this));
		moduleEditor.setLocation(100, 100);
		moduleEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		moduleEditor.pack();
		moduleEditor.setVisible(true);
	}
}
