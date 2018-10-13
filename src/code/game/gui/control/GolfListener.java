package code.game.gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.game.gui.GUI;
import code.game.gui.GolfGUI;

public class GolfListener implements ActionListener {
	
	private GUI gui;
	
	public GolfListener(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GolfGUI game = new GolfGUI();
		gui.getPanel().removeAll();
		gui.getPanel().add(game);
		gui.getFrame().pack();
	}
}
