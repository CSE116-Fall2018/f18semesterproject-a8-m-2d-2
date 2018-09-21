package code.game.golf;

import java.util.ArrayList;

import code.cards.Card;
import code.cards.Pile;

public class Homecell implements Pile {

	private ArrayList<Card> cards;
	
	/**
	 * Adds a card to the pile iff its rank differs by 1 from the top card in the pile.
	 * 
	 * @param card The card to be added.
	 */
	@Override
	public void addCard(Card card) {
		int rankGap = card.getValue() - getCard().getValue();
		
		if (Math.abs(rankGap) == 1) {
			cards.add(0, card);
		}
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
		return cards.size();
	}

	/**
	 * Returns the face-up card at the top of the Homecell pile.
	 * 
	 * @return Card at the top of the Homecell pile.
	 */
	@Override
	public Card getCard() {
		return cards.get(0);
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
