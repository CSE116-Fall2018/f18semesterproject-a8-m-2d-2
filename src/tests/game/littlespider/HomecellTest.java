package tests.game.littlespider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.cards.Card;
import code.cards.Deck;
import code.game.littlespider.Homecell;

public class HomecellTest {
	
	private Deck deck;
	private Homecell h;
	
	public void refresh() {
		deck = new Deck();
		Card c = new Card(0, false);
		h = new Homecell(c);
	}
	
	/**
	 * Tests that homecell piles are initiated correctly.
	 */
	@Test
	public void homecellTest() {
		refresh();
		assertNotNull("No top card", h.getCard());
		assertNotNull("No list of cards", h.getCards());
		Card c = h.getCards().get(0);
		assertEquals("Top card does not match index or 0", c, h.getCard());
	}
	/**
	 * Tests addCard() according to game rules.
	 */
	@Test
	public void addCardTest() {
		refresh();
		//make cards for testing
		Card d2 = new Card(1, false);
		Card ha = new Card(13, false);
		Card h2 = new Card(14, false);
		Card h5 = new Card(17, false);
		Card ck = new Card(51, false);
		Card cq = new Card(50, false);
		Card sk = new Card(38, false);
		Card s9 = new Card(34, false);
		
		assertTrue("addCard returned false on a legal move", h.addCard(d2));
		assertEquals("topCard not properly updated", d2, h.getCard());
		assertEquals("list not properly updated", d2, h.getCards().get(0));
		assertFalse(h.addCard(s9));
		
		Homecell t2 = new Homecell(ha);
		assertTrue("addCard returned false on a legal move", t2.addCard(h2));
		assertFalse("addCard returned true on illegal move", t2.addCard(h5));
	}
	
	@Test
	public void removeCardTest() {
		refresh();
	}
	
	@Test
	public void getNumCardsTest() {
		refresh();
	}
	
	
	@Test
	public void takeCardTest() {
		refresh();
	}
	
}
