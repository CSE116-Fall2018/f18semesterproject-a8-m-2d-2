package code.game.golf;

import code.cards.Card;
import code.cards.Deck;

public class Golf {
	
	private Deck cards;
	private Tableau[] tableaus;
	private Homecell homecell;
	private Stockpile stockpile;

	public Golf() {
		this.cards = new Deck();
		this.tableaus = new Tableau[7];
		this.homecell = new Homecell();
		this.stockpile = new Stockpile(this.cards.getDeck());
		init();
	}
	
	/**
	 * Initializes the Golf game, creating the tableaus, Homecell, and Stockpile.
	 */
	private void init() {
		// for each tableau, add 5 cards from the deck
		for (int i = 0; i < this.tableaus.length; i++) {
			Tableau t = new Tableau();
			
			for (int j = 0; j < 5; j++) {
				Card c = this.cards.getCard();
				c.setFaceUp();
				t.addCard(c);
			}
			// disallows adding anymore cards to the current tableau after it receives its initial 5
			t.setCanAdd(false);
			this.tableaus[i] = t;
		}
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
