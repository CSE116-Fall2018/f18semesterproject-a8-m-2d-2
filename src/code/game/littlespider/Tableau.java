package code.game.littlespider;

import java.util.ArrayList;

import code.cards.Card;
import code.cards.Pile;

public class Tableau implements Pile {

	/**
	 * Holds all the cards in the pile in an ArrayList.
	 */
	private ArrayList<Card> cards;
	
	/**
	 * Holds the top card for use in the class.
	 */
	private Card topCard;
	
	/**
	 * Instatiats the ArrayList of cards in the pile.
	 */
	public Tableau() {
		cards = new ArrayList<>();
	}
	
	/**
	 * Adds card if and only if it complies with the rules of the game.
	 * 
	 */
	@Override
	public boolean addCard(Card card) {
		
		if(cards.size() == 0) return false;
		
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

	/**
	 * Returns the number of cards in the pile.
	 */
	@Override
	public int getNumCards() {
		return cards.size();
	}
	 
	/**
	 * Returns the top card in the pile.
	 */
	@Override
	public Card getCard() {
		return topCard;
	}

	/**
	 * Returns the card being removed for use by other methods.
	 * Removes that card from the pile.
	 */
	@Override
	public Card takeCard() {
		Card top = topCard;
		if(cards.size() == 0) return null;
		
		cards.remove(0);
		topCard = cards.get(0);
			
		return top;
	}
	
	/**
	 * Used to add the first 6 cards in tableau piles.  Has no regard for
	 * rank or suit.
	 * 
	 * @param card Card to be added.
	 */
	public void addFirstCard(Card card) {
		cards.add(0, card);
		topCard = card;
	}

	
	/**
	 * Returns the ArrayList of cards currently in the pile.
	 * @return
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	

}
