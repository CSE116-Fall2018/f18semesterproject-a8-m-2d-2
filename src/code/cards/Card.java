package code.cards;

public class Card {
	
	int rank;
	int suit;
	boolean faceUp;

	public Card(int rank, int suit) {
		// TODO Determine best way to instantiate the card wrt suit
	}

	public boolean isFaceUp() {
		return faceUp;
	}

	public void setFaceUp(boolean faceUp) {
		this.faceUp = faceUp;
	}
}
