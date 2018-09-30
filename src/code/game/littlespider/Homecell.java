package code.game.littlespider;

import java.util.ArrayList;

import code.cards.Card;
import code.cards.Pile;

/**
 * Instances of this class are used to hold data about the homecell piles in the Little Spider game.
 * It implements the pile interface to reflect the game rules given in the project
 * specifications.
 * 
 * @author Drew Fiutko
 * 
 */
public class Homecell implements Pile {
	/**
	 * Holds all the cards that are in the pile in an ArrayList.
	 */
	private ArrayList<Card> cards;

	/**
	 * Holds the top card in the pile for use by the class.
	 */
	private Card topCard;

	/**
	 * Constructor for LittleSpider Homecell. Adds @param as topCard and adds card to cards list.
	 * 
	 * @param card Card that homecell pile starts faceUp with
	 */

	public Homecell(Card card) {
		cards = new ArrayList<>();
		cards.add(card);
		topCard = card;
	}
	/**
	 * Takes a card and adds it to the top of the pile if and only if it follows the game rules.
	 * 
	 * @param card Card to be put on the top of the pile.
	 * @param override Boolean value used to bypass the add card rules.
	 * @return {@code true} if {@code card} was successfully added to the pile. {@code false} if {@code card} was an illegal add and was not added to the pile.
	 */

	@Override
	public boolean addCard(Card card, boolean override) {
		
		if(card == null) return false;
		
		if(override) {
			cards.add(0,card);
			topCard = card;
			return true;
		}
		
		//groups diamonds and hearts per rules of the game
		if(card.getSuit().equals("diamond") || card.getSuit().equals("heart")) {
			//checks if suits match and input card is one above
			if(card.getSuit().equals(topCard.getSuit()) && card.getValue() == topCard.getValue()+1) {

				cards.add(0,card);
				topCard = card;
				return true;
			}else {
				//cannot add card
				return false;
			}

		}
		//groups spades and clubs per rules of the game
		if(card.getSuit().equals("spade") || card.getSuit().equals("club")) {
			//checks if suits match and input card is one below
			if(card.getSuit().equals(topCard.getSuit()) && card.getValue() == topCard.getValue()-1) {
				cards.add(0,card);
				topCard = card;
				return true;
			}else {
				//cannot add card
				return false;
			}
		}
		return false;
	}


	/**
	 * Returns number of cards in pile.
	 * 
	 * @return Number of cards.
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
		return topCard;
	}
	/**
	 * Removes the top card and returns the top card for use by another method.
	 * If its the last card, {@code takeCard()} returns null as this is an invalid move.
	 * 
	 * @return Top {@code Card} in the pile.
	 */
	@Override
	public Card takeCard() {
		Card top = topCard;
		if(cards.size() == 1) return null;

		cards.remove(0);
		topCard = cards.get(0);

		return top;
	}
	/**
	 * Returns an ArrayList of all the cards in the pile.
	 * 
	 * @return All cards in the pile in an ArrayList.
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}


}
