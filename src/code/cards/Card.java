package code.cards;


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
	
	
	/**
	 * The constructor of Card takes two parameters, int id and boolean faceUp.
	 * The id must be between 0 - 51, as this determines the rank and suit of the card.
	 * Values below and above this should not be passed to the Card class.
	 * 
	 * @param id - int that will determine the rank and suit of the card.
	 * @param faceUp - boolean that determines if the card is faced up or faced down.
	 */
	public Card(int id, boolean faceUp) {
		this.id = id;
		int suit = id/13;
		int rank = id%13;
		this.value = rank;
		
		switch(suit) {
		case 0:
			this.suit = "diamond";
			break;
		case 1:
			this.suit = "heart";
			break;
		case 2:
			this.suit = "spade";
			break;
		case 3:
			this.suit = "club";
			break;
		default:
			System.out.println("no matching suit");
			
		}
		
		if (rank == 0)
			this.rank = "ace";
		else if(rank>0 && rank<10) {
			Integer Rank = rank + 1;
			this.rank = (Rank).toString();
		}
		else if(rank == 10)
			this.rank = "jack";
		else if(rank == 11)
			this.rank = "queen";
		else
			this.rank = "king";
		this.faceUp = faceUp;
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
		return value;
	}
}
