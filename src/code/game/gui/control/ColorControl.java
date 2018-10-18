package code.game.gui.control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.game.gui.GUI;
/**
 * Used to set the color of the background of the game for a 
 * specific color.
 *
 */
public class ColorControl implements ActionListener {
	/**
	 * Color to set to the background.
	 */
	private Color color;
	/**
	 * GUI instance for the game.
	 */
	private GUI gui;
	/**
	 * Constructor to take the color and the GUI instance.
	 * @param c Color for the background.
	 * @param gu GUI for the game.
	 */
	public ColorControl(Color c, GUI gu) {
		color = c;
		gui = gu;
	}
	/**
	 * When the menuItem is clicked, the background update to the given color.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		gui.getPanel().setBackground(color);
		gui.setColor(color);
	}

}
