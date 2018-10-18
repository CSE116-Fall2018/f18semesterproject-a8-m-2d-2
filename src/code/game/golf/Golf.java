package code.game.golf;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import code.cards.Card;
import code.cards.Deck;
import code.game.Game;
import code.game.gui.GUI;

/**
 * Instantiates the Golf game, create 7 Tableau piles, one Homecell
 * pile, and one Stockpile pile. The class has getter functions for each
 * of these piles, to be used the GUI to place them in the GUI.
 * 
 * @author Matt Ferrera
 */
public class Golf extends Game implements ActionListener {
	
	/**
	 * Required when extending JComponents or something.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Tableaus is an array containing all 7 Tableaus for the game.
	 */
	private Tableau[] tableaus;
	/**
	 * Homecell is the Homecell object where cards will be placed throughout the game.
	 */
	private Homecell homecell;
	/**
	 * Stockpile is the game's Stockpile object.
	 */
	private Stockpile stockpile;
	/**
	 * The amount of vertical offset per card per tableau.
	 */
	private static final int Y_OFFSET = 25;
	/**
	 * The horizontal offset to the right of each tableau.
	 */
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
	private void init() {
		Deck cards = new Deck(null);
		cards.shuffle();
		
		this.tableaus = new Tableau[7];
		
		// For each Tableau, add 5 Cards from the deck
		for (int i = 0; i < this.tableaus.length; i++) {
			Tableau t = new Tableau();
			
			// Set every card in the tableau face up before adding
			for (int j = 0; j < 5; j++) {
				Card c = cards.takeCard();
				c.setFaceUp();
				t.addCard(c, true);
			}
			
			this.tableaus[i] = t;
		}
		
		// Give the stockpile the rest of the deck
		this.stockpile = new Stockpile(this, cards.getDeck());
		this.homecell = new Homecell();
		refresh();
	}
	
	/**
	 * This method refreshes the game pane every time a change is made
	 * to keep the piles up to date. Should be called after every
	 * successful move.
	 */
	public void refresh() {
		// Origin starting point to place cards
		Point pos = new Point(10, 20);
		
		// Iterate through all 7 tableaus
		for(int i = 0; i < this.tableaus.length; i++) {
			ArrayList<Card> cards = this.tableaus[i].getAllCards();
			// Iterate through every card in the current tableau
			for(int j = 0; j < cards.size(); j++) {
				// Must be Integer object to denote layer in pane
				Integer depth = j;
				Card icon = cards.get(j);
				// Set bounds of this JLabel at (x,y) to width & height of icon
				icon.setBounds(pos.x, pos.y, 
						icon.getIcon().getIconWidth(), 
						icon.getIcon().getIconHeight());
				
				// Set the last card as the top card
				if (j == cards.size() - 1) {
					icon.setTop();
				}
				
				this.add(icon, depth, 0);
				// Move down Y_OFFSET to stagger the cards in this tableau
				pos.y += Y_OFFSET;
			}
			// Reset y-coord for next Tableau, and move x-coord right
			pos.y = 20;
			pos.x += X_OFFSET;
		}
		
		// Add the stockpile and homecell pile
		this.stockpile.setBounds(280, 300, 
				this.stockpile.getIcon().getIconWidth(), 
				this.stockpile.getIcon().getIconHeight());
		this.add(this.stockpile, Integer.valueOf(0), 0);	
		this.homecell.setBounds(400, 300, 
				this.homecell.getIcon().getIconWidth(), 
				this.homecell.getIcon().getIconHeight());
		this.add(this.homecell, Integer.valueOf(0), 0);
	}
	
	/**
	 * Returns an array of the tableaus in the game.
	 * 
	 * @return Tableau[] The array of tableaus in the game.
	 */
	public Tableau[] getTableaus() {
		return tableaus;
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

	/**
	 * Initializes the game and then places itself
	 * on to the GUI frame after clearing it.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		removeAll();
		init();
		this.gui.getPanel().removeAll();
		this.gui.getPanel().add(this);
		this.gui.getFrame().pack();	
	}
}
