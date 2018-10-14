package code.game.golf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;

import code.cards.Card;
import code.cards.Pile;
import code.game.gui.GUI;

/**
 * Instantiates the Stockpile pile, which receives the remainder
 * of the deck after the cards are disbursed to the Tableau piles. 
 * Cards cannot be added to this pile, but can be removed in accordance
 * with the rules of the game.
 * 
 * @author Matt Ferrera
 */
@SuppressWarnings("serial")
public class Stockpile extends JLabel implements ActionListener, MouseListener, Pile {

	/**
	 * Cards is the ArrayList containing all Card objects in the Stockpile pile.
	 */
	private ArrayList<Card> cards;
	Golf game;
	
	/**
	 * The Stockpile constructor takes all of the deck not disbursed to tableaus and sets it to the cards field.
	 * 
	 * @param remainingDeck
	 */
	public Stockpile(Golf game, ArrayList<Card> remainingDeck) {
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
		System.out.println("Cannot add cards to this pile.");
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
		
		Card card = getCard();
		this.cards.remove(0);
		// Set new top card face up
		if (getNumCards() != 0) {
			getCard().setFaceUp();
			this.setIcon(getCard().getIcon());
		}
		
		return card;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (getNumCards() == 0) {
			this.setIcon(GUI.getEmptyIcon());
			return;
		}
		
		if (!getCard().isFaceUp()) {
			getCard().setFaceUp();
			this.setIcon(getCard().getIcon());
			return;
		}
		
		this.game.getHomecell().addCard(takeCard(), true);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
