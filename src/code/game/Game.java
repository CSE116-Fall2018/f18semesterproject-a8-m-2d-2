package code.game;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;

import code.cards.Card;
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
	private Card cardSelected;

	/**
	 * A superclass of the solitaire games that takes a GUI
	 * parameter. This class is used to pass to cards so that
	 * they can access the game.
	 * 
	 * @param GUI gui the game's GUI.
	 */
	public Game(GUI gui) {
		this.gui = gui;
		this.cardSelected = null;
		setPreferredSize(new Dimension(780, 500));
		setBackground(GUI.BG_COLOR);
		setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
	}
	
	/**
	 * Returns whether or not a card is currently selected by the player.
	 * 
	 * @return boolean whether or not a card is selected
	 */
	public boolean isCardSelected() {
		return cardSelected != null;
	}
	
	public Card cardSelected() {
		return this.cardSelected;
	}

	/**
	 * Sets whether or not a card is currently selected in the game.
	 * 
	 * @param cardSelected set with true or false
	 */
	public void setCardSelected(Card card) {
		this.cardSelected = card;
	} 
	public abstract void refresh();
}
