package code.game.gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import code.game.gui.GUI;
import code.game.gui.GolfGUI;

public class GolfListener implements ActionListener {
	
	private GUI gui;
	
	public GolfListener(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Start little spider from code.game.GUI little spider
		GolfGUI game = new GolfGUI();
		JPanel panel = gui.getPanel();
		panel.removeAll();
		panel.add(game.getGame());
		JFrame frame = gui.getFrame();
		frame.pack();
	}

}
