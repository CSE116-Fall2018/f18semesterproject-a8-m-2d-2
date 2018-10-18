package code.game.gui.control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.game.gui.GUI;

public class ColorControl implements ActionListener {
	
	private Color color;
	private GUI gui;

	public ColorControl(Color c, GUI gu) {
		color = c;
		gui = gu;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		gui.getPanel().setBackground(color);
		gui.setColor(color);
	}

}
