package code.game.littlespider;


import code.cards.Deck;

public class LittleSpider {
	
	private Deck deck;
	private Homecell[] homecells;
	private Tableau[] tableaus;

	
	public LittleSpider() {
		deck = new Deck();
		init();
	}
	/**
	 * Creates Homecell and Tableau piles for Little Spider.
	 */
	private void init() {
		homecells = new Homecell[4];
		
		Homecell homecell1 = new Homecell(deck.getSpecificCard("ace", "diamond").setFaceUp());
		homecells[0] = homecell1;
		
		//need to discuss faceUp faceDown implementation
		
		Homecell homecell2 = new Homecell(deck.getSpecificCard("ace", "heart").setFaceUp());
		homecells[0] = homecell2;
		
		Homecell homecell3 = new Homecell(deck.getSpecificCard("king", "club").setFaceUp());
		homecells[0] = homecell3;
		
		Homecell homecell4 = new Homecell(deck.getSpecificCard("king", "spade").setFaceUp());
		homecells[0] = homecell4;
		
		tableaus = new Tableau[8];
		for(int i=0; i<tableaus.length; i++) {
			for(int j=1; j<=6; j++) {
				tableaus[i].addCard(deck.takeCard().setFaceUp());
			}
		}
	}
}
