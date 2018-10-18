package code.game;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;

import code.cards.Pile;
import code.game.gui.GUI;

public abstract class Game extends JLayeredPane {

	/**
	 * Required when extending JComponents or something.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The GUI element reference passed from code.game.gui
	 */
	protected GUI gui;
	/**
	 * The card that is currently selected
	 */
	private Pile tableauSelected;
	
	protected Pile[] tableaus;

	/**
	 * A superclass of the solitaire games that takes a GUI
	 * parameter. This class is used to pass to cards so that
	 * they can access the game.
	 * 
	 * @param GUI gui the game's GUI.
	 */
	public Game(GUI gui) {
		this.gui = gui;
		this.tableauSelected = null;
		setPreferredSize(new Dimension(780, 500));
		setBackground(gui.getColor());
		setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
	}
	
	/**
	 * Returns whether or not a card is currently selected by the player.
	 * 
	 * @return boolean whether or not a card is selected
	 */
	public boolean isTableauSelected() {
		return tableauSelected != null;
	}
	
	public Pile tableauSelected() {
		return this.tableauSelected;
	}
	
	public Pile[] getTableaus() {
		return this.tableaus;
	}

	/**
	 * Sets whether or not a card is currently selected in the game.
	 * 
	 * @param cardSelected set with true or false
	 */
	public void setTableauSelected(Pile tableau) {
		this.tableauSelected = tableau;
	} 
	
	public abstract void refresh();
}
