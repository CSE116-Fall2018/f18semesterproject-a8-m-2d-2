package code.game.fortythieves;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import code.cards.Card;
import code.cards.Pile;
import code.game.Game;
import code.game.gui.GUI;

/**
 * Instantiates the Wastepile, which initiates with an
 * empty ArrayList of cards. Cards are added to this class
 * throughout the game in accordance with the rules of the game.
 * 
 * @author Matt Ferrera
 */
public class Wastepile extends JLabel implements MouseListener, Pile {

	/** Required when extending JComponents or something. */
	private static final long serialVersionUID = 1L;
	/** Cards is the ArrayList containing all Card objects in the Wastepile. */
	private ArrayList<Card> cards;
	/** Game instance that this wastepile belongs to (needs to be a Golf instance). */
	private Game game;
	
	/**
	 * Wastepile initializes the ArrayList for the cards fields, which is empty.
	 * 
	 * @param game The FortyThieves game instance
	 */
	public Wastepile(Game game) {
		this.game = game;
		cards = new ArrayList<>();
		
		addMouseListener(this);
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.TOP);
		setIcon(GUI.getEmptyIcon());
	}
	
	/**
	 * Adds a card to the pile iff the wastepile is empty, or if the rank of the card
	 * at the top of the Wastepile differs by 1 from the top card in the pile.
	 * 
	 * @param card The card to be added.
	 */
	@Override
	public boolean addCard(Card card, boolean override) {
		// Do not allow adding null cards
		if (card == null) {
			return false;
		}
		
		if (override || !override) {
			cards.add(0, card);
			setIcon(card.getIcon());
			return true;
		}
		
		
		return false;
	}

	/**
	 * Returns an int of the number of cards in the Wastepile pile.
	 * 
	 * @return int number of cards in the Wastepile pile.
	 */
	@Override
	public int getNumCards() {
		return cards.size();
	}

	/**
	 * Returns the face-up card at the top of the Wastepile pile.
	 * 
	 * @return Card at the top of the Wastepile pile.
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
		if(cards.size() == 0) return null;
		
		Card top = cards.get(0);
		
		if(cards.size() == 1) {
			cards.remove(0);
			setIcon(GUI.getEmptyIcon());
		}else {
			cards.remove(0);
			setIcon(cards.get(0).getIcon());
		}
		
		return top;
	}
	/** Deselects the current card in the game. */
	public void deselect() {
		this.game.setWasteSelected(null);
		setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
	}

	/**
	 * The mouseClick handler for this class, which extends MouseListener.
	 * When clicked, it checks if a tableau is selected, and if not it does
	 * nothing. If a tableau is selected, it attempts to add the card to itself.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (getNumCards() == 0) {
			this.setIcon(null);
			this.game.setErrorText();
			return;
		}
		
		// If tableau (card) is selected, this is an illegal move
		if (this.game.isTableauSelected() || this.game.isHomecellSelected()) {
			this.game.setErrorText();
			return;
		}else if(this.game.isWasteSelected()) {
			setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
			deselect();
		}else {
			this.game.setWasteSelected(this);
			setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			return;
		}
		
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
