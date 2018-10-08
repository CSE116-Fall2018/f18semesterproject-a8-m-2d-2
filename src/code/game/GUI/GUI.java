package code.game.GUI;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import code.game.GUI.control.ExitListener;
import code.game.GUI.control.GolfListener;
import code.game.GUI.control.LittleSpiderListener;

public class GUI {

	public JMenuBar getMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("New Game");
		JMenuItem littleSpider = new JMenuItem("Little Spider");
		littleSpider.addActionListener(new LittleSpiderListener());
		menu.add(littleSpider);
		JMenuItem golf = new JMenuItem("Golf");
		golf.addActionListener(new GolfListener());
		menu.add(golf);
		menuBar.add(menu);
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ExitListener());  
		menu.add(exit);
		return menuBar;
	}
	
}
