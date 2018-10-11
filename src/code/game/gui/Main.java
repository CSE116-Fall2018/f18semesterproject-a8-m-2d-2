package code.game.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
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
