package main;

public class TuringAudio {

	public static MultiWindow parent;
	
	private static void createAndShowGUI() {
		// Create and set up the window.
		parent = new MultiWindow();
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

}
