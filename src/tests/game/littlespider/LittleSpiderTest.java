package tests.game.littlespider;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import code.cards.Card;
import code.cards.Pile;
import code.game.gui.GUI;
import code.game.littlespider.Homecell;
import code.game.littlespider.LittleSpider;

public class LittleSpiderTest {

	private LittleSpider game;
	
	/**
	 * Starts a new LittleSpider instance for later testing.
	 */
	
	@Before
	public void newGame() {
		GUI gui = new GUI();
		LittleSpider game = new LittleSpider(gui);
		this.game = game;
	}
	
	/**
	 * Tests initialization of LittleSpider game.
	 */
	
	@Test
	public void initTest() {
		Homecell[] homecells = game.getHomecells();
		Pile[] tableaus = game.getTableaus();
		int homecellSize = homecells.length;
		int tableauSize = tableaus.length;
		
		//test correct number of piles
		assertEquals("Wrong number of Tableau piles", 8, tableauSize);
		assertEquals("Wrong number of Homecell piles", 4, homecellSize);
		
		//test correct number of cards in tableau
		for(int i=0; i<tableaus.length; i++) {
			int j = i +1;
			String message = "Wrong number of cards in Tableau pile " + j;
			Pile x = tableaus[i];
			ArrayList<Card> m = x.getAllCards();
			int size = m.size();
			assertEquals(message, 6, size);
		}
		
		//test homecell has correct card
		Card h1 = homecells[0].getCard();
		assertEquals("ace", h1.getRank());
		assertEquals("diamond", h1.getSuit());
		
		Card h2 = homecells[1].getCard();
		assertEquals("ace", h2.getRank());
		assertEquals("heart", h2.getSuit());
		
		Card h3 = homecells[2].getCard();
		assertEquals("king", h3.getRank());
		assertEquals("club", h3.getSuit());
		
		Card h4 = homecells[3].getCard();
		assertEquals("king", h4.getRank());
		assertEquals("spade", h4.getSuit());
		
	}
}
