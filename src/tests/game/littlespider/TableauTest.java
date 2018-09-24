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
		Card c = new Card(0, false);
		Card c1 = new Card(1, false);
		Card c2 = new Card(6, false);
		
		//check that cards can't be added to a empty pile
		assertFalse("addCard returned true when adding first card", t.addCard(c1));
		t.addFirstCard(c);
		
		//checks normal adds
		assertTrue("addCard returned false when doing a legal add", t.addCard(c1));
		assertEquals("did not update list after adding card", c1, t.getCards().get(0));
		assertEquals("did not update top card after adding card", c1, t.getCard());
		assertFalse("returned true on an illegal add", t.addCard(c2));
		assertEquals("changed topCard after an illegal add", c1, t.getCard());
		assertEquals("changed list after illegal add", c1, t.getCards().get(0));
		
		//test wrap around function
		refresh();
		t.addFirstCard(c);
		Card c3 = new Card(12, false);
		assertTrue("wrap around function not working properly", t.addCard(c3));
		refresh();
		t.addFirstCard(c3);
		assertTrue("wrap around function not working properly", t.addCard(c));
	}
	
	
	@Test
	public void getNumCardsTest() {
		refresh();
		Card c = new Card(0, false);
		Card c1 = new Card(1, false);
		t.addFirstCard(c);
		t.addCard(c1);
		assertEquals(2, t.getCards().size());
	}
	
	@Test
	public void takeCardTest() {
		refresh();
		assertNull(t.takeCard());
		Card c = new Card(0, false);
		Card c1 = new Card(1, false);
		t.addFirstCard(c);
		t.addCard(c1);
		assertEquals("does not take correct card", c1, t.takeCard());
		assertEquals("does not remove card from list", c, t.getCards().get(0));
		assertEquals("does not update top card", c, t.getCard());
	}
	
	@Test
	public void addFirstCardTest() {
		refresh();
		Card c = new Card(0, false);
		t.addFirstCard(c);
		assertEquals("does not update top card correctly", c, t.getCard());
		assertEquals("does not update list correctly", c, t.getCards().get(0));
		
	}
}
