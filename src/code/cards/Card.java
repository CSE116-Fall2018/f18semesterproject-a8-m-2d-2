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
public class Card extends JLabel {

	/** Required when extending JComponents or something. */
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
	private ImageIcon cardIcon;
	/** The back of the card */
	private ImageIcon cardBack;
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

		// Set this card's actual cardface & back of the card
		try {
			URL icon = getClass().getResource("/" + imgFile + ".png");
			this.cardIcon = new ImageIcon(icon);
			URL back = getClass().getResource("/b.png");
			this.cardBack = new ImageIcon(back);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Is not set as top card of pile
		this.setTop(false);
		// Card defaults to face down
		this.faceUp = false;

		// Add CardListener as mouseListener
		addMouseListener(new CardListener(rank, game, this));
		setIcon(this.cardBack);
		setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.TOP);
	}

	/**
	 * Flips the card over.
	 */
	public void flip() {
		this.faceUp = !this.faceUp;
		
		if (this.faceUp) {
			setIcon(this.cardIcon);
		} else {
			setIcon(this.cardBack);
		}
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
		this.setTop(false);
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
	public void setTableauNum(int num) {
		this.tableauNum = num;
	}

	/**
	 * Returns the tableau tableau number that this card of part of.
	 * 
	 * @return int The tableau index #
	 */
	public int getTableauNum() {
		return this.tableauNum;
	}

	public boolean isTop() {
		return top;
	}

	public void setTop(boolean top) {
		this.top = top;
	}
	
}
