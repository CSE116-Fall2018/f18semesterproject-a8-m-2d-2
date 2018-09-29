package code.game.golf;

import java.util.ArrayList;

import code.cards.Card;
import code.cards.Pile;

public class Stockpile implements Pile {

	/**
	 * Cards is the ArrayList containing all Card objects in the Stockpile pile.
	 */
	private ArrayList<Card> cards;
	
	/**
	 * The Stockpile constructor takes all of the deck not disbursed to tableaus and sets it to the cards field.
	 * 
	 * @param remainingDeck
	 */
	public Stockpile(ArrayList<Card> remainingDeck) {
		cards = remainingDeck;
	}
	
	/**
	 * Attempts to add a card to the Stockpile, which is an illegal action in golf, so it returns false.
	 * 
	 * @param card Card to be added
	 * @return false Cards cannot be added to this pile in Golf.
	 */
	@Override
	public boolean addCard(Card card, boolean override) {
		System.out.println("Cannot add cards to this pile.");
		return false;
	}

	/**
	 * Returns an int of the remaining cards in the stockpile.
	 * 
	 * @return int Number of cards in the stockpile.
	 */
	@Override
	public int getNumCards() {
		return cards.size();
	}

	/**
	 * Returns the card at the top of the stockpile. Does not remove the card from the pile.
	 * 
	 * @return Card The card at index 0 of the Stockpile.
	 */
	@Override
	public Card getCard() {
		if (getNumCards() == 0) {
			return null;
		}
		
		return cards.get(0);
	}

	/**
	 * Attempts to take the card from the top of the Stockpile. Does not remove it unless certain conditions are met.
	 * 
	 * @return Card The card at the top of the Stockpile.
	 */
	@Override
	public Card takeCard() {
		if (getNumCards() == 0) {
			return null;
		}
		
		Card card = getCard();
		cards.remove(0);
		// Set new top card face up
		if (getNumCards() != 0) {
			Card newTopCard = cards.get(0);
			newTopCard.setFaceUp();
		}
		
		return card;
	}
}
