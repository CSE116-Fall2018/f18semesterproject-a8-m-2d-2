package code.game.GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
	
	public static void runGUI() {
		GUI gui = new GUI();
		
		JFrame frame = new JFrame("Solitare");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(1000,1000);
		frame.setJMenuBar(gui.getMenuBar());
		//frame.getContentPane().add(); // add game panel
		//frame.pack();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                runGUI();
            }
        });
	}
	
}
