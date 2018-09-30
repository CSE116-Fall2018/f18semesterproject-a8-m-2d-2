package tests.cards;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.Timeout;

import code.cards.Card;
import code.cards.Deck;

import org.junit.Test;

public class DeckTest {

	@Test
	public void sizeTest() {
		Deck test = new Deck();
		assertEquals("the deck's size is not 52",52,test.getNumCards());
	}

	@Test
	public void allUnique() {
		Deck test = new Deck();
		for(int c = 0; c < test.getNumCards(); c++) {
			for(int d = c + 1; d < test.getNumCards(); d++) {
				assertNotEquals("indecies " + c + " and " + d + " were the same card", test.getDeck().get(c).toString(), test.getDeck().get(d).toString() );
			}
		}
	}

	@Test
	public void validSuit() {
		Deck test = new Deck();
		int[] suit = new int[4];
		for(int c = 0; c < 52; c++) {
			String Suit = test.getDeck().get(c).getSuit();
			switch (Suit) {
			case "diamond":
				suit[0]++;
				break;
			case "spade":
				suit[1]++;
				break;
			case "club":
				suit[2]++;
				break;
			case "heart":
				suit[3]++;
				break;
			default:
				fail("Unrecognized suit");
			}
		}
		assertEquals("Improper number of diamonds",13,suit[0]);
		assertEquals("Improper number of spades",13,suit[1]);
		assertEquals("Improper number of clubs",13,suit[2]);
		assertEquals("Improper number of hearts",13,suit[3]);
	}

	@Test
	public void validRank() {
		Deck test = new Deck();
		int[] rank = new int[13];
		for(int c = 0; c < 52; c++) {
			String Rank = test.getDeck().get(c).getRank();
			if(Rank.length()==1||Rank.equals("10")) 
				rank[Integer.valueOf(Rank)-1]++;
			else if(Rank.equals("ace"))
				rank[0]++;
			else if(Rank.equals("jack"))
				rank[10]++;
			else if(Rank.equals("queen"))
				rank[11]++;
			else if(Rank.equals("king"))
				rank[12]++;
			else
				fail("Unrecognized rank type");
		}
		assertEquals("Improper number of aces",4,rank[0]);
		assertEquals("Improper number of twos",4,rank[1]);
		assertEquals("Improper number of threes",4,rank[2]);
		assertEquals("Improper number of fours",4,rank[3]);
		assertEquals("Improper number of fives",4,rank[4]);
		assertEquals("Improper number of sixes",4,rank[5]);
		assertEquals("Improper number of sevens",4,rank[6]);
		assertEquals("Improper number of eights",4,rank[7]);
		assertEquals("Improper number of nines",4,rank[8]);
		assertEquals("Improper number of tens",4,rank[9]);
		assertEquals("Improper number of jacks",4,rank[10]);
		assertEquals("Improper number of queens",4,rank[11]);
		assertEquals("Improper number of kings",4,rank[12]);
	}

	@Test 
	public void shuffleTest() {
		Deck test1 = new Deck();
		Deck test2 = new Deck();
		Deck placeholder = new Deck();
		double[] ratio = new double[3];
		test1.shuffle();
		test2.shuffle();
		int[] counter = new int[3];
		for(int d = 0; d < 3; d++) {
			for(int c = 0; c < 52; c++) {
				String a = test1.getDeck().get(c).toString();
				String b = test2.getDeck().get(c).toString();
				String p = placeholder.getDeck().get(c).toString();
				if(!a.equals(b) && !b.equals(p) && !a.equals(p))
					counter[d]++;
			}
			test1.shuffle();
			test2.shuffle();
		}
		assertTrue("Shuffle method returned some repeat decks",counter[0] > 0 && counter[1] > 0 && counter[2] > 0);
	}
	
	@Test
	public void takeCardTest() {
		Deck test = new Deck();
		ArrayList<Card> compare1 = new ArrayList<>();
		ArrayList<Card> compare2 = new ArrayList<>();
		for(int c = 1; c < 52; c++) compare1.add(new Card(c,false));
		Card[] removed = new Card[46];
		removed [0] = test.takeCard();
		
		for(int c = 0; c < test.getDeck().size(); c++)
			assertEquals("takeCard fails the first call",test.getDeck().get(c).getRank(),compare1.get(c).getRank());
		
		assertEquals("Did not remove the top card of the deck",0,removed[0].id);
		
		for(int c = 0; c < removed.length-1; c++) removed[c+1] = test.takeCard();
		for(int c = removed.length; c < 52; c++) compare2.add(new Card(c,false));
		
		for(int c = 0; c < test.getDeck().size(); c++) 
			assertEquals("takeCard fails after multiple iterations", test.getDeck().get(c).getRank(), compare2.get(c).getRank());
		
	}
	
	@Test
	public void deckAddCard() {
		Deck test = new Deck();
		boolean check = test.addCard(new Card(33,false), false);
		assertFalse("addCard did not return false", check);
		assertEquals("Deck's add card increased the size of the deck", 52, test.getDeck().size());
	}
	
	@Test
	public void getSpecificCard() {
		Deck test = new Deck();
		Card[] removed = new Card[3];
		
		removed[0] = test.getSpecificCard("heart", "2");
		assertEquals("getSpecificCard does not decrease deck size on proper call", 51, test.getNumCards());
		
		removed[1] = test.getSpecificCard("space", "ace");
		removed[2] = test.getSpecificCard("diamond", "back");
		assertEquals("getSpecificCard decreases size on null call", 51, test.getNumCards());
		
		assertTrue("getSpecificCard does not return the correct card",removed[0].getRank().equals("2") && removed[0].getSuit().equals("heart"));
		assertNull("Improper suit does not return a null card", removed[1]);
		assertNull("Improper rank does not return a null card", removed[2]);
	}

}
