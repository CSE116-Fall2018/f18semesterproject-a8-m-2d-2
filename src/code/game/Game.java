package code.game;

import java.awt.Dimension;

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
	 * The card/tableau that is currently selected
	 */
	private Pile tableauSelected;
	/**
	 * The tableaus in the game. Set by the Game subclass.
	 */
	protected Pile[] tableaus;
	/**
	 * The card/homecell that is currently selected
	 */
	private Pile homecellSelected;
	/**
	 * The homecell in the game. Set by the Game subclass.
	 */
	protected Pile[] homecells;


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
		this.homecellSelected = null;
		setPreferredSize(new Dimension(GUI.WIN_WIDTH, GUI.WIN_HEIGHT));
		setBackground(gui.getColor());
	}
	
	/**
	 * Returns whether or not a card is currently selected by the player.
	 * 
	 * @return boolean whether or not a tableau card is selected
	 */
	public boolean isTableauSelected() {
		return tableauSelected != null;
	}
	
	/**
	 * Returns the Tableau pile that is currently selected.
	 * 
	 * @return Pile the currently selected tableau pile.
	 */
	public Pile tableauSelected() {
		return this.tableauSelected;
	}
	
	/**
	 * Returns all of the tableaus in the game.
	 * 
	 * @return Pile[] the tableaus in the game.
	 */
	public Pile[] getTableaus() {
		return this.tableaus;
	}

	/**
	 * Returns all of the homecells in the game.
	 * 
	 * @return Pile[] the homecells in the game.
	 */
	public Pile[] getHomecells() {
		return this.homecells;
	}
	/**
	 * Sets whether or not a card is currently selected in the game.
	 * 
	 * @param cardSelected set with true or false
	 */
	public void setTableauSelected(Pile tableau) {
		this.tableauSelected = tableau;
	} 
	/**
	 * Sets whether or not a card is currently selected in the game.
	 * 
	 * @param cardSelected set with true or false
	 */
	public void setHomecellSelected(Pile homecell) {
		this.homecellSelected = homecell;
	} 
	/**
	 * Returns whether or not a card is currently selected by the player.
	 * 
	 * @return boolean whether or not a homecell card is selected
	 */
	public boolean isHomecellSelected() {
		return homecellSelected != null;
	}
	
	/**
	 * Returns the homecell pile that is currently selected.
	 * 
	 * @return Pile the currently selected homecell pile.
	 */
	public Pile homecellSelected() {
		return this.homecellSelected;
	}
	
	/** Refreshes all components placed on the GUI. */
	public abstract void refresh();
}
