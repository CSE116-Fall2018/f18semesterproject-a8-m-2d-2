package code.game.gui.control;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import code.game.Game;
import code.game.golf.Golf;
import code.game.gui.Cardtrix;
import code.game.gui.GUI;
import code.game.littlespider.LittleSpider;

public class EndGame {
	
	
	/**
	 *  Checks both little spider and golf game to see if the current highscore is greater
	 *  than the current game's number of moves. and if it is we need to make a new file with the
	 *  updated higscores
	 */
	public static void win(GUI gui, Game game) {
		Cardtrix matrix = new Cardtrix(gui, game, 1);
		// Trigger matrix
		matrix.actionPerformed(new ActionEvent(matrix, ActionEvent.ACTION_PERFORMED, null) {
			private static final long serialVersionUID = 1L;
		});
		
		new Cardtrix(gui, game, 1);
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
