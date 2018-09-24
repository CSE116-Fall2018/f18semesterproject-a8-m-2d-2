package code.game.littlespider;

import java.util.ArrayList;

import code.cards.Card;
import code.cards.Pile;

public class Tableau implements Pile {

	private ArrayList<Card> cards;
	private Card topCard;
	
	public Tableau() {
		cards = new ArrayList<>();
	}
	
	@Override
	public boolean addCard(Card card) {
		
		
		int difference = Math.abs(card.getValue() - topCard.getValue());
		if(difference == 1) {
			cards.add(0, card);
			topCard = card;
			return true;
		}
		else if (difference == 12) {
			cards.add(0, card);
			topCard = card;
			return true;
		}
		return false;
	}

	@Override
	public void removeCard() {
		if(cards.size() == 0) {
			//cannot remove card from empty pile
		}else {
			cards.remove(0);
			topCard = cards.get(0);
		}
		
	}

	@Override
	public int getNumCards() {
		return cards.size();
	}

	@Override
	public Card getCard() {
		return topCard;
	}

	@Override
	public Card takeCard() {
		removeCard();
		if(cards.size() == 0) return null; //cannot remove card from empty pile
		return topCard;
	}
	
	/**
	 * Used to add the first 8 cards in tableau piles.  Has no regard for
	 * rank or suit.
	 * 
	 * @param card Card to be added.
	 */
	public void addFirstCard(Card card) {
		cards.add(0, card);
	}

	public ArrayList<Card> getCards() {
		return cards;
	}
	
	

}
