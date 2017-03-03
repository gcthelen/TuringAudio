package main.editor;

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

public class ModuleEditor extends JPanel  {
	
	private MultiWindow parent;
	private ModuleEditorView view;
	private ModuleEditorController controller;
	private JToolBar navigationBar;
	
	public ModuleEditor(MultiWindow parent) {
		super(new BorderLayout());
		this.parent = parent;
	    view = new ModuleEditorView(this);
	    view.setBackground(Color.black);
	    controller = new ModuleEditorController(this);
	    navigationBar = new JToolBar();
	    add(createNavigationBar(), BorderLayout.PAGE_START);
	    view.addMouseListener(controller);
	    view.addMouseMotionListener(controller);
	    view.setPreferredSize(new Dimension(1500, 840));
	    JScrollPane scrollPane = new JScrollPane(view);
	    scrollPane.setSize(1500, 840);
	    add(scrollPane, BorderLayout.CENTER);
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

	public void updateView() {
		view.repaint();
	}

}
