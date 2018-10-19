package code.game.gui.control;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import code.game.gui.GUI;

public class HighScoreDisplay extends JLayeredPane {

	/**
	 * Required.
	 */
	private static final long serialVersionUID = 1L;

	public HighScoreDisplay(GUI gui) {
		
		JLabel header = new JLabel("High Scores:");
		header.setBounds(0,50,925,400);
		header.setFont(new Font("Arial", Font.PLAIN, 100));
		header.setHorizontalAlignment(JLabel.CENTER);
		add(header, 0, 0);
		
		JLabel hs = new JLabel();
		hs.setBounds(0,300,925,400);
		hs.setText(getHighScores());
		hs.setFont(new Font("Arial", Font.PLAIN, 50));
		hs.setHorizontalAlignment(JLabel.CENTER);
		add(hs, 0, 0);
		
	}
	
	public String getHighScores() {
		
		try {
			@SuppressWarnings("resource")
			BufferedReader brTest = new BufferedReader(new FileReader("resources/highscore.csv"));
			String data1 = brTest.readLine();
			String[] data = data1.split(",");
			String golf = data[0];
			String littlespider = data[1];
			String newGame = data[2];
			String retVal = "<html>Golf: " + golf + "<br/>" + 
					"Little Spider: " + littlespider + "<br/>" + 
					"Game to be Added: " + newGame + "</html>";
			return retVal;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
