package code.game.littlespider;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import code.cards.Card;
import code.cards.Pile;
import code.game.Game;

/**
 * Instances of this class are used to hold data about the homecell piles in the Little Spider game.
 * It implements the pile interface to reflect the game rules given in the project
 * specifications.
 * 
 * @author Drew Fiutko
 * 
 */
public class Homecell extends JLabel implements MouseListener, Pile {
	/**
	 * required.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Holds all the cards that are in the pile in an ArrayList.
	 */
	private ArrayList<Card> cards;

	/**
	 * Holds the top card in the pile for use by the class.
	 */
	private Card topCard;

	private Game game;
	/** The index of the homecell this card belongs to */
	private int homecellNum;

	/**
	 * Constructor for LittleSpider Homecell. Adds @param as topCard and adds card to cards list.
	 * 
	 * @param card Card that homecell pile starts faceUp with
	 */

	public Homecell(Card card, Game game) {
		cards = new ArrayList<>();
		cards.add(card);
		topCard = card;
		this.game = game;

		addMouseListener(this);
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.TOP);
		setIcon(topCard.getIcon());
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
			setIcon(topCard.getIcon());
			return true;
		}

		//groups diamonds and hearts per rules of the game
		if(card.getSuit().equals("diamond") || card.getSuit().equals("heart")) {
			//checks if suits match and input card is one above
			if(card.getSuit().equals(topCard.getSuit()) && card.getValue() == topCard.getValue()+1) {

				cards.add(0,card);
				topCard = card;
				setIcon(topCard.getIcon());
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
				setIcon(topCard.getIcon());
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
		setIcon(topCard.getIcon());

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
	@Override
	public ArrayList<Card> getAllCards() {
		return cards;
	}

	/**
	 * Set the homecell index that this card is part of
	 * @param int the homecell index
	 */
	public void setHomecellNum(int a) {
		this.homecellNum = a;
	}

	/**
	 * Returns the homecell tableau number that this card of part of.
	 * 
	 * @return int The homecell index #
	 */
	public int getHomecellNum() {
		return this.homecellNum;
	}
	/** Deselects the current card in the game. */
	public void deselect() {
		this.game.setHomecellSelected(null);
		setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		Pile[] homecells = this.game.getHomecells();

		// if homecell is already selected
		if (game.homecellSelected() != null && game.homecellSelected().equals(homecells[homecellNum])) {
			deselect();
			return;
		}

		// If no card is selected
		if (!this.game.isTableauSelected() && !this.game.isHomecellSelected()) {
			this.game.setHomecellSelected(homecells[this.homecellNum]);
			setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			return;
		}
		if(this.game.isTableauSelected()) {
			// Take the top card from the Tableau
			Card toAdd = this.game.tableauSelected().takeCard();
			// See if it can be added to the homecell
			boolean added = this.addCard(toAdd, false);
			// If not, add it back to the tableau
			if (!added) {
				// GUI.sendError("Illegal move");
				this.game.tableauSelected().addCard(toAdd, true);
			}

			// Deselect the tableau & refresh
			this.game.setTableauSelected(null);
			toAdd.deselect();
			this.game.refresh();
			return;
		}
	}
	/** This method is not used. */
	@Override
	public void mouseEntered(MouseEvent e) {}
	/** This method is not used. */
	@Override
	public void mouseExited(MouseEvent e) {}
	/** This method is not used. */
	@Override
	public void mousePressed(MouseEvent e) {}
	/** This method is not used. */
	@Override
	public void mouseReleased(MouseEvent e) {}


}
