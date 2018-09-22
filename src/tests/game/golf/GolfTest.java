package tests.game.golf;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import code.game.golf.Golf;
import code.game.golf.Homecell;
import code.game.golf.Stockpile;
import code.game.golf.Tableau;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GolfTest {
	Golf g;
	
	@Before
	public void createGame() {
		g = new Golf();
	}
	
	@Test
	public void testTableaus() {
		Tableau[] t = g.getTableaus();
		assertNotNull("getTableaus() should not return null", t);
		assertEquals("getTableaus() should return a Tableau[] array of length 7", t.length, 7);
		// Converts Tableau[] to a list & adds it to a HashSet, which cannt accept duplicates, and checks size
		// There should be no duplicates because every card is unique, so every Tableau should be
		assertEquals("getTableaus() should return an array with no duplicates", new HashSet<Tableau>(Arrays.asList(t)).size(), 7);
		for (Tableau b : t) {
			assertEquals("All Tableaus should be instantiated with 5 cards", b.getNumCards(), 5);
		}
	}
	
	@Test
	public void testHomecell() {
		Homecell h = g.getHomecell();
		assertNotNull("Golf's Homecell should not be null", h);
		assertEquals("There should be no cards in the homecell on init", h.getNumCards(), 0);
	}
	
	@Test
	public void testStockpile() {
		Stockpile s = g.getStockpile();
		assertNotNull("Golf's Stockpile should not be null", s);
		assertEquals("There should be 17 cards in the Stockpile on init", s.getNumCards(), 17);
	}

}
