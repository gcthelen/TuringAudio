package main.directory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import main.MultiWindow;

public class FileBrowser extends JPanel  {
	
	public static final String samplesDir = "C:\\Samples";

	private static final long serialVersionUID = -7203847788002161520L;
	private MultiWindow parent;
	private FileBrowserView view;
	private FileBrowserController controller;
	private JToolBar navigationBar;
	private Visible tree;
	
	public FileBrowser(MultiWindow parent) {
		super(new BorderLayout());
		this.parent = parent;
	    view = new FileBrowserView(this);
	    view.setBackground(Color.black);
	    controller = new FileBrowserController(this);
	    navigationBar = new JToolBar();
	    add(createNavigationBar(), BorderLayout.PAGE_START);
	    view.addMouseListener(controller);
	    view.addMouseMotionListener(controller);
	    view.setPreferredSize(new Dimension(1500, 840));
	    JScrollPane scrollPane = new JScrollPane(view);
	    scrollPane.setSize(1500, 840);
	    add(scrollPane, BorderLayout.CENTER);
	    tree = new Visible(this, new File(samplesDir), 0);
	}
	
	public void addNavigationButton(String buttonText) {
		JButton button = new JButton(buttonText);
		button.addActionListener((ActionListener) controller);
		navigationBar.add(button);
	}
	
	public JToolBar createNavigationBar() {
		addNavigationButton("All");
		addNavigationButton("Used");
    	return navigationBar;
	}

	public void showAllSamples() {
		// TODO Auto-generated method stub
		
	}

	public void showUsedSamples() {
		// TODO Auto-generated method stub
	}
	
	public ArrayList<Visible> getVisibleFiles() {
		ArrayList<Visible> returnVal = new ArrayList<Visible>();
		returnVal.add(tree);
		returnVal.addAll(tree.getVisibleChildren());
		return returnVal;
	}
	
	public void updateView() {
		view.repaint();
	}
	
	public FileBrowserView getView() {
		return view;
	}

}
