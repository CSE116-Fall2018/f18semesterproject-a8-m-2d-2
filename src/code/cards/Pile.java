package code.cards;

import java.util.ArrayList;

/**
 * The pile interface contains methods that copy or are
 * useful to how a pile of cards is interacted with in the real world.
 * 
 * @author Matt Ferrera
 *
 */
public interface Pile {
	
	/**
	 * Adds a card to the top of the Pile.
	 * 
	 * @param card The Card object to be added
	 * @param override Whether or not to adhere to or ignore card adding rules
	 */
	public boolean addCard(Card card, boolean override);
	
	/**
	 * Returns an int with the amount of cards in the Pile.
	 * 
	 * @return int of amount of cards in the Pile.
	 */
	public int getNumCards();
	
	/**
	 * Returns the Card object on the top of a pile.
	 * 
	 * @return the Card object first in the cards ArrayList
	 */
	public Card getCard();
	
	/**
	 * Gets & removes the card at the top of the pile.
	 * 
	 * @return Card at the top of the pile.
	 */
	public Card takeCard();
	
	/**
	 * Returns the ArrayList containing all the Cards in this pile.
	 * 
	 * @return ArrayList<Card> all of the cards in the pile.
	 */
	public ArrayList<Card> getAllCards();
}
