package code.game.golf;

import java.util.ArrayList;

import code.cards.Card;
import code.cards.Pile;

public class Homecell implements Pile {

	/**
	 * Cards is the ArrayList containing all Card objects in the Homecell pile.
	 */
	private ArrayList<Card> cards;
	
	/**
	 * Homecell initializes the ArrayList for the cards fields, which is empty.
	 */
	public Homecell() {
		this.cards = new ArrayList<>();
	}
	
	/**
	 * Adds a card to the pile iff the homecell is empty, or if the rank of the card
	 * at the top of the Homecell differs by 1 from the top card in the pile.
	 * 
	 * @param card The card to be added.
	 */
	@Override
	public boolean addCard(Card card, boolean override) {
		// Do not allow adding null cards
		if (card == null) {
			return false;
		}
		
		// No cards are currently in the Homecell
		if (this.cards.size() == 0 || override) {
			this.cards.add(0, card);
			return true;
		}
		
		int difference = Math.abs(card.getValue() - this.getCard().getValue());
		
		if (difference == 1) {
			this.cards.add(0, card);
			return true;
		}
		
		return false;
	}

	/**
	 * Returns an int of the number of cards in the Homecell pile.
	 * 
	 * @return int number of cards in the Homecell pile.
	 */
	@Override
	public int getNumCards() {
		return this.cards.size();
	}

	/**
	 * Returns the face-up card at the top of the Homecell pile.
	 * 
	 * @return Card at the top of the Homecell pile.
	 */
	@Override
	public Card getCard() {
		if (getNumCards() == 0) {
			return null;
		}
		
		return this.cards.get(0);
	}

	/**
	 * In the future, will disallow cards to be removed from the pile.
	 * 
	 * @return Card returns null, as this is not a legal operation.
	 */
	@Override
	public Card takeCard() {
		// TODO Placeholder functionality until GUI
		System.out.println("Cannot remove cards from the Homecell pile.");
		return null;
	}
}
