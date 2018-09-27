package code.game.golf;

import java.util.ArrayList;

import code.cards.Card;
import code.cards.Pile;

public class Tableau implements Pile {

	/**
	 * Cards is the ArrayList containing all Card objects in each Tableau pile.
	 */
	private ArrayList<Card> cards;

	/**
	 * The Tableau constructor initializes the ArrayList for the cards field.
	 */
	public Tableau() {
		this.cards = new ArrayList<>();
	}
	
	/**
	 * Adds Cards to the cards field, as long as canAdd is still true. Prints a message if a card isn't allowed to be added.
	 * 
	 * @param card The Card to be added.
	 */
	@Override
	public boolean addCard(Card card, boolean override) {
		if (override) {
			this.cards.add(0, card);
			return true;
		}
		
		System.out.println("Cannot add cards to this pile.");
		return false;
	}

	/**
	 * Returns an int of the number of cards still in the Tableau pile.
	 * 
	 * @return int number of cards in the Tableau.
	 */
	@Override
	public int getNumCards() {
		return this.cards.size();
	}

	/**
	 * Returns the Card at the top of the pile. Does not remove it from the pile.
	 * 
	 * @return Card at position 0 in the cards ArrayList.
	 */
	@Override
	public Card getCard() {
		if (getNumCards() == 0) {
			return null;
		}
		
		return this.cards.get(0);
	}

	/**
	 * Returns the card at the top of the pile and removes that card from the Tableau.
	 * 
	 * @return Card at the top of the pile.
	 */
	@Override
	public Card takeCard() {
		if (getNumCards() == 0) {
			return null;
		}
		
		Card card = getCard();
		this.cards.remove(0);
		return card;
	}

}
