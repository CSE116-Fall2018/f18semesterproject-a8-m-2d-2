package code.game.golf;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JLabel;

import code.cards.Card;
import code.cards.Deck;
import code.game.Game;
import code.game.gui.GUI;
import code.game.gui.control.EndGame;

/**
 * Instantiates the Golf game, create 7 Tableau piles, one Homecell
 * pile, and one Stockpile pile. The class has getter functions for each
 * of these piles, to be used the GUI to place them in the GUI.
 * 
 * @author Matt Ferrera
 */
public class Golf extends Game {
	
	/** Required when extending JComponents or something. */
	private static final long serialVersionUID = 1L;
	/** Homecell is the Homecell object where cards will be placed throughout the game. */
	private Homecell homecell;
	/** Stockpile is the game's Stockpile object. */
	private Stockpile stockpile;
	/** The amount of vertical offset per card per tableau. */
	private static final int Y_OFFSET = 25;
	/** The horizontal offset to the right of each tableau. */
	private static final int X_OFFSET = 110;

	/**
	 * Golf takes no parameters. It creates the deck object, shuffles it, creates 7 tableaus, the homecell pile,
	 * and then initializes the rest of the game.
	 */
	public Golf(GUI gui) {
		super(gui);
	}
	
	/**
	 * Initializes the Golf game, creating the tableaus with 5 cards each, as well as the Stockpile.
	 */
	protected void init() {
		Deck cards = new Deck(this);
		cards.shuffle();
		this.setTableauSelected(null);

		this.tableaus = new Tableau[7];
		
		// For each Tableau, add 5 Cards from the deck
		for (int i = 0; i < this.tableaus.length; i++) {
			Tableau t = new Tableau();
			
			// Set every card in the tableau face up before adding
			for (int j = 0; j < 5; j++) {
				Card c = cards.takeCard();
				c.flip();
				// Set the tableau # the card is a child of
				c.setTableauNum(i);
				t.addCard(c, true);
				
			}
			
			this.tableaus[i] = t;
		}
		
		// Give the stockpile the rest of the deck
		this.stockpile = new Stockpile(this, cards.getDeck());
		this.homecell = new Homecell(this);
		refresh();
		setMoves(0);
	}
	
	/**
	 * This method refreshes the game pane every time a change is made
	 * to keep the piles up to date. Should be called after every
	 * successful move.
	 */
	public void refresh() {
		if (gameWon()) {
			EndGame.win(this.gui, this);
			return;
		}
		
		removeAll();
		// Origin starting point to place cards
		Point pos = new Point(80, 20);

		int w = 100, h = 140;
		// Iterate through all 7 tableaus
		for(int i = 0; i < this.tableaus.length; i++) {
			ArrayList<Card> cards = this.tableaus[i].getAllCards();
			Integer depth = 0;
			
			// If tableau is empty, add null & skip the next tableau
			if (cards == null) {
				JLabel space = new JLabel();
				space.setBounds(pos.x, pos.y, w, h);
				this.add(space, depth, 0);
				pos.x += X_OFFSET;
				continue;
			}
			
			// Iterate through every card in the current tableau
			for(int j = cards.size(); j > 0; j--) {
				// Must be Integer object to denote layer in pane
				Card icon = cards.get(j - 1);
				// Set bounds of this JLabel at (x,y) to width & height of icon
				icon.setBounds(pos.x, pos.y, w, h);
				
				// Set the last card as the top card
				if (j == 1) {
					icon.setTop();
				}
				
				this.add(icon, depth++, 0);
				// Move down Y_OFFSET to stagger the cards in this tableau
				pos.y += Y_OFFSET;
			}
			depth = 0;
			// Reset y-coord for next Tableau, and move x-coord right
			pos.y = 20;
			pos.x += X_OFFSET;
		}
		
		// Add the stockpile and homecell pile
		if (this.getStockpile().getIcon() != null) {
				this.stockpile.setBounds(350, 300, w, h);
		}
		this.add(this.stockpile, Integer.valueOf(0), 0);	
		this.homecell.setBounds(470, 300, w, h);
		this.add(this.homecell, Integer.valueOf(0), 0);
		
		this.errorLabel.setBounds(325, 700, 300, 100);
		this.add(this.errorLabel, 0, 0);
		this.gui.getPanel().validate();
		this.gui.getPanel().repaint();
	}
	
	/**
	 * Returns the Homecell pile.
	 * 
	 * @return Homecell The homecell pile.
	 */
	public Homecell getHomecell() {
		return homecell;
	}
	
	/**
	 * Returns the Stockpile pile.
	 * 
	 * @return Stockpile The stockpile pile.
	 */
	public Stockpile getStockpile() {
		return stockpile;
	}
}
