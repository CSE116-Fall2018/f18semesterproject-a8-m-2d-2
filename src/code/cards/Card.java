package code.cards;


/**
 * @author thurs
 */
/**
 * @author thurs
 *
 */
public class Card {
	
	/** The suit the card is in (e.g. heart)*/
	private String suit;
	/** The rank of the card (e.g. ace, 6, queen)*/
	private String rank;
	/** The value that the card represents*/
	private int value;
	/** */
	int ID;
	/** */
	public boolean faceUp;
	
	
	/**
	 * @param ID - int that will determine the rank and suit of the card.
	 * @param faceUp - boolean that determines if the card is faced up or faced down.
	 */
	public Card(int ID, boolean faceUp) {
		this.ID = ID;
		int suit = ID/13;
		Integer rank = (int) ID%13;
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
	 * @return the instance String referencing the rank of the card.
	 */
	public String getRank() {
		return this.rank;
	}
	
	/**
	 * @return the instance String referencing the suit of the card.
	 */
	public String getSuit() {
		return this.suit;
	}

	/**
	 * @return the instance int referencing the value of the card.
	 */
	public int getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return rank +" of " +suit;
	}
	
}
