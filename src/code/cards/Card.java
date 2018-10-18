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
	
	private Game game;
	
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
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.TOP);
		
		// Card defaults to face down
		this.faceUp = false;
	}

	/**
	 * Sets the field faceUp as true.
	 */
	public void setFaceUp() {
		// Resize the card image & set the image stuff appropriately
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

	public Game getGame() {
		return this.game;
	}
	/**
	 * Determines what it done when the card is clicked.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (!this.top) {
			return;
		}
		
		if (this.game.isTableauSelected() == this.game.get) {
			this.game.setTableauSelected(null);
			setBorder(null);
		}
		
		if (game instanceof Golf && this.game.isCardSelected()) {
			return;
		} else if (game instanceof Golf && !this.game.isCardSelected()) {
			this.game.setCardSelected(this);
			setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		}
	}
	
	public void setTableauNum(int a) {
		this.tableauNum = a;
	}
	
	public int getTableauNum() {
		return this.tableauNum;
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
}
