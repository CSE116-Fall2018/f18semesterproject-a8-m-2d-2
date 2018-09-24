package code.cards;

public class Card {
	
	private String suit;
	private String rank;
	private int value;
	int ID;
	public boolean faceUp;
	
	public Card(int ID, boolean faceUp) {
		this.ID = ID;
		int suit = ID/13;
		Integer rank = (int) ID%13;
		this.setValue(rank);
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

	public void setFaceUp() {
		this.faceUp = true;
	}

	public String getRank() {
		return this.rank;
	}
	
	public String getSuit() {
		return this.suit;
	}

	public String toString() {
		return rank +" of " +suit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
