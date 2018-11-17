package tests.game.fortythieves;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.cards.Card;
import code.game.fortythieves.Tableau;

public class TableauTest {

	private Tableau t;
	
	public void refresh() {
		t = new Tableau();
		Card empty = new Card(53, null);
		t.addCard(empty, true);
	}
	
	/**
	 * Tests that list in tableau is properly instantiated.
	 */
	@Test
	public void tableauTest() {
		refresh();
		assertNotNull(t.getAllCards());
	}
	/**
	 * Tests addCard() method according to game rules.
	 */
	@Test
	public void addCardTest() {
		refresh();
		Card c = new Card(0,null);
		Card c1 = new Card(1,null);
		Card c2 = new Card(6,null);
		
		//check that cards can be added to a empty pile
		assertTrue("addCard returned true when adding to empty pile", t.addCard(c1, false));
		
		//checks normal adds
		assertTrue("addCard returned false when doing a legal add", t.addCard(c, false));
		assertEquals("did not update list after adding card", c, t.getCard());
		assertEquals("did not update top card after adding card", c, t.getCard());
		assertFalse("returned true on an illegal add", t.addCard(c2, false));
		assertEquals("changed topCard after an illegal add", c, t.getCard());
		assertEquals("changed list after illegal add", c, t.getCard());
		assertEquals("did not update number of cards", 2, t.getNumCards());
		
		//test null add
		Card x = null;
		assertFalse("addCard not working properly on null add", t.addCard(x, false));
	}
	
	
	@Test
	public void getNumCardsTest() {
		refresh();
		Card c = new Card(0,null);
		Card c1 = new Card(1,null);
		t.addCard(c1, false);
		assertEquals(1, t.getNumCards());
		t.addCard(c, false);
		assertEquals(2, t.getNumCards());
	}
	
	@Test
	public void getCardTest() {
		refresh();
		assertNull("does not return null on empty pile", t.getCard());
		Card c = new Card(0,null);
		t.addCard(c, true);
		assertEquals("does not return topCard", c, t.getCard());
	}
	
	@Test
	public void takeCardTest() {
		refresh();
		assertNull("returns null on empty pile", t.takeCard());
		Card c = new Card(0,null);
		Card c1 = new Card(1,null);
		t.addCard(c1, false);
		t.addCard(c, false);
		assertEquals("does not take correct card", c, t.takeCard());
		assertEquals("does not remove card from list", c1, t.getCard());
		assertEquals("does not update top card", c1, t.getCard());
		assertEquals("did not update number of cards", 1, t.getNumCards());
	}
}
