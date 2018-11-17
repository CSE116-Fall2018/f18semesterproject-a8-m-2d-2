package tests.game.fortythieves;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.cards.Card;
import code.cards.Deck;
import code.game.fortythieves.Wastepile;

public class WastepileTest {
	@Test
	public void testStockpileInit() {
	Wastepile h = new Wastepile(null);
		assertNotNull("Stockpile should properly initiate when given an ArrayList of cards", h);
	}
	
	@Test
	public void testAddCard() {
		Deck d = new Deck(null);
		Wastepile h = new Wastepile(null); 
		Card c = d.takeCard();
		Card c2 = d.takeCard();
		Card c4 = d.takeCard();
		Card c5 = d.takeCard();
		Card c6 = null;
		assertFalse("Wastepile should never allow adding a null card", h.addCard(c6, false));
		assertTrue("Wastepile should always allow the first card to be added to it", h.addCard(c, false));
		assertEquals("Most recently added card should be on top of the Wastepile pile", c, h.getCard());
		assertTrue("Wastepile should allow cards to be added if rank differs by 1", h.addCard(c2, false));
		assertEquals("Most recently added card should be on top of the Wastepile pile", c2, h.getCard());
		assertTrue("Wastepile should allow cards to be added if they come from the Stockpile", h.addCard(c5, false));
		assertTrue("Wastepile should allow cards to be added if rank differs by 1", h.addCard(c4, false));
		assertEquals("Most recently added card should be on top of the Wastepile pile", c4, h.getCard());
	}
	
	@Test
	public void testGetNumCards() {
		Deck d = new Deck(null);
		Wastepile h = new Wastepile(null);
		assertEquals("Wastepile should return 0 as number of cards after at initialization", 0, h.getNumCards());
		Card c = d.takeCard();
		h.addCard(c, true);
		assertEquals("Wastepile should return 1 as number of cards after 1 is added", 1, h.getNumCards());
		Card c2 = d.takeCard();
		h.addCard(c2, false);
		assertEquals("Wastepile should return 2 as number of cards after 2 are added", 2, h.getNumCards());
	}
	
	@Test
	public void testGetCard() {
		Deck d = new Deck(null);
		Wastepile h = new Wastepile(null);
		assertNull("Wastepile should return null when trying to get a card from an empty Stockpile", h.getCard());
		Card c = d.takeCard();
		h.addCard(c, true);
		assertEquals("getCard() should return the card at position 0", c, h.getCard());
		Card c2 = d.takeCard();
		h.addCard(c2, true);
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
		Wastepile h = new Wastepile(null);
		Card c = d.takeCard();
		h.addCard(c, false);
		assertEquals("Wastepile should return the card on top when taking a card", c, h.takeCard());
		d.shuffle();
		Card c2 = d.takeCard();
		h.addCard(c2, false);
		assertEquals("Wastepile should return the card on top when taking a card", c2, h.takeCard());
	}

}
