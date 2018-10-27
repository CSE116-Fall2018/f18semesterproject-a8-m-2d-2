package code.game.gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is just an ActionListener that exits the game when called.
 */
public class ExitListener implements ActionListener {

	/**
	 * Stops listening when player ends game
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
