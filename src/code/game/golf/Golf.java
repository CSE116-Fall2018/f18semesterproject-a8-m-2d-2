package code.game.golf;

import code.cards.Card;
import code.cards.Deck;

public class Golf {
	
	Deck cards;
	Tableau[] tableaus;
	Homecell homecell;
	Stockpile stockpile;
	boolean started = false;

	public Golf() {
		cards = new Deck();
		init();
	}
	
	/**
	 * Initializes the Golf game, creating the tableaus, Homecell, and Stockpile.
	 */
	private void init() {
		tableaus = new Tableau[7];
		
		// for each tableau, add 5 cards from the deck
		for (int i = 0; i < tableaus.length; i++) {
			for (int j = 0; j < 5; j++) {
				tableaus[i].addCard(cards.getCard().setFaceUp());
			}
			// disallows adding anymore cards to the current tableau after it 
			// receives its initial 5
			tableaus[i].canAdd = false;
		}
		
		homecell = new Homecell();
		stockpile = new Stockpile(cards.deck);
	}
}
