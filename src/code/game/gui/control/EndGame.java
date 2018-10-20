package code.game.gui.control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import code.game.Game;
import code.game.golf.Golf;
import code.game.littlespider.LittleSpider;

public class EndGame {

	public static void win(Game game) {
		int moves = game.getMoves();
		try {
			@SuppressWarnings("resource")
			BufferedReader brTest = new BufferedReader(new FileReader("resources/highscore.csv"));
			String data1 = brTest.readLine();
			String[] data = data1.split(",");
			String golf = data[0];
			String littlespider = data[1];
			String newGame = data[2];
			if(game instanceof Golf) {
				if(Integer.valueOf(golf) > moves) {
					golf = String.valueOf(moves);
				}
			} 
			if(game instanceof LittleSpider) {
				if(Integer.valueOf(littlespider) > moves) {
					littlespider = String.valueOf(moves);
				}
			}
			// handle next game
			
			String newScores = golf + "," + littlespider + "," + newGame;
			
			FileWriter fw = new FileWriter("resources/highscore.csv", false);
			fw.write(newScores);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
