package code.game.littlespider;


import code.cards.Card;
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
		
		Card c1 = deck.getSpecificCard("ace", "diamond");
		c1.setFaceUp();
		Homecell homecell1 = new Homecell(c1);
		homecells[0] = homecell1;
		
		Card c2 = deck.getSpecificCard("ace", "heart");
		c2.setFaceUp();
		Homecell homecell2 = new Homecell(c2);
		homecells[0] = homecell2;
		
		Card c3 = deck.getSpecificCard("king", "club");
		c3.setFaceUp();
		Homecell homecell3 = new Homecell(c3);
		homecells[0] = homecell3;
		
		Card c4 = deck.getSpecificCard("king", "spade");
		c4.setFaceUp();
		Homecell homecell4 = new Homecell(c4);
		homecells[0] = homecell4;
		
		tableaus = new Tableau[8];
		for(int i=0; i<tableaus.length; i++) {
			for(int j=1; j<=6; j++) {
				Card x = deck.takeCard();
				x.setFaceUp();
				tableaus[i].addCard(x);
			}
		}
	}
	public Homecell[] getHomecells() {
		return homecells;
	}
	public Tableau[] getTableaus() {
		return tableaus;
	}
	
	
}
