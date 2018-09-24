package tests.game.golf;

import org.junit.Test;

import code.cards.Card;
import code.cards.Deck;
import code.game.golf.Stockpile;
import code.game.golf.Tableau;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

public class StockpileTest {
	
	@Test
	public void testStockpileInit() {
		Deck d = new Deck();
		Stockpile s = new Stockpile(d.getDeck());
		assertNotNull("Stockpile should properly initiate when given an ArrayList of cards", s);
	}
	
	@Test
	public void testAddCard() {
		Deck d = new Deck();
		d.shuffle();
		Stockpile s = new Stockpile(d.getDeck());
		Card c = d.takeCard();
		Card c2 = d.takeCard();
		assertFalse("Stockpile should not allow any cards to be added and always return false", s.addCard(c));
		assertFalse("Stockpile should not allow any cards to be added and always return false", s.addCard(c2));
	}
	
	@Test
	public void testGetNumCards() {
		Deck d = new Deck();
		d.shuffle();
		Stockpile s = new Stockpile(d.getDeck());
		Card c = d.takeCard();
		Card c2 = d.takeCard();
		ArrayList<Card> arr = new ArrayList<>();
		arr.add(c);
		arr.add(c2);
		Stockpile s2 = new Stockpile(arr);
		Stockpile s3 = new Stockpile(new ArrayList<>());
		assertEquals("Stockpile should return 2 as number of cards", 2, s2.getNumCards());
		assertEquals("Stockpile should return 50 as number of cards", 50, s.getNumCards());
		assertEquals("Stockpile should return 0 as number of cards", 0, s3.getNumCards());
	}
	
	@Test
	public void testGetCard() {
		Deck d = new Deck();
		d.shuffle();
		Stockpile s = new Stockpile(d.getDeck());
		Stockpile s2 = new Stockpile(new ArrayList<>());
		Card c = d.getCard();
		assertNull("Stockpile should return null when trying to get a card from an empty Stockpile", s2.getCard());
		assertEquals("getCard() should return the card at position 0", c, s.getCard());
		c = s.takeCard();
		c = s.getCard();
		assertEquals("getCard() should return the card at position 0", c, s.getCard());
		d = new Deck();
		d.shuffle();
		// Takes all the cards from the Stockpile
		for (int i = 0; i < 52; i++) {
			c = s.takeCard();
		}
		assertNull("Stockpile should return null when trying to get a card from an empty Stockpile", s.getCard());
	}
	
	@Test
	public void testTakeCard() {
		Deck d = new Deck();
		d.shuffle();
		Stockpile s = new Stockpile(d.getDeck());
		Stockpile s2 = new Stockpile(new ArrayList<>());
		assertNull("Stockpile should return null when trying to get a card from an empty Stockpile", s2.getCard());
		Card c = d.getCard();
		Card c2 = s.takeCard();
		assertEquals("takeCard() should return the card at position 0", c, c2);
		assertEquals("takeCard() should decrease the amount of cards in the pile by one", 51, s.getNumCards());
		d = new Deck();
		d.shuffle();
		for (int i = 0; i < 52; i++) {
			c = s.takeCard();
		}
		assertNull("Stockpile should return null when trying to take a card from an empty Stockpile", s.takeCard());
	}

}
