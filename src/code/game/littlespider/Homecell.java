package code.game.littlespider;

import java.util.ArrayList;

import code.cards.Card;
import code.cards.Pile;

public class Homecell implements Pile {

	private ArrayList<Card> cards;
	private Card topCard;
	
	/**
	 * Constructor for Homecell. Adds @param as topCard and adds card to cards list.
	 * 
	 * @param firstCard Card that homecell pile starts faceUp with
	 */
	
	public Homecell(Card firstCard) {
		cards.add(firstCard);
		topCard = firstCard;
	}
	
	@Override
	public void addCard(Card card) {
		int difference = Math.abs(card.getValue() - topCard.getValue());
		if(difference == 1) {
			cards.add(0, card);
			topCard = card;
		}
	}

	@Override
	public void removeCard() {
		if(cards.size() == 1) {
			//error - cannot take last card
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
		return topCard;
	}

}
