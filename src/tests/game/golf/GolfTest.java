package tests.game.golf;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import code.cards.Card;
import code.game.golf.Golf;
import code.game.golf.Homecell;
import code.game.golf.Stockpile;
import code.game.golf.Tableau;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GolfTest {
	private Golf g;
	
	@Before
	public void createGame() {
		g = new Golf();
	}
	
	@Test
	public void testTableaus() {
		Tableau[] t = g.getTableaus();
		assertEquals("getTableaus() should return a Tableau[] array of length 7", 7, t.length);
		// Converts Tableau[] to a list & adds it to a HashSet, which cannot accept duplicates, and checks size
		// There should be no duplicates because every card is unique, so every Tableau should be
		assertEquals("getTableaus() should return an array with no duplicates", 7, new HashSet<Tableau>(Arrays.asList(t)).size());
		
		for (Tableau b : t) {
			assertNotNull("No tableaus should be null", b);
			assertEquals("All Tableaus should be instantiated with 5 cards", 5, b.getNumCards());
			Card[] cards = new Card[5];
			cards[0] = b.takeCard();
			cards[1] = b.takeCard();
			cards[2] = b.takeCard();
			cards[3] = b.takeCard();
			cards[4] = b.takeCard();
			for (Card c : cards) {
				assertTrue("All cards should be instantiated as face up in Tableaus", c.faceUp);
			}
		}
	}
	
	@Test
	public void testHomecell() {
		Homecell h = g.getHomecell();
		assertNotNull("Golf's Homecell should not be null", h);
		assertEquals("There should be no cards in the homecell on init", 0, h.getNumCards());
	}
	
	@Test
	public void testStockpile() {
		Stockpile s = g.getStockpile();
		assertNotNull("Golf's Stockpile should not be null", s);
		assertEquals("There should be 17 cards in the Stockpile on init", 17, s.getNumCards());
	}

}
