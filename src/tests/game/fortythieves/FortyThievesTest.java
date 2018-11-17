package tests.game.fortythieves;

import static org.junit.Assert.assertEquals;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import code.cards.Card;
import code.cards.Pile;
import code.game.fortythieves.FortyThieves;
import code.game.gui.GUI;
import code.game.fortythieves.Homecell;

public class FortyThievesTest {

	private FortyThieves game;
	
	/**
	 * Starts a new LittleSpider instance for later testing.
	 */
	
	@Before
	public void newGame() {
		GUI gui = new GUI();
		FortyThieves game = new FortyThieves(gui);
		this.game = game;
		// This triggers the game's action event, triggering init()
		game.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null) {
			private static final long serialVersionUID = 1L;
		});
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
		assertEquals("Wrong number of Tableau piles", 13, tableauSize);
		assertEquals("Wrong number of Homecell piles", 8, homecellSize);
		
		//test correct number of cards in tableau
		for(int i=0; i<tableaus.length; i++) {
			int j = i +1;
			String message = "Wrong number of cards in Tableau pile " + j;
			Pile x = tableaus[i];
			ArrayList<Card> m = x.getAllCards();
			int size = m.size();
			// NOTE: This is 4 because we have an empty, non-playable card
			// that serves as our 'empty pile' so that when a tableau is 'empty'
			// e.g. size() 1, playable cards from other tableaus can be added to it
			assertEquals(message, 4, size);
		}
		
		//test homecell has correct card
		Card h1 = homecells[0].getCard();
		assertEquals("ace", h1.getRank());
		assertEquals("diamond", h1.getSuit());
		
		Card h2 = homecells[1].getCard();
		assertEquals("ace", h2.getRank());
		assertEquals("heart", h2.getSuit());
		
		Card h3 = homecells[2].getCard();
		assertEquals("ace", h3.getRank());
		assertEquals("club", h3.getSuit());
		
		Card h4 = homecells[3].getCard();
		assertEquals("ace", h4.getRank());
		assertEquals("spade", h4.getSuit());
		
		Card h5 = homecells[4].getCard();
		assertEquals("ace", h5.getRank());
		assertEquals("diamond", h5.getSuit());

		Card h6 = homecells[5].getCard();
		assertEquals("ace", h6.getRank());
		assertEquals("heart", h6.getSuit());

		Card h7 = homecells[6].getCard();
		assertEquals("ace", h7.getRank());
		assertEquals("club", h7.getSuit());

		Card h8 = homecells[7].getCard();
		assertEquals("ace", h8.getRank());
		assertEquals("spade", h8.getSuit());
		
	}
}
