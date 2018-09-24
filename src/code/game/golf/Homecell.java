package code.game.golf;

import java.util.ArrayList;

import code.cards.Card;
import code.cards.Pile;

public class Homecell implements Pile {

	private ArrayList<Card> cards;
	
	public Homecell() {
		this.cards = new ArrayList<Card>();
	}
	
	/**
	 * Adds a card to the pile iff the homecell is empty, or if the rank of the card
	 * at the top of the Homecell differs by 1 from the top card in the pile.
	 * 
	 * @param card The card to be added.
	 */
	@Override
	public boolean addCard(Card card) {
		// No cards are currently in the Homecell
		if (this.cards.size() == 0) {
			this.cards.add(card);
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
	 * Adds a card to the pile if it's from the Stockpile without checking or complaint, as long as the first parameter is true.
	 * 
	 * @param fromStockpile boolean value denoting whether it's from stockpile
	 * @param card The card to be added.
	 */
	public boolean addCard(boolean fromStockpile, Card card) {
		if (fromStockpile == false) {
			return false;
		}
		
		this.cards.add(0, card);
		return true;
	}
	
	
	/**
	 * In the future, will disallow cards to be removed from the pile.
	 */
	@Override
	public void removeCard() {
		// TODO Placeholder functionality until GUI
		System.out.println("Cannot remove cards from the Homecell pile.");
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
		// TODO Handle this properly later when GUI is involved.
		removeCard();
		return null;
	}
}
