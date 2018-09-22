package code.game.golf;

import java.util.ArrayList;

import code.cards.Card;
import code.cards.Pile;

public class Tableau implements Pile {

	private ArrayList<Card> cards;
	private boolean canAdd = true;

	public Tableau() {
		this.cards = new ArrayList<Card>();
	}
	
	/**
	 * Returns whether or not a card can be added to this pile.
	 * 
	 * @return boolean Whether a card can be added or not
	 */
	public boolean isCanAdd() {
		return canAdd;
	}

	/**
	 * Sets whether or not cards can be added to this pile.
	 * 
	 * @param canAdd True if cards can be added, false if not.
	 */
	public void setCanAdd(boolean canAdd) {
		this.canAdd = canAdd;
	}
	
	/**
	 * Adds Cards to the cards field, as long as canAdd is still true. Prints a message if a card isn't allowed to be added.
	 * 
	 * @param card The Card to be added.
	 */
	@Override
	public boolean addCard(Card card) {
		if (this.canAdd) {
			this.cards.add(card);
			return true;
		}
		
		System.out.println("Cannot add cards to this pile.");
		return false;
	}

	/**
	 * Removes the card at the top of the Tableau pile from the cards ArrayList.
	 */
	@Override
	public void removeCard() {
		this.cards.remove(0);
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
		return this.cards.get(0);
	}

	/**
	 * Returns the card at the top of the pile and removes that card from the Tableau.
	 * 
	 * @return Card at the top of the pile.
	 */
	@Override
	public Card takeCard() {
		Card card = getCard();
		this.removeCard();
		return card;
	}

}
