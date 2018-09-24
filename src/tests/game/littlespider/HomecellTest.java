package tests.game.littlespider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.cards.Card;
import code.game.littlespider.Homecell;

public class HomecellTest {
	
	private Homecell h;
	
	public void refresh() {
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
		Card sq = new Card(37, false);
		Card s9 = new Card(34, false);
		
		//test diamonds if statement
		assertTrue("addCard returned false on a legal move", h.addCard(d2));
		assertEquals("topCard not properly updated", d2, h.getCard());
		assertEquals("list not properly updated", d2, h.getCards().get(0));
		assertFalse("card returned true on an illegal move", h.addCard(s9));
		assertEquals("topCard updated after illegal move", d2, h.getCard());
		assertEquals("list updated after illegal move", d2, h.getCards().get(0));
		
		//test hearts
		Homecell t2 = new Homecell(ha);
		assertTrue("addCard returned false on a legal move", t2.addCard(h2));
		assertFalse("addCard returned true on illegal move", t2.addCard(h5));
		
		//test clubs
		Homecell t3 = new Homecell(ck);
		assertTrue("addCard retruned false on legal move", t3.addCard(cq));
		assertEquals("topCard not properly updated", cq, t3.getCard());
		assertEquals("list not properly updated", cq, t3.getCards().get(0));
		assertFalse("card returned true on an illegal move", t3.addCard(sk));
		assertEquals("topCard updated after illegal move", cq, t3.getCard());
		assertEquals("list updated after illegal move", cq, t3.getCards().get(0));
		
		//test spades
		Homecell t4 = new Homecell(sk);
		assertTrue("addCard returned false on legal move", t4.addCard(sq));
		assertFalse("addCard returned true on illegal move", t4.addCard(s9));
	}
	
	@Test
	public void removeCardTest() {
		refresh();
		//test no change. cannot take top card
		Card c = h.getCard();
		h.removeCard();
		assertEquals("topCard changed after attempting to take last card", c, h.getCard());
		assertEquals("list changed after attempting to take last card", c, h.getCard());
		//test that card is properly removed
		Card c2 = new Card(1, false);
		Card c3 = new Card(2, false);
		h.addCard(c2);
		h.addCard(c3);
		h.removeCard();
		assertEquals("did not remove card from list", c2, h.getCards().get(0));
		assertEquals("did not update top card after removal", c2, h.getCard());
		
	}
	
	@Test
	public void getNumCardsTest() {
		refresh();
		assertEquals("incorrect number of cards displayed", 1, h.getNumCards());
		Card ee = new Card(1, false);
		h.addCard(ee);
		assertEquals("incorrect number of cards displayed", 2, h.getNumCards());
	}
	
	
	@Test
	public void takeCardTest() {
		refresh();
		//test that card null is returned when last card is taken
		assertNull(h.takeCard());
		//test that old topCard is taken and removed
		Card e = new Card(1, false);
		Card e1 = new Card(2,false);
		h.addCard(e);
		h.addCard(e1);
		assertEquals("did not return card that was taken", e1, h.takeCard());
		assertEquals("did not remove card", e, h.getCard());
	}
	
}
