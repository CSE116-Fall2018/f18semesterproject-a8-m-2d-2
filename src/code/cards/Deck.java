package code.cards;

import java.util.ArrayList;
import java.util.Collections;

import code.game.Game;

/**
 * Instantiates a deck of cards, containing an ArrayList of all 52 cards in the deck.
 * Implements the Pile interface.
 * 
 * @author Mitch Thurston
 * 
 */
public class Deck implements Pile {

	/**
	 * The Collection of all cards in the deck.
	 */
	ArrayList<Card> deck = new ArrayList<>();

	/**
	 * Constructor for Deck that takes no parameters and calls initialize().
	 */
	public Deck (Game game) {
		for(int c = 0; c < 52; c++) {
			this.deck.add(new Card(c, game));
			
		}
		
	}

	/**
	 * Sorts the Cards in this.deck in a random sequence.
	 */
	public void shuffle() {
		Collections.shuffle(this.deck);
	}

	/**
	 * Returns the ArrayList<Card> deck field containing all cards in the deck
	 * 
	 * @return ArrayList<Card> The ArrayList of Cards in this.deck.
	 */
	public ArrayList<Card> getDeck(){
		return this.deck;
	}

	/**
	 * Returns a specific card from the deck. The card is specific by the String suit 
	 * and String rank of the card.
	 * 
	 * @param suit the String of the suit value of the desired card
	 * @param rank the String of the rank value of the desired card
	 * @return Card the specified card
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
	
	/**
	 * Attempts to add a card to the deck, which is an illegal action for the deck.
	 * Always returns false.
	 * 
	 * @param card The Card to be added
	 * @param override Boolean value, whether or not to override adding rules
	 * @return boolean Always returns false.
	 */
	@Override
	public boolean addCard(Card card, boolean override) {
		return false;
	}

	/**
	 * Returns an int of the amount of cards in the deck.
	 * 
	 * @return int Number of cards in the deck.
	 */
	@Override
	public int getNumCards() {
		return this.deck.size();
	}

	/**
	 * Returns the card at the top of the deck.
	 * 
	 * @return Card The Card at the top of the deck.
	 */
	@Override
	public Card getCard() {
		return deck.get(0);
	}

	/**
	 * Takes the card at the top of the deck. This removes the card from the deck field.
	 * 
	 * @return Card the Card at the top of the deck.
	 */
	@Override
	public Card takeCard() {
		Card result = deck.get(0);
		deck.remove(result);
		return result;
	}

	/** This method is not used. Returns null. */
	@Override
	public ArrayList<Card> getAllCards() { 
		return this.deck; 
	}
}
