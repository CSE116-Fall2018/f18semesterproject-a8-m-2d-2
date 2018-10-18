package code.game.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Main {

	public static void main(String[] args) {
		GUI gui = new GUI();
		
        Timer t = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.refresh();
            }
        });

        t.start();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				gui.runGUI();
			}
		});
	}

}
