package code.cards;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import code.game.Game;
import code.game.golf.Golf;
import code.game.littlespider.Homecell;
import code.game.littlespider.LittleSpider;

/**
 * Instantiates a card dependent upon the id passed to it.
 * The id is used to determine the suit and rank of the card.
 * 
 * @author Mitch Thurston
 * 
 */
public class Card extends JLabel implements MouseListener {

	/** 
	 * Required when extending JComponents or something. 
	 */
	private static final long serialVersionUID = 1L;
	/** The suit the card is in (e.g. heart)*/
	private String suit;
	/** The rank of the card (e.g. ace, 6, queen)*/
	private String rank;
	/** The value that the card represents*/
	private int value;
	/** The parameter ID stored*/
	public int id;
	/** Whether the card is faced down or up, true is facing up, false if facing down*/
	private boolean faceUp;
	/** If the card is at the top of its pile or not */
	private boolean top;
	/** The file path of the image icon */
	private URL iconPath;	
	/** The current Game instance */
	private Game game;
	/** The index of the Tableau this card belongs to */
	private int tableauNum;

	/**
	 * The constructor of Card takes two parameters, int id and boolean faceUp.
	 * The id must be between 0 - 51, as this determines the rank and suit of the card.
	 * Values below and above this should not be passed to the Card class.
	 * 
	 * @param id - int that will determine the rank and suit of the card.
	 * @param faceUp - boolean that determines if the card is faced up or faced down.
	 */
	public Card(int id, Game game) {
		this.game = game;
		this.id = id;
		// int value of the suit
		int suit = id / 13;
		// int value of the rank
		int rank = id % 13;
		this.value = rank;

		switch(suit) {
		case 0:
			this.suit = "heart";
			break;
		case 1:
			this.suit = "diamond";
			break;
		case 2:
			this.suit = "club";
			break;
		case 3:
			this.suit = "spade";
			break;
		default:
			System.out.println("no matching suit");	
		}

		// The image filename, e.g. 1d, ad, etc.
		String imgFile;

		switch(rank) {
		case 0:
			this.rank = "ace";
			imgFile = "a" + this.suit.charAt(0);
			break;
		case 10:
			this.rank = "jack";
			imgFile = "j" + this.suit.charAt(0);
			break;
		case 11:
			this.rank = "queen";
			imgFile = "q" + this.suit.charAt(0);
			break;
		case 12:
			this.rank = "king";
			imgFile = "k" + this.suit.charAt(0);
			break;
		default:
			this.rank = String.valueOf(rank+1);
			imgFile = this.rank + this.suit.charAt(0);
		}

		this.iconPath = getClass().getResource("/" + imgFile + ".png");
		if (this.iconPath == null) {
			throw new IllegalArgumentException("Could not find card image file " + imgFile);
		}

		// Is not set as top card of pile
		this.top = false;
		// Add self as mouseListener
		addMouseListener(this);

		// Set default image as back of card
		URL path = getClass().getResource("/b.png");
		if (path == null) {
			throw new IllegalArgumentException("Could not find card image file " + path);
		}

		setIcon(new ImageIcon(path));
		setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.TOP);

		// Card defaults to face down
		this.faceUp = false;
	}

	/**
	 * Sets the field faceUp as true.
	 */
	public void setFaceUp() {
		setIcon(new ImageIcon(this.iconPath));
		this.faceUp = true;
	}

	/**
	 * Returns whether or not the card is face up.
	 * 
	 * @return boolean whether or not the card is face up
	 */
	public boolean isFaceUp() {
		return this.faceUp;
	}

	/**
	 * Returns the rank of the card in the rank field.
	 * 
	 * @return String the instance String referencing the rank of the card.
	 */
	public String getRank() {
		return this.rank;
	}

	/**
	 * Returns the suit of the card in the suit field.
	 * 
	 * @return String the instance String referencing the suit of the card.
	 */
	public String getSuit() {
		return this.suit;
	}

	/**
	 * Returns the int value of the rank of the card from the value field.
	 * 
	 * @return int the instance int referencing the value of the card.
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * Sets the card as the card on top of the pile.
	 */
	public void setTop() {
		this.top = true;
	}
	/**
	 * Sets the card as under the top card.
	 */
	public void setUnder() {
		this.top = false;
	}
	/** Deselects the current card in the game. */
	public void deselect() {
		this.game.setTableauSelected(null);
		setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
	}

	/**
	 * Set the tableau index that this card is part of
	 * @param int the tableau index
	 */
	public void setTableauNum(int a) {
		this.tableauNum = a;
	}

	/**
	 * Returns the tableau tableau number that this card of part of.
	 * 
	 * @return int The tableau index #
	 */
	public int getTableauNum() {
		return this.tableauNum;
	}

	/**
	 * Determines what it done when the card is clicked.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// Return if this card isn't the top of the tableau
		if (!this.top) {
			return;
		}

		// Get all of the Tableaus
		Pile[] tableaus = this.game.getTableaus();
		System.out.println(this.game.getMoves());
		// If a tableau is selected (not null) and the same
		// tableau is clicked again, deselect the tableau
		if (game.tableauSelected() != null && game.tableauSelected().equals(tableaus[tableauNum])) {
			deselect();
			return;
		}
		
		if(this.game.isHomecellSelected()) {
			System.out.println("running");
			Card card = this.game.homecellSelected().takeCard();
			boolean added = tableaus[this.tableauNum].addCard(card, false);
			if(added) {
				card.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
				tableaus[this.tableauNum].getAllCards().get(1).setUnder();
				card.setTableauNum(this.tableauNum);
				game.setMoves(game.getMoves() + 1);
			}else {
				this.game.homecellSelected().addCard(card, true);
			}
			System.out.println(this.game.homecellSelected().getNumCards());
			((Homecell) this.game.homecellSelected()).deselect();
			
			deselect();
			this.game.refresh();
			return;
		}
		// If no tableau card is selected yet, select this one
		if (!this.game.isTableauSelected()) {
			this.game.setTableauSelected(tableaus[this.tableauNum]);
			setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			return;
		}

		// If this is a Golf game, and a tableau is already selected, do nothing
		if (game instanceof Golf) {
			// GUI.sendError("Illegal move");
			return;
		} 
		// If this is a Little Spider game, and a tableau is already selected, try to add to 
		// This card's parent tableau
		if (game instanceof LittleSpider) {
			if(this.game.isTableauSelected()) {
				// Attempt to add the card to tableau
				Card card = this.game.tableauSelected().getCard();
				boolean added = tableaus[this.tableauNum].addCard(card, false);
				if (added) {
					this.game.tableauSelected().takeCard();
					card.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
					tableaus[this.tableauNum].getAllCards().get(1).setUnder();
					card.setTableauNum(this.tableauNum);
					game.setMoves(game.getMoves() + 1);
				}else {
					//throw error
					card.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
				}
				deselect();
				this.game.refresh();
				return;
			}
			
		}
	}

	/** This method does nothing. */
	@Override
	public void mousePressed(MouseEvent e) {}
	/** This method does nothing. */
	@Override
	public void mouseReleased(MouseEvent e) {}
	/** This method does nothing. */
	@Override
	public void mouseEntered(MouseEvent e) {}
	/** This method does nothing. */
	@Override
	public void mouseExited(MouseEvent e) {}

	public String toString() {
		return rank + suit;
	}
}
