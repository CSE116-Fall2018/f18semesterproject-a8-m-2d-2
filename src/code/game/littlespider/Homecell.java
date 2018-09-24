package code.game.littlespider;

import java.util.ArrayList;

import code.cards.Card;
import code.cards.Pile;

public class Homecell implements Pile {

	private ArrayList<Card> cards;
	private Card topCard;
	
	/**
	 * Constructor for LittleSpider Homecell. Adds @param as topCard and adds card to cards list.
	 * 
	 * @param card Card that homecell pile starts faceUp with
	 */
	
	public Homecell(Card card) {
		cards = new ArrayList<>();
		cards.add(card);
		topCard = card;
	}
	/**
	 * Takes a card and adds it to the top of the pile if and only if it 
	 * is within one rank of the current top card.
	 * 
	 * @param card Card to be put on the top of the pile.
	 */
	@Override
	public boolean addCard(Card card) {
		//groups diamonds and hearts per rules of the game
				if(card.getSuit().equals("diamond") || card.getSuit().equals("heart")) {
					//checks if suits match and input card is one above
					if(card.getSuit().equals(topCard.getSuit()) && card.getValue() == topCard.getValue()+1) {
						
						cards.add(0,card);
						topCard = card;
						return true;
					}else {
						//cannot add card
						return false;
					}
					
					
					
					
				}
				//groups spades and clubs per rules of the game
				if(card.getSuit().equals("spade") || card.getSuit().equals("club")) {
					//checks if suits match and input card is one below
					if(card.getSuit().equals(topCard.getSuit()) && card.getValue() == topCard.getValue()-1) {
						cards.add(0,card);
						topCard = card;
						return true;
					}else {
						//cannot add card
						return false;
					}
				}
				return false;
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
		Card top = topCard;
		if(cards.size() == 1) return null;
		
		cards.remove(0);
		topCard = cards.get(0);
		
		return top;
	}

	public String toString() {
		String r = "";
		for(Card c : cards) {
			r = r + ", " + c;
		}
		return r;
	}
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	
}
