package tests.game.littlespider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.cards.Card;
import code.game.littlespider.Tableau;

public class TableauTest {

	private Tableau t;
	
	public void refresh() {
		t = new Tableau();
	}
	
	/**
	 * Tests that list in tableau is properly instantiated.
	 */
	@Test
	public void tableauTest() {
		refresh();
		assertNotNull(t.getCards());
	}
	/**
	 * Tests addCard() method according to game rules.
	 */
	@Test
	public void addCardTest() {
		refresh();
		Card c = new Card(0);
		Card c1 = new Card(1);
		Card c2 = new Card(6);
		
		//check that cards can't be added to a empty pile
		assertFalse("addCard returned true when adding to empty pile", t.addCard(c1, false));
		t.addCard(c, true);
		
		//checks normal adds
		assertTrue("addCard returned false when doing a legal add", t.addCard(c1, false));
		assertEquals("did not update list after adding card", c1, t.getCards().get(0));
		assertEquals("did not update top card after adding card", c1, t.getCard());
		assertFalse("returned true on an illegal add", t.addCard(c2, false));
		assertEquals("changed topCard after an illegal add", c1, t.getCard());
		assertEquals("changed list after illegal add", c1, t.getCards().get(0));
		assertEquals("did not update number of cards", 2, t.getNumCards());
		
		//test wrap around function
		refresh();
		t.addCard(c, true);
		Card c3 = new Card(12);
		assertTrue("wrap around function not working properly", t.addCard(c3, false));
		refresh();
		t.addCard(c3, true);
		assertTrue("wrap around function not working properly", t.addCard(c, false));
		
		//test null add
		Card x = null;
		assertFalse("addCard not working properly on null add", t.addCard(x, false));
		
		//test override
		refresh();
	}
	
	
	@Test
	public void getNumCardsTest() {
		refresh();
		Card c = new Card(0);
		Card c1 = new Card(1);
		t.addCard(c, true);
		t.addCard(c1, false);
		assertEquals(2, t.getCards().size());
	}
	
	@Test
	public void getCardTest() {
		refresh();
		assertNull("does not return null on empty pile", t.getCard());
		Card c = new Card(0);
		t.addCard(c, true);
		assertEquals("does not return topCard", c, t.getCard());
	}
	
	@Test
	public void takeCardTest() {
		refresh();
		assertNull("returns an object on empty pile", t.takeCard());
		Card c = new Card(0);
		Card c1 = new Card(1);
		t.addCard(c, true);
		t.addCard(c1, false);
		assertEquals("does not take correct card", c1, t.takeCard());
		assertEquals("does not remove card from list", c, t.getCards().get(0));
		assertEquals("does not update top card", c, t.getCard());
		assertEquals("did not update number of cards", 1, t.getNumCards());
	}
}
