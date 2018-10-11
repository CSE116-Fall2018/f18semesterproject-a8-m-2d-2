package code.game.gui.control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import code.game.gui.GUI;
import code.game.gui.LittleSpider;

public class LittleSpiderListener implements ActionListener{

	private GUI gui;
	
	public LittleSpiderListener(GUI gui) {
		this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Start little spider from code.game.GUI little spider
		LittleSpider game = new LittleSpider();
		JPanel panel = gui.getPanel();
		panel.removeAll();
		panel.add(game.getGame());
		JFrame frame = gui.getFrame();
		frame.pack();
	}

}
