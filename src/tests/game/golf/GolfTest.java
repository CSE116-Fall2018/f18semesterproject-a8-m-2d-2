package tests.game.golf;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import code.game.golf.Golf;
import code.game.golf.Tableau;

import static org.junit.Assert.assertEquals;

public class GolfTest {
	
	@Test
	public void testTableaus() {
		Golf g = new Golf();
		Tableau[] t = g.getTableaus();
		assertEquals("getTableaus() should return a Tableau[] array of length 7", t.length, 7);
		// Converts Tableau[] to a list & adds it to a HashSet, which cannt accept duplicates, and checks size
		// There should be no duplicates because every card is unique, so every Tableau should be
		assertEquals("getTableaus() should return an array with no duplicates", new HashSet<Tableau>(Arrays.asList(t)).size(), 7);
		for (Tableau b : t) {
			assertEquals("All Tableaus should be instantiated with 5 cards", b.getNumCards(), 5);
		}
	}

}
