package code.game.littlespider;

import java.util.ArrayList;

import code.cards.Card;
import code.cards.Pile;

/**
 * Instances of this class are used to hold data about the tableau piles in the Little Spider game.
 * It implements the pile interface to reflect the game rules given in the project
 * specifications.
 * 
 * @author Drew Fiutko
 * 
 */
public class Tableau implements Pile {

	/**
	 * Holds all the cards in the pile in an ArrayList.
	 */
	private ArrayList<Card> cards;
	
	/**
	 * Holds the top card for use in the class.
	 */
	private Card topCard;
	
	
	/**
	 * Instantiates the ArrayList of cards in the pile and sets {@code canUseAddFirstCard} to true so that the first 6 cards can be added to the pile.
	 */
	public Tableau() {
		cards = new ArrayList<>();
	}
	
	/**
	 * Adds card if and only if it complies with the rules of the game.
	 * 
	 * @param card Card to be added.
	 * @param override Boolean value used to bypass the add card rules to add the first 6 cards.
	 * @return {@code true} if {@code card} was successfully added to the pile. {@code false} if {@code card} was an illegal add and was not added to the pile.
	 */
	@Override
	public boolean addCard(Card card, boolean override) {
		
		if(card == null) return false;
		
		if(override == true) {
			cards.add(0, card);
			topCard = card;
			return true;
		}
		
		if(cards.size() == 0) return false;
		
		int difference = Math.abs(card.getValue() - topCard.getValue());
		if(difference == 1) {
			cards.add(0, card);
			topCard = card;
			return true;
		}
		else if (difference == 12) {
			cards.add(0, card);
			topCard = card;
			return true;
		}
		return false;
	}

	/**
	 * Returns the number of cards in the pile.
	 * 
	 * @return Number of cards in the pile.
	 */
	@Override
	public int getNumCards() {
		return cards.size();
	}
	 
	/**
	 * Returns the top card in the pile.
	 * 
	 * @return Top {@code Card} in the pile.
	 */
	@Override
	public Card getCard() {
		
		if(cards.size() == 0) return null;
		return topCard;
	}

	/**
	 * Returns the card being removed for use by other methods.
	 * Removes that card from the pile.
	 * 
	 * @return Top {@code Card} in the pile.
	 */
	@Override
	public Card takeCard() {
		Card top = topCard;
		if(cards.size() == 0) return null;
		
		cards.remove(0);
		topCard = cards.get(0);
			
		return top;
	}

	
	/**
	 * Returns the ArrayList of cards currently in the pile.
	 * 
	 * @return ArrayList<Card> All cards in the pile.
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}

}
