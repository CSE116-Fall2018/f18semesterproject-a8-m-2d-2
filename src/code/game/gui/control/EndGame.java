package code.game.gui.control;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import code.game.Game;
import code.game.fortythieves.FortyThieves;
import code.game.golf.Golf;
import code.game.gui.Cardtrix;
import code.game.gui.GUI;
import code.game.littlespider.LittleSpider;

/**
 * The EndGame class is called only when a game has been finished.
 * In our case, when it has been won. It's only purpose is to display
 * Cardtrix and add to the low score CSV if it's a new high score.
 * 
 * @author Drew Fiutko
 *
 */
public class EndGame {
	
	/**
	 *  Checks both little spider and golf game to see if the current highscore is greater
	 *  than the current game's number of moves. and if it is we need to make a new file with the
	 *  updated higscores
	 */
	public static void win(GUI gui, Game game) {
		Cardtrix matrix = new Cardtrix(gui, game, Cardtrix.GAME_WON);
		// Trigger matrix
		matrix.actionPerformed(new ActionEvent(matrix, ActionEvent.ACTION_PERFORMED, null) {
			private static final long serialVersionUID = 1L;
		});
		
		int moves = game.getMoves();
		try {
			@SuppressWarnings("resource")
			BufferedReader brTest = new BufferedReader(new FileReader("resources/highscore.csv"));
			String data1 = brTest.readLine();
			String[] data = data1.split(",");
			String golf = data[0];
			String littlespider = data[1];
			String fortythieves = data[2];
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
			if(game instanceof FortyThieves) {
				if(Integer.valueOf(fortythieves) > moves) {
					fortythieves = String.valueOf(moves);
				}
			}
			
			String newScores = golf + "," + littlespider + "," + fortythieves;
			
			FileWriter fw = new FileWriter("resources/highscore.csv", false);
			fw.write(newScores);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
