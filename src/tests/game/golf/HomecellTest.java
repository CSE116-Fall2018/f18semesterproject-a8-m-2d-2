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
		Deck d = new Deck();
		Homecell h = new Homecell();
		Card c = d.takeCard();
		Card c2 = d.takeCard();
		Card c3 = d.takeCard();
		Card c4 = d.takeCard();
		Card c5 = d.takeCard();
		assertTrue("Homecell should always allow the first card to be added to it", h.addCard(c));
		assertFalse("Homecell should not allow cards to be added if rank differs by > 1", h.addCard(c3));
		assertTrue("Homecell should allow cards to be added if rank differs by 1", h.addCard(c2));
		assertTrue("Homecell should allow cards to be added if they come from the Stockpile", h.addCard(true, c5));
		assertTrue("Homecell should allow cards to be added if rank differs by 1", h.addCard(c4));
		assertTrue("Homecell should allow cards to be added if rank differs by 1", h.addCard(c3));
	}
	
	@Test
	public void testGetNumCards() {
		Deck d = new Deck();
		Homecell h = new Homecell();
		assertEquals("Homecell should return 0 as number of cards after at initialization", 0, h.getNumCards());
		Card c = d.takeCard();
		h.addCard(c);
		assertEquals("Homecell should return 1 as number of cards after 1 is added", 1, h.getNumCards());
		Card c2 = d.takeCard();
		h.addCard(c2);
		assertEquals("Homecell should return 2 as number of cards after 2 are added", 2, h.getNumCards());
	}
	
	@Test
	public void testGetCard() {
		Deck d = new Deck();
		Homecell h = new Homecell();
		assertNull("Homecell should return null when trying to get a card from an empty Stockpile", h.getCard());
		Card c = d.takeCard();
		h.addCard(c);
		assertEquals("getCard() should return the card at position 0", c, h.getCard());
		Card c2 = d.takeCard();
		h.addCard(c2);
		assertEquals("getCard() should return the card at position 0", c2, h.getCard());
		d = new Deck();
		// Try a random card
		d.shuffle();
		Card c3 = d.takeCard();
		h.addCard(true, c3);
		assertEquals("getCard() should return the card at position 0", c3, h.getCard());
	}
	
	@Test
	public void testTakeCard() {
		Deck d = new Deck();
		Homecell h = new Homecell();
		h.addCard(true, d.takeCard());
		assertNull("Homecell should always return null when trying to take a card", h.takeCard());
		d.shuffle();
		h.addCard(true, d.takeCard());
		assertNull("Homecell should always return null when trying to take a card", h.takeCard());
		h.addCard(true, d.takeCard());
		assertNull("Homecell should always return null when trying to take a card", h.takeCard());
	}
}
