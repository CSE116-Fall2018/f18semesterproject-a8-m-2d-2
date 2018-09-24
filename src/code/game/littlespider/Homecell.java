package code.game.littlespider;

import java.util.ArrayList;

import code.cards.Card;
import code.cards.Pile;

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
	 * Takes a card and adds it to the top of the pile if and only if it 
	 * follows the game rules.
	 * 
	 * @param card Card to be put on the top of the pile.
	 * @return boolean Boolean value that shows whether the add was legal or illegal.
	 */

	@Override
	public boolean addCard(Card card) {
		
		if(card == null) return false;
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
	 * @return int Number of cards.
	 */
	@Override
	public int getNumCards() {
		return cards.size();
	}
	/**
	 * Returns the top card in the pile.
	 * 
	 * @return Card Top card in the pile.
	 */
	@Override
	public Card getCard() {
		
		if(cards.size() == 0) return null;
		return topCard;
	}
	/**
	 * Removes the top card and returns the top card for use by another method.
	 * If its the last card, takeCard() returns null as this is an invalid move.
	 * 
	 * @return Card top card in the pile.
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
	 * @return ArrayList<Card> All cards in the pile.
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}


}
