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
	/**
	 * Takes a card and adds it to the top of the pile if and only if it 
	 * is within one rank of the current top card.
	 * 
	 * @param card Card to be put on the top of the pile.
	 */
	@Override
	public void addCard(Card card) {
		int difference = Math.abs(card.getValue() - topCard.getValue());
		if(difference == 1) {
			cards.add(0, card);
			topCard = card;
		}
	}

	/**
	 * Removes top card if it is not the last card in the pile.
	 */
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
	/**
	 * Removes the top card and returns the top card for use by another method.
	 * If its the last card, takeCard() returns null as this is an invalid move.
	 */
	@Override
	public Card takeCard() {
		removeCard();
		if(cards.size() == 1) return null;
		else return topCard;
	}

}
