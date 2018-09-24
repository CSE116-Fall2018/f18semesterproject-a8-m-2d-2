package code.game.golf;

import code.cards.Card;
import code.cards.Deck;

public class Golf {
	
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
	 * Golf takes no parameters. It creates the deck object, shuffles it, creates 7 tableaus, the homecell pile,
	 * and then initializes the rest of the game.
	 */
	public Golf() {
		this.tableaus = new Tableau[7];
		this.homecell = new Homecell();
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
				t.addCard(c);
			}
			
			// Disallows adding anymore cards to the current tableau after it receives its initial 5
			t.setCanAdd(false);
			this.tableaus[i] = t;
		}
		
		// TODO Improve this so it removes constituent items from the Deck object
		this.stockpile = new Stockpile(cards.getDeck());
	}
	
	/**
	 * Returns an array of the tableaus in the game.
	 * 
	 * @return Tableau[] The array of tableaus in the game.
	 */
	public Tableau[] getTableaus() {
		return this.tableaus;
	}
	
	/**
	 * Returns the Homecell pile.
	 * 
	 * @return Homecell The homecell pile.
	 */
	public Homecell getHomecell() {
		return this.homecell;
	}
	
	/**
	 * Returns the Stockpile pile.
	 * 
	 * @return Stockpile The stockpile pile.
	 */
	public Stockpile getStockpile() {
		return this.stockpile;
	}
}
