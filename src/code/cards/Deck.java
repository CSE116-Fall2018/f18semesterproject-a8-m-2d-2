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


	@Override
	public void addCard(Card card) {
		// TODO Auto-generated method stub

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
