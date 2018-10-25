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
		Card c = new Card(0,null);
		h = new Homecell(c, null);
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
		Card d2 = new Card(1,null);
		Card ha = new Card(13,null);
		Card h2 = new Card(14,null);
		Card h5 = new Card(17,null);
		Card ck = new Card(51,null);
		Card cq = new Card(50,null);
		Card sk = new Card(38,null);
		Card sq = new Card(37,null);
		Card s9 = new Card(34,null);
		
		//test diamonds if statement
		assertTrue("addCard returned false on a legal move", h.addCard(d2,false));
		assertEquals("topCard not properly updated", d2, h.getCard());
		assertEquals("list not properly updated", d2, h.getCards().get(0));
		assertFalse("card returned true on an illegal move", h.addCard(s9,false));
		assertEquals("topCard updated after illegal move", d2, h.getCard());
		assertEquals("list updated after illegal move", d2, h.getCards().get(0));
		assertEquals("did not update number of cards", 2, h.getNumCards());
		
		//test hearts
		Homecell t2 = new Homecell(ha, null);
		assertTrue("addCard returned false on a legal move", t2.addCard(h2, false));
		assertFalse("addCard returned true on illegal move", t2.addCard(h5, false));
		
		//test clubs
		Homecell t3 = new Homecell(ck, null);
		assertTrue("addCard retruned false on legal move", t3.addCard(cq, false));
		assertEquals("topCard not properly updated", cq, t3.getCard());
		assertEquals("list not properly updated", cq, t3.getCards().get(0));
		assertFalse("card returned true on an illegal move", t3.addCard(sk, false));
		assertEquals("topCard updated after illegal move", cq, t3.getCard());
		assertEquals("list updated after illegal move", cq, t3.getCards().get(0));
		assertEquals("did not update number of cards", 2, t3.getNumCards());
		
		//test spades
		Homecell t4 = new Homecell(sk, null);
		assertTrue("addCard returned false on legal move", t4.addCard(sq, false));
		assertFalse("addCard returned true on illegal move", t4.addCard(s9, false));
		
		//test null add
		Card x = null;
		assertFalse("addCard not working properly on null add", h.addCard(x, false));
	}

	
	@Test
	public void getNumCardsTest() {
		refresh();
		assertEquals("incorrect number of cards displayed", 1, h.getNumCards());
		Card ee = new Card(1,null);
		h.addCard(ee, false);
		assertEquals("incorrect number of cards displayed", 2, h.getNumCards());
	}
	
	@Test
	public void getCardTest() {
		refresh();
		Card c1 = new Card(1,null);
		h.addCard(c1, false);
		assertEquals("does not return topCard", c1, h.getCard());
	}
	
	@Test
	public void takeCardTest() {
		refresh();
		//test that card null is returned when last card is taken
		assertNull(h.takeCard());
		//test that old topCard is taken and removed
		Card e = new Card(1,null);
		Card e1 = new Card(2,null);
		h.addCard(e, false);
		h.addCard(e1, false);
		assertEquals("did not return card that was taken", e1, h.takeCard());
		assertEquals("did not update top card", e, h.getCard());
		assertEquals("did not update list correctly", e, h.getCard());
		assertEquals("did not update number of cards", 2, h.getNumCards());
	}
	
}
