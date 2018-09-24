package tests.game.littlespider;

import org.junit.Before;
import org.junit.Test;

import code.game.littlespider.LittleSpider;

public class LittleSpiderTest {

	private LittleSpider game;
	
	@Before
	public void newGame() {
		LittleSpider game = new LittleSpider();
		this.game = game;
	}
	
	@Test
	public void initTest() {
		
	}
}
