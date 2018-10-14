package code.game.gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.game.golf.Golf;
import code.game.gui.GUI;

public class GolfListener implements ActionListener {
	
	private GUI gui;
	
	public GolfListener(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Golf game = new Golf();
		gui.getPanel().removeAll();
		gui.getPanel().add(game);
		gui.getFrame().pack();
	}
}
