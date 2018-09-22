package code.cards;

import java.util.ArrayList;
import java.util.Collections;


public class Deck implements Pile{

	ArrayList<Card> deck = new ArrayList<>();

	public Deck () {
		initialize();
	}

	public void initialize() {
		for(int c = 0; c < 52; c++) {
			this.deck.add(new Card(c,false));
		}
	}

	public void shuffle(int RNG1, int RNG2) {
		Collections.shuffle(this.deck);
	}

	public ArrayList<Card> getDeck(){
		return this.deck;
	}

	public Card getSpecificCard(String suit, String rank) {
		for(Card c : deck) {
			if(c.getSuit().equals(suit) && c.getRank().equals(rank)) {
				deck.remove(c);
				return c;
			}
		}
		return null;
	}
	
	@Override
	public boolean addCard(Card card) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void removeCard() {
		this.deck.remove(0);
	}

	@Override
	public int getNumCards() {
		return this.deck.size();
	}

	@Override
	public Card getCard() {
		Card result = deck.get(0);
		return result;
	}

	@Override
	public Card takeCard() {
		Card result = deck.get(0);
		deck.remove(result);
		return result;
	}

}
