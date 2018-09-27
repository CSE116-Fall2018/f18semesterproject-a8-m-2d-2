package code.game.littlespider;


import code.cards.Card;
import code.cards.Deck;


/**
 * This class holds the code for the Little Spider game. Its constructor initializes four homecell piles and eight tableau piles.
 * 
 * @author Drew Fiutko
 */
public class LittleSpider {
	
	/**
	 * Holds an array of Homecell instances that are used by the game.
	 */
	private Homecell[] homecells;
	
	/**
	 * Holds an array of Tableau instances that are used by the game.
	 */
	private Tableau[] tableaus;

	/**
	 * Starts a little spider game.
	 */
	public LittleSpider() {
		init();
	}
	/**
	 * Creates Homecell and Tableau piles for Little Spider.
	 */
	private void init() {
		Deck deck = new Deck();
		
		homecells = new Homecell[4];
		
		Card c1 = deck.getSpecificCard("diamond", "ace");
		c1.setFaceUp();
		Homecell homecell1 = new Homecell(c1);
		homecells[0] = homecell1;
		
		Card c2 = deck.getSpecificCard("heart", "ace");
		c2.setFaceUp();
		Homecell homecell2 = new Homecell(c2);
		homecells[1] = homecell2;
		
		Card c3 = deck.getSpecificCard("club", "king");
		c3.setFaceUp();
		Homecell homecell3 = new Homecell(c3);
		homecells[2] = homecell3;
		
		Card c4 = deck.getSpecificCard("spade", "king");
		c4.setFaceUp();
		Homecell homecell4 = new Homecell(c4);
		homecells[3] = homecell4;
		
		tableaus = new Tableau[8];
		for(int i=0; i<tableaus.length; i++) {
			Tableau tableau = new Tableau();
			for(int j=1; j<=6; j++) {
				Card x = deck.takeCard();
				x.setFaceUp();
				tableau.addCard(x, true);
			}
			tableaus[i]= tableau;
		}
	}
	/**
	 * Returns homecell array.
	 * 
	 * @return Array of homecell instances.
	 */
	public Homecell[] getHomecells() {
		return homecells;
	}
	/**
	 * Returns array of tableaus.
	 * 
	 * @return Array of tableau instances.
	 */
	public Tableau[] getTableaus() {
		return tableaus;
	}
}
