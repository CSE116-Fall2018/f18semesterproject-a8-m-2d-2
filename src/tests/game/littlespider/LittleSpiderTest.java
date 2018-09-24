package tests.game.littlespider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import code.cards.Deck;
import code.game.littlespider.Homecell;
import code.game.littlespider.LittleSpider;
import code.game.littlespider.Tableau;

public class LittleSpiderTest {

	private LittleSpider game;
	
	/**
	 * Starts a new LittleSpider instance for later testing.
	 */
	
	@Before
	public void newGame() {
		LittleSpider game = new LittleSpider();
		this.game = game;
	}
	
	/**
	 * Test LittleSpider constructor from the newGame() call.  If this test fails
	 * all others will as well.
	 */
	
	@Test
	public void testLittleSpider() {
		Deck deck = game.getDeck();
		assertNotNull("No deck created", deck);
	}
	/**
	 * Tests initialization of LittleSpider game.
	 */
	
	@Test
	public void initTest() {
		Homecell[] homecells = game.getHomecells();
		Tableau[] tableaus = game.getTableaus();
		int homecellSize = homecells.length;
		int tableauSize = tableaus.length;
		
		assertEquals("Wrong number of Tableau piles", 8, tableauSize);
		assertEquals("Wrong number of Homecell piles", 4, homecellSize);
		
		
	}
}
