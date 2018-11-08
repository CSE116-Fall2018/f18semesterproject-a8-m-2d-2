package tests.game.fortythieves;

import org.junit.Test;

import code.cards.Card;
import code.cards.Deck;
import code.game.fortythieves.FortyThieves;
import code.game.fortythieves.Homecell;
import code.game.gui.GUI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class HomecellTest {
	
	@Test
	public void sizeTest() {
		Card c = new Card(1,null);
		GUI gui = new GUI();
		Homecell h = new Homecell(c, new FortyThieves(gui));
		assertNotNull(h);
		assertEquals(1,h.getAllCards().size());
	}
	
	@Test public void addTest() {
		Card c = new Card(1,null);
		GUI gui = new GUI();
		Homecell h = new Homecell(c, new FortyThieves(gui));
		assertTrue(h.addCard(new Card(2,null), false));
		assertTrue(h.addCard(new Card(3,null), false));
		assertEquals(3,h.getAllCards().size());
		assertFalse(h.addCard(new Card(5,null), false));
		
		assertTrue(h.addCard(new Card(12,null), true));
		assertFalse(h.addCard(new Card(13,null), false));
		h.addCard(new Card(13,null), true);
		assertTrue(h.addCard(new Card(14,null),false));
		assertEquals(6,h.getAllCards().size());

		h.addCard(new Card(25,null), true);
		assertFalse(h.addCard(new Card(26,null), false));
		h.addCard(new Card(26,null), true);
		assertTrue(h.addCard(new Card(27,null),false));
		assertEquals(9,h.getAllCards().size());

		h.addCard(new Card(38,null), true);
		assertFalse(h.addCard(new Card(39,null), false));
		h.addCard(new Card(39,null), true);
		assertTrue(h.addCard(new Card(40,null),false));
		assertEquals(12,h.getAllCards().size());

	}
	
	@Test public void removeTest() {
		GUI gui = new GUI();
		Deck deck = new Deck(new FortyThieves(gui));
		while(deck.getAllCards().size() != 0) {
			Card c = deck.takeCard();
			Homecell h = new Homecell(c, new FortyThieves(gui));
			assertNull(h.takeCard());
		}
	}

}
