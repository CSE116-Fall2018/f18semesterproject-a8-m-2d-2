package code.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import code.cards.Pile;
import code.game.gui.GUI;

/**
 * This class is the main game subclass, containing methods that
 * should stay consistent among all Solitaire games. A GUI instance
 * must be passed to it to instantiate the game.
 * 
 * @author Matt Ferrera
 * @author Drew Fiutko
 *
 */
public abstract class Game extends JLayeredPane implements ActionListener {

	/** Required when extending JComponents or something. */
	private static final long serialVersionUID = 1L;
	/** The GUI element reference passed from code.game.gui */
	protected GUI gui;
	/** The card/tableau that is currently selected */
	private Pile tableauSelected;
	/** The tableaus in the game. Set by the Game subclass. */
	protected Pile[] tableaus;
	/**  The card/homecell that is currently selected */
	private Pile homecellSelected;
	/** The homecell in the game. Set by the Game subclass. */
	protected Pile[] homecells;
	/**  The card/wastePile that is currently selected */
	protected Pile waste;
	/** Tracks the number of total legal moves in the current game. */
	private int moves;
	/** The JLabel that displays error messages on the GUI */
	protected JLabel errorLabel;


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
		this.errorLabel = new JLabel();
		this.errorLabel.setFont(new Font("Arial", Font.PLAIN, 50));
		this.errorLabel.setForeground(Color.RED);
	}
	
	/** 
	 * Initializes the game, shuffles a deck, and adds cards
	 * to the appropriate piles. Calls refresh() when done.
	 */
	protected abstract void init();
	
	/** 
	 * Initializes and/or refreshes all components placed on the GUI. 
	 */
	public abstract void refresh();
	
	/**
	 * This method is called on every refresh. It iterates
	 * through every tableau and returns if they are all empty
	 * or not. If they are all empty, the game has been won.
	 * 
	 * @return boolean whether or not the game has been won
	 */
	protected boolean gameWon() {
		for (Pile p : tableaus) {
			if (p.getNumCards() != 0) {
				return false;
			}
		}
		return true;
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
	/**
	 * Returns whether or not a card is currently selected by the player.
	 * 
	 * @return boolean whether or not a wastePile card is selected
	 */
	public void setWasteSelected(Pile waste) {
		this.waste = waste;
	} 
	
	/**
	 * Returns whether or not a card is currently selected by the player.
	 * 
	 * @return boolean whether or not a waste card is selected
	 */
	public boolean isWasteSelected() {
		return waste != null;
	}
	/**
	 * Returns the wastePile pile that is currently selected.
	 * 
	 * @return Pile the currently selected wastePile.
	 */
	public Pile wasteSelected() {
		return this.waste;
	}

	
	/**
	 * Returns the number of moves that have been made
	 * in the game so far.
	 * 
	 * @return int the number of moves so far
	 */
	public int getMoves() {
		return moves;
	}

	/**
	 * Sets the number of moves that have been made 
	 * in the game so far.
	 * 
	 * @param moves the number of moves so far
	 */
	public void setMoves(int moves) {
		this.moves = moves;
	}

	/**
	 * Sets the error text JLabel of the game to display
	 * to the user. Defaults to "Illegal move"
	 */
	public void setErrorText() {
		this.errorLabel.setText("Illegal move");
	}
	
	/**
	 * Sets the error text JLabel to blank. This is called
	 * when a legal move has been made to clear out any
	 * illegal move text still present on the GUI.
	 */
	public void setBlankErrorText() {
		this.errorLabel.setText("");
	}
	
	/**
	 * Initializes the game and then places itself
	 * on to the GUI frame after clearing it.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		init();
		this.gui.setPanel(this);
	}
}
