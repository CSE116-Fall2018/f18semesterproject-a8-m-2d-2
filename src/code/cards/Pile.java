package code.cards;

public interface Pile {
	
	/**
	 * Adds a card to the top of the Pile.
	 * 
	 * @param	card	The Card object to be added
	 */
	public void addCard(Card card);
	
	/**
	 * Removes the Card object first in the cards ArrayList.
	 */
	public void removeCard();
	
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
}
