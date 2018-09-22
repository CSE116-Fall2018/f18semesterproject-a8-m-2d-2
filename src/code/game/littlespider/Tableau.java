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
		//groups diamonds and hearts per rules of the game
		if(card.getSuit().equals("diamond") || card.getSuit().equals("heart")) {
			//checks if suits match and input card is one above
			if(card.getSuit().equals(topCard.getSuit()) && card.getValue() == topCard.getValue()+1) {
				
				cards.add(0,card);
				topCard = card;
			}else {
				//cannot add card
			}
		}
		//groups spades and clubs per rules of the game
		if(card.getSuit().equals("spade") || card.getSuit().equals("club")) {
			//checks if suits match and input card is one below
			if(card.getSuit().equals(topCard.getSuit()) && card.getValue() == topCard.getValue()-1) {
				cards.add(0,card);
				topCard = card;
			}else {
				//cannot add card
			}
		}
		
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

}
