package code.game.golf;

import java.awt.Dimension;
import java.awt.Point;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import code.cards.Card;
import code.cards.Deck;
import code.game.gui.GUI;
import code.game.gui.HomecellGUI;

/**
 * Instantiates the Golf game, create 7 Tableau piles, one Homecell
 * pile, and one Stockpile pile. The class has getter functions for each
 * of these piles, to be used the GUI to place them in the GUI.
 * 
 * @author Matt Ferrera
 */
@SuppressWarnings("serial")
public class Golf extends JLayeredPane {
	
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
	 * The GUI homecell pile.
	 */
	private JLabel homecellIcon;
	/**
	 * The GUI stockpile.
	 */
	private Card stockpileIcon;
	/**
	 * The amount of vertical offset per card per tableau.
	 */
	private static final int Y_OFFSET = 25;
	/**
	 * The horizontal offset to the right of each tableau.
	 */
	private static final int X_OFFSET = 110;
	private boolean cardSelected;

	/**
	 * Golf takes no parameters. It creates the deck object, shuffles it, creates 7 tableaus, the homecell pile,
	 * and then initializes the rest of the game.
	 */
	public Golf() {
		tableaus = new Tableau[7];
		homecell = new Homecell();
		setPreferredSize(new Dimension(780, 500));
		setBackground(GUI.BG_COLOR);
		setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
		setCardSelected(false);
		init();
	}
	
	/**
	 * Initializes the Golf game, creating the tableaus with 5 cards each, as well as the Stockpile.
	 */
	private void init() {
		Deck cards = new Deck();
		cards.shuffle();
		// For each Tableau, add 5 Cards from the deck
		for (int i = 0; i < this.tableaus.length; i++) {
			Tableau t = new Tableau();
			
			for (int j = 0; j < 5; j++) {
				Card c = cards.takeCard();
				c.setFaceUp();
				t.addCard(c, true);
			}
			
			this.tableaus[i] = t;
		}
		
		this.stockpile = new Stockpile(cards.getDeck());
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
		
		// Set the homecell icon
		if (this.homecellIcon == null) {
			// The default homecell image when no cards have been placed
			URL path = getClass().getResource("/e.png"); // empty.png
			this.homecellIcon = new HomecellGUI(path);
		} else {
			this.homecellIcon = this.getHomecell().getCard();
		}
		
		this.stockpileIcon = this.getStockpile().getCard();
		
		// Iterate through all 7 tableaus
		for(int i = 0; i < this.tableaus.length; i++) {
			ArrayList<Card> cards = tableaus[i].getAllCards();
			// Iterate through every card in the current tableau
			for(int j = 0; j < cards.size(); j++) {
				// Must be Integer object to denote layer in pane
				Integer depth = j;
				Card icon = cards.get(j);
				// Set bounds of this JLabel at (x,y) to width & height of icon
				icon.setBounds(pos.x, pos.y, 
						icon.getIcon().getIconWidth(), 
						icon.getIcon().getIconHeight());
				
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
		this.stockpileIcon.setBounds(280, 300, 
				this.stockpileIcon.getIcon().getIconWidth(), 
				this.stockpileIcon.getIcon().getIconHeight());
		this.add(this.stockpileIcon, Integer.valueOf(0), 0);	
		this.homecellIcon.setBounds(400, 300, 
				this.homecellIcon.getIcon().getIconWidth(), 
				this.homecellIcon.getIcon().getIconHeight());
		this.add(this.homecellIcon, Integer.valueOf(0), 0);
	}

	public boolean isCardSelected() {
		return cardSelected;
	}

	public void setCardSelected(boolean cardSelected) {
		this.cardSelected = cardSelected;
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
}
