package code.game.golf;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import code.cards.Card;
import code.cards.Pile;

/**
 * Instantiates the Homecell pile, which initiates with an
 * empty ArrayList of cards. Cards are added to this class
 * throughout the game in accordance with the rules of the game.
 * 
 * @author Matt Ferrera
 */
@SuppressWarnings("serial")
public class Homecell extends JLabel implements ActionListener, MouseListener, Pile {

	/**
	 * Cards is the ArrayList containing all Card objects in the Homecell pile.
	 */
	private ArrayList<Card> cards;
	
	/**
	 * Homecell initializes the ArrayList for the cards fields, which is empty.
	 */
	public Homecell() {
		cards = new ArrayList<>();
		this.addMouseListener(this);
		URL filePath = getClass().getResource("/e.png"); // empty.png
		Image img = new ImageIcon(filePath).getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH);
		ImageIcon resizedImg = new ImageIcon(img);
		this.setIcon(resizedImg);
		this.setPreferredSize(new Dimension(100, 140));
		this.setOpaque(true);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.TOP);
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
		
		// No cards are currently in the Homecell
		if (cards.size() == 0 || override) {
			cards.add(0, card);
			this.setIcon(card.getIcon());
			return true;
		}
		
		int difference = Math.abs(card.getValue() - getCard().getValue());
		
		if (difference == 1) {
			cards.add(0, card);
			this.setIcon(card.getIcon());
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
	 * In the future, will disallow cards to be removed from the pile.
	 * 
	 * @return Card returns null, as this is not a legal operation.
	 */
	@Override
	public Card takeCard() {
		// TODO Placeholder functionality until GUI
		System.out.println("Cannot remove cards from the Homecell pile.");
		return null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
