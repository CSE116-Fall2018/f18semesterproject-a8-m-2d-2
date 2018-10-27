package code.game.gui;

import javax.swing.SwingUtilities;

/**
 * Main class that starts the game.  Main pulls the frame from the GUI class.
 *
 */
public class Main {
	
	/**
	 * Runs the game.
	 */
	public static void main(String[] args) {
		GUI gui = new GUI();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				gui.runGUI();
			}
		});
	}

}