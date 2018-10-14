package code.game.gui;

import javax.swing.SwingUtilities;

public class Main {
	
	public static void main(String[] args) {
		GUI gui = new GUI();
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               gui.runGUI();
            }
        });
	}
	
}
