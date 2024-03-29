package code.game.golf;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;

import code.cards.Card;
import code.cards.Pile;
import code.game.Game;
import code.game.gui.GUI;

/**
 * Instantiates the Homecell pile, which initiates with an
 * empty ArrayList of cards. Cards are added to this class
 * throughout the game in accordance with the rules of the game.
 * 
 * @author Matt Ferrera
 */
public class Homecell extends JLabel implements MouseListener, Pile {

	/** Required when extending JComponents or something. */
	private static final long serialVersionUID = 1L;
	/** Cards is the ArrayList containing all Card objects in the Homecell pile. */
	private ArrayList<Card> cards;
	/** Game instance that this homecell belongs to (needs to be a Golf instance). */
	private Game game;
	
	/**
	 * Homecell initializes the ArrayList for the cards fields, which is empty.
	 * 
	 * @param game The Golf game instance
	 */
	public Homecell(Game game) {
		this.game = game;
		cards = new ArrayList<>();
		
		addMouseListener(this);
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.TOP);
		setIcon(GUI.getEmptyIcon());
	}
	
	/**
	 * Adds a card to the pile iff the homecell is empty, or if the rank of the card
	 * at the top of the Homecell differs by 1 from the top card in the pile.
	 * 
	 * @param card The card to be added.
	 */
	@Override
	public boolean addCard(Card card, boolean override) {
		// Do not allow adding null cards
		if (card == null) {
			return false;
		}
		
		if (override) {
			cards.add(0, card);
			setIcon(card.getIcon());
			return true;
		}
		
		// No cards are currently in the Homecell
		if (cards.size() == 0 && !override) {
			return false;
		}	
		
		int difference = Math.abs(card.getValue() - getCard().getValue());
		
		if (difference == 1 || difference == 12) {
			cards.add(0, card);
			setIcon(card.getIcon());
			return true;
		}
		
		return false;
	}

	/**
	 * Returns an int of the number of cards in the Homecell pile.
	 * 
	 * @return int number of cards in the Homecell pile.
	 */
	@Override
	public int getNumCards() {
		return cards.size();
	}

	/**
	 * Returns the face-up card at the top of the Homecell pile.
	 * 
	 * @return Card at the top of the Homecell pile.
	 */
	@Override
	public Card getCard() {
		if (getNumCards() == 0) {
			return null;
		}
		
		return cards.get(0);
	}

	/**
	 * Does not sallow cards to be removed from the pile.
	 * 
	 * @return Card returns null, as this is not a legal operation.
	 */
	@Override
	public Card takeCard() {
		this.game.setErrorText();
		return null;
	}

	/**
	 * The mouseClick handler for this class, which extends MouseListener.
	 * When clicked, it checks if a tableau is selected, and if not it does
	 * nothing. If a tableau is selected, it attempts to add the card to itself.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// If no tableau (card) is selected, this is an illegal move
		if (!this.game.isTableauSelected()) {
			this.game.setErrorText();
			return;
		}
		
		// Take the top card from the Tableau
		Card toAdd = this.game.tableauSelected().takeCard();
		// See if it can be added to the homecell
		boolean added = this.addCard(toAdd, false);
		
		// If not, add it back to the tableau
		if (!added) {
			this.game.setErrorText();
			this.game.tableauSelected().addCard(toAdd, true);
		} else {
			this.game.setBlankErrorText();
			game.setMoves(game.getMoves() + 1);
		}

		// Deselect the tableau & refresh
		this.game.setTableauSelected(null);
		toAdd.deselect();
		this.game.refresh();
	}
	
	/**
	 * Returns null since middle cards are not needed. Only top card is used.
	 */
	@Override
	public ArrayList<Card> getAllCards() { return null; }
	
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
	/** This method is not used. */
}
