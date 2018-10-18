package tests.game.golf;

import org.junit.Test;

import code.cards.Card;
import code.cards.Deck;
import code.game.golf.Homecell;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class HomecellTest {
	
	@Test
	public void testStockpileInit() {
		Homecell h = new Homecell();
		assertNotNull("Stockpile should properly initiate when given an ArrayList of cards", h);
	}
	
	@Test
	public void testAddCard() {
		Deck d = new Deck(null);
		Homecell h = new Homecell(); 
		Card c = d.takeCard();
		Card c2 = d.takeCard();
		Card c3 = d.takeCard();
		Card c4 = d.takeCard();
		Card c5 = d.takeCard();
		Card c6 = null;
		assertFalse("Homecell should never allow adding a null card", h.addCard(c6, false));
		assertTrue("Homecell should always allow the first card to be added to it", h.addCard(c, false));
		assertEquals("Most recently added card should be on top of the Homecell pile", c, h.getCard());
		assertFalse("Homecell should not allow cards to be added if rank differs by > 1", h.addCard(c3, false));
		assertTrue("Homecell should allow cards to be added if rank differs by 1", h.addCard(c2, false));
		assertEquals("Most recently added card should be on top of the Homecell pile", c2, h.getCard());
		assertTrue("Homecell should allow cards to be added if they come from the Stockpile", h.addCard(c5, true));
		assertTrue("Homecell should allow cards to be added if rank differs by 1", h.addCard(c4, false));
		assertEquals("Most recently added card should be on top of the Homecell pile", c4, h.getCard());
		assertTrue("Homecell should allow cards to be added if rank differs by 1", h.addCard(c3, false));
	}
	
	@Test
	public void testGetNumCards() {
		Deck d = new Deck(null);
		Homecell h = new Homecell();
		assertEquals("Homecell should return 0 as number of cards after at initialization", 0, h.getNumCards());
		Card c = d.takeCard();
		h.addCard(c, false);
		assertEquals("Homecell should return 1 as number of cards after 1 is added", 1, h.getNumCards());
		Card c2 = d.takeCard();
		h.addCard(c2, false);
		assertEquals("Homecell should return 2 as number of cards after 2 are added", 2, h.getNumCards());
	}
	
	@Test
	public void testGetCard() {
		Deck d = new Deck(null);
		Homecell h = new Homecell();
		assertNull("Homecell should return null when trying to get a card from an empty Stockpile", h.getCard());
		Card c = d.takeCard();
		h.addCard(c, false);
		assertEquals("getCard() should return the card at position 0", c, h.getCard());
		Card c2 = d.takeCard();
		h.addCard(c2, false);
		assertEquals("getCard() should return the card at position 0", c2, h.getCard());
		d = new Deck(null);
		// Try a random card
		d.shuffle();
		Card c3 = d.takeCard();
		h.addCard(c3, true);
		assertEquals("getCard() should return the card at position 0", c3, h.getCard());
	}
	
	@Test
	public void testTakeCard() {
		Deck d = new Deck(null);
		Homecell h = new Homecell();
		h.addCard(d.takeCard(), false);
		assertNull("Homecell should always return null when trying to take a card", h.takeCard());
		d.shuffle();
		h.addCard(d.takeCard(), false);
		assertNull("Homecell should always return null when trying to take a card", h.takeCard());
	}
}
