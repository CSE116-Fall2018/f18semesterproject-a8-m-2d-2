package code.game.fortythieves;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;

import code.cards.Card;
import code.cards.Pile;

/**
 * Instantiates the Stockpile pile, which receives the remainder
 * of the deck after the cards are disbursed to the Tableau piles. 
 * Cards cannot be added to this pile, but can be removed in accordance
 * with the rules of the game.
 * 
 * @author Matt Ferrera
 */
public class Stockpile extends JLabel implements MouseListener, Pile {

	/** Required when extending JComponents or something. */
	private static final long serialVersionUID = 1L;
	/** Cards is the ArrayList containing all Card objects in the Stockpile pile. */
	private ArrayList<Card> cards;
	/** The current game instance passed from Golf init */
	private FortyThieves game;
	
	/**
	 * The Stockpile constructor takes all of the deck not disbursed to tableaus and sets it to the cards field.
	 * 
	 * @param game the Golf game instance
	 * @param remainingDeck the ArrayList contained the remainder of cards
	 */
	public Stockpile(FortyThieves game, ArrayList<Card> remainingDeck) {
		this.addMouseListener(this);
		this.game = game;
		this.cards = remainingDeck;
		
		if (cards.size() > 0) {
			this.setIcon(getCard().getIcon());
		}
	}
	
	/**
	 * Attempts to add a card to the Stockpile, which is an illegal action in golf, so it returns false.
	 * 
	 * @param card Card to be added
	 * @return false Cards cannot be added to this pile in Golf.
	 */
	@Override
	public boolean addCard(Card card, boolean override) {
		this.game.setErrorText();
		return false;
	}

	/**
	 * Returns an int of the remaining cards in the stockpile.
	 * 
	 * @return int Number of cards in the stockpile.
	 */
	@Override
	public int getNumCards() {
		return cards.size();
	}

	/**
	 * Returns the card at the top of the stockpile. Does not remove the card from the pile.
	 * 
	 * @return Card The card at index 0 of the Stockpile.
	 */
	@Override
	public Card getCard() {
		if (getNumCards() == 0) {
			return null;
		}
		
		return cards.get(0);
	}

	/**
	 * Attempts to take the card from the top of the Stockpile. Does not remove it unless certain conditions are met.
	 * 
	 * @return Card The card at the top of the Stockpile.
	 */
	@Override
	public Card takeCard() {
		if (getNumCards() == 0) {
			return null;
		}
		
		Card card = this.cards.remove(0);
		
		// Set new top card face up & set new icon
		if (getNumCards() == 0) {
			this.setIcon(null);
		} else {
			this.setIcon(getCard().getIcon());
		}
		
		return card;
	}

	/**
	 * When clicked, the stockpile checks if it needs to flip the top
	 * card over if it has not yet been clicked. Following cards are set
	 * face up and are moved to the homecell pile with click.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (getNumCards() == 0) {
			this.setIcon(null);
			this.game.setErrorText();
			return;
		}
		
		// Set the card going to homecell pile face up
		getCard().flip();
		game.setMoves(game.getMoves() + 1);
		this.game.getWastepile().addCard(takeCard(), true);
		this.game.setBlankErrorText();
	}

	/**
	 * Returns null since middle of cards are not used. Only top card is used.
	 */
	@Override
	public ArrayList<Card> getAllCards() {
		return null;
	}
	
	/** This method is not used. */
	@Override
	public void mousePressed(MouseEvent e) {}
	/** This method is not used. */
	@Override
	public void mouseReleased(MouseEvent e) {}
	/** This method is not used. */
	@Override
	public void mouseEntered(MouseEvent e) {}
	/** This method is not used. */
	@Override
	public void mouseExited(MouseEvent e) {}
}
