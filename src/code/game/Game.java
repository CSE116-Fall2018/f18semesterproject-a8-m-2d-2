package code.game;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;

import code.game.gui.GUI;

public class Game extends JLayeredPane {

	/**
	 * Required when extending JComponents or something.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The GUI element reference passed from code.game.gui
	 */
	protected GUI gui;

	/**
	 * A superclass of the solitaire games that takes a GUI
	 * parameter. This class is used to pass to cards so that
	 * they can access the game.
	 * 
	 * @param GUI gui the game's GUI.
	 */
	public Game(GUI gui) {
		this.gui = gui;
		setPreferredSize(new Dimension(780, 500));
		setBackground(GUI.BG_COLOR);
		setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
	}
}
