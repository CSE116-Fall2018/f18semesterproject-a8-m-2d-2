package code.game.golf;

import java.util.ArrayList;

import code.cards.Card;
import code.cards.Pile;

public class Tableau implements Pile {

	private ArrayList<Card> cards;
	boolean canAdd = true;
	
	public Tableau() {
		// TODO Write Tableau constructor
	}
	
	/**
	 * Adds Cards to the cards field, as long as canAdd is still true. Prints a message if a card isn't allowed to be added.
	 * 
	 * @param card The Card to be added.
	 */
	@Override
	public void addCard(Card card) {
		if (!canAdd) {
			// TODO Placeholder functionality until GUI
			System.out.println("Cannot add cards to this pile.");
		}
		
		cards.add(card);
	}

	/**
	 * Removes the card at the top of the Tableau pile from the cards ArrayList.
	 */
	@Override
	public void removeCard() {
		cards.remove(0);
	}

	/**
	 * Returns an int of the number of cards still in the Tableau pile.
	 * 
	 * @return int number of cards in the Tableau.
	 */
	@Override
	public int getNumCards() {
		return cards.size();
	}

	/**
	 * Returns the Card at the top of the pile. Does not remove it from the pile.
	 * 
	 * @return Card at position 0 in the cards ArrayList.
	 */
	@Override
	public Card getCard() {
		return cards.get(0);
	}

	/**
	 * Returns the card at the top of the pile and removes that card from the Tableau.
	 * 
	 * @return Card at the top of the pile.
	 */
	@Override
	public Card takeCard() {
		Card c = getCard();
		removeCard();
		return c;
	}

}
