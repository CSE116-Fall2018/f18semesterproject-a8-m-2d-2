package tests.game.golf;

import org.junit.Test;

import code.cards.Card;
import code.cards.Deck;
import code.game.golf.Tableau;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

public class TableauTest {
	
	@Test
	public void testAddCard() {
		Deck d = new Deck(null);
		d.shuffle();
		Tableau t = new Tableau();
		assertTrue("Tableau should return true when adding an overridden", t.addCard(d.takeCard(), true));
		assertFalse("Tableau should return false when adding a card", t.addCard(d.takeCard(), false));
	}
	
	@Test
	public void testTakeCard() {
		Deck d = new Deck(null);
		d.shuffle();
		Tableau t = new Tableau();
		assertNull("Tableaus should return null when trying to remove a card from an empty Tableau", t.takeCard());
		Card c = d.takeCard();
		t.addCard(c, true);
		assertEquals("takeCard() should return the most recently added card to it", c, t.takeCard());
		assertNull("Tableaus should return null when trying to remove a card from an empty Tableau", t.takeCard());
		Card c2 = d.takeCard();
		Card c3 = d.takeCard();
		t.addCard(c2, true);
		t.addCard(c3, true);
		assertEquals("takeCard() should return the most recently added card to it", c3, t.takeCard());
		assertEquals("takeCard() should return the most recently added card to it", c2, t.takeCard());
		assertNull("Tableaus should return null when trying to remove a card from an empty Tableau", t.takeCard());
	}
	
	@Test
	public void testGetNumCards() {
		Deck d = new Deck(null);
		d.shuffle();
		Tableau t = new Tableau();
		assertEquals("Tableaus should instantiate with zero cards", 0, t.getNumCards());
		t.addCard(d.takeCard(), true);
		t.addCard(d.takeCard(), true);
		t.addCard(d.takeCard(), true);
		assertEquals("testGetNumCards should return 3 cards when 3 are added", 3, t.getNumCards());
		@SuppressWarnings("unused")
		Card c = t.takeCard();
		c = t.takeCard();
		assertEquals("testGetNumCards should return 2 cards when 2 are added and 1 is removed", 1, t.getNumCards());
		c = t.takeCard();
		assertEquals("testGetNumCards should return 0 cards when both are removed", 0, t.getNumCards());
	}
	
	@Test
	public void testGetCard() {
		Deck d = new Deck(null);
		d.shuffle();
		Tableau t = new Tableau();
		Card c = d.takeCard();
		Card c2 = d.takeCard();
		Card c3 = d.takeCard();
		assertNull("Tableaus should return null when trying to get a card from an empty Tableau", t.getCard());
		t.addCard(c, true);
		assertEquals("getCard() should return the card at position 0", c, t.getCard());
		c = t.takeCard();
		assertNull("Tableaus should return null when trying to get a card from an empty Tableau", t.getCard());
		t.addCard(c2, true);
		t.addCard(c3, true);
		assertEquals("getCard() should return the card at position 0", c3, t.getCard());
		c3 = t.takeCard();
		assertEquals("getCard() should return the card at position 0", c2, t.getCard());
	}

}
