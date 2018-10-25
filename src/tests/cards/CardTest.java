package tests.cards;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import code.cards.Card;

public class CardTest {
	
	@Test
	public void suitTest() {
		for(int i = 0;i<13;i++) {
			Card test = new Card(i,null);
			assertEquals("heart", test.getSuit());
		}
		for(int i=13;i<26;i++) {
			Card test = new Card(i,null);
			assertEquals("diamond", test.getSuit());
		}
		for(int i=26;i<39;i++) {
			Card test = new Card(i,null);
			assertEquals("club", test.getSuit());
		}
		for(int i=39;i<52;i++) {
			Card test = new Card(i,null);
			assertEquals("spade", test.getSuit());
		}
	}
	//0,13,26,39 are ace
	//1-9,14-22,  are i+1
	//10-12,
	@Test
	public void rankTest() {
		for(int i = 0;i<52;i++) {
			Card test = new Card(i,null);
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
			Card test = new Card(i,null);
			assertEquals(i%13,test.getValue());
		}
	}
}
