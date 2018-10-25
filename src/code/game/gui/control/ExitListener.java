package code.game.gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitListener implements ActionListener{

	/**
	 * Stops listening when player ends game
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
	

}
