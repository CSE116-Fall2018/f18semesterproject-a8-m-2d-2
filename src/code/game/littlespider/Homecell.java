package code.game.littlespider;

import java.util.ArrayList;

import code.cards.Card;
import code.cards.Pile;

public class Homecell implements Pile {

	private ArrayList<Card> cards;
	private Card topCard;
	
	public Homecell(Card firstCard) {
		cards.add(firstCard);
		topCard = firstCard;
	}
	
	@Override
	public void addCard(Card card) {
		
	}

	@Override
	public void removeCard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getNumCards() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Card getCard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Card takeCard() {
		// TODO Auto-generated method stub
		return null;
	}

}
