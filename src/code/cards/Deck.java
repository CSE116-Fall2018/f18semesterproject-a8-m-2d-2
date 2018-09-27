package code.cards;

import java.util.ArrayList;
import java.util.Collections;


public class Deck implements Pile{

	/**
	 * @Field deck - the Collection of all cards in the deck.
	 */
	ArrayList<Card> deck = new ArrayList<>();

	/**
	 * Constructor for Deck that takes no parameters and calls initialize().
	 */
	public Deck () {
		initialize();
	}

	/**
	 * method that initializes 52 cards, all face down.
	 */
	public void initialize() {
		for(int c = 0; c < 52; c++) {
			this.deck.add(new Card(c,false));
		}
	}

	/**
	 * method that sorts the Cards in this.deck in a random sequence.
	 */
	public void shuffle() {
		Collections.shuffle(this.deck);
	}

	/**
	 * @return The ArrayList of Cards in this.deck.
	 */
	public ArrayList<Card> getDeck(){
		return this.deck;
	}

	/**
	 * @param suit - the String of the suit value of the desired card
	 * @param rank - the String of the rank value of the desired card
	 * @return 
	 */
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
	public boolean addCard(Card card, boolean override) {
		return false;
	}


	@Override
	public int getNumCards() {
		return this.deck.size();
	}

	@Override
	public Card getCard() {
		return deck.get(0);
	}

	@Override
	public Card takeCard() {
		Card result = deck.get(0);
		deck.remove(result);
		return result;
	}
	public String toString() {
		String result = "";
		for(Card c : deck) {
			result = result + ", " + c;
		}
		return result;
	}
	
}
