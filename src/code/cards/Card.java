package code.cards;

import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * Instantiates a card dependent upon the id passed to it.
 * The id is used to determine the suit and rank of the card.
 * 
 * @author Mitch Thurston
 * 
 */
public class Card {
	
	/** The suit the card is in (e.g. heart)*/
	private String suit;
	/** The rank of the card (e.g. ace, 6, queen)*/
	private String rank;
	/** The value that the card represents*/
	private int value;
	/** The parameter ID stored*/
	public int id;
	/** Whether the card is faced down or up, true is facing up, false if facing down*/
	public boolean faceUp;
	/** The JLabel card image */
	private JLabel icon;
	
	
	/**
	 * The constructor of Card takes two parameters, int id and boolean faceUp.
	 * The id must be between 0 - 51, as this determines the rank and suit of the card.
	 * Values below and above this should not be passed to the Card class.
	 * 
	 * @param id - int that will determine the rank and suit of the card.
	 * @param faceUp - boolean that determines if the card is faced up or faced down.
	 */
	public Card(int id) {
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
		
		// Local URL of the image
		URL imgURL = getClass().getResource("/" + imgFile + ".png");
		Image img = new ImageIcon(imgURL).getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH);
		ImageIcon resizedImg = new ImageIcon(img);
		JLabel label = new JLabel();
		label.setIcon(resizedImg);
		label.setPreferredSize(new Dimension(100, 140));
		label.setOpaque(true);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.TOP);
	    
		this.icon = label;
		this.faceUp = false;
	}

	/**
	 * Sets the field faceUp as true.
	 */
	public void setFaceUp() {
		this.faceUp = true;
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
	 * Returns the JLabel with the card face ImageIcon for display in the GUI.
	 * 
	 * @return JLabel the JLabel image icon of the card.
	 */
	public JLabel getIcon() {
		return this.icon;
	}
}
