package tests.cards;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.Timeout;

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
	
}
