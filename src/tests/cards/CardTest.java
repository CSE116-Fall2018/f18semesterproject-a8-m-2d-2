package tests.cards;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import code.cards.Card;
import code.cards.Deck;

public class CardTest {
	
	@Test
	public void suitTest() {
		for(int i = 0;i<13;i++) {
			Card test = new Card(i);
			assertEquals("heart", test.getSuit());
		}
		for(int i=13;i<26;i++) {
			Card test = new Card(i);
			assertEquals("diamond", test.getSuit());
		}
		for(int i=26;i<39;i++) {
			Card test = new Card(i);
			assertEquals("club", test.getSuit());
		}
		for(int i=39;i<52;i++) {
			Card test = new Card(i);
			assertEquals("spade", test.getSuit());
		}
	}
	//0,13,26,39 are ace
	//1-9,14-22,  are i+1
	//10-12,
	@Test
	public void rankTest() {
		for(int i = 0;i<52;i++) {
			Card test = new Card(i);
			if(test.getValue()%13==0) {
				assertEquals("ace", test.getRank());
			}
			else if(test.getValue()%13>0 && test.getValue()%13<10) {
				assertEquals(String.valueOf((i%13)+1),test.getRank());
			}
			else if(test.getValue()%13==10) {
				assertEquals("jack",test.getRank());
			}
			else if(test.getValue()%13==11) {
				assertEquals("queen",test.getRank());
			}
			else if(test.getValue()%13==12) {
				assertEquals("king",test.getRank());
			}
		}
	}
	@Test
	public void valueTest() {
		for(int i = 0;i<52;i++) {
			Card test = new Card(i);
			assertEquals(i%13,test.getValue());
		}
	}
}
