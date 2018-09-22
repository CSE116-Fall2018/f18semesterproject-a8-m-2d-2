package code.game.golf;

import java.util.ArrayList;

import code.cards.Card;
import code.cards.Pile;

public class Stockpile implements Pile {

	private ArrayList<Card> cards;
	
	public Stockpile(ArrayList<Card> remainingDeck) {
		this.cards = remainingDeck;
	}
	
	@Override
	public boolean addCard(Card card) {
		return false;
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
