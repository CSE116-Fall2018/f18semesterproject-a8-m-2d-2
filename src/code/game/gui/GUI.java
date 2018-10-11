package code.game.gui;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

import code.game.gui.control.ExitListener;
import code.game.gui.control.GolfListener;
import code.game.gui.control.LittleSpiderListener;

public class GUI {
	
	private JPanel panel;
	private JFrame frame;
	
	public GUI() {
		this.panel = new JPanel();
	}

	public JMenuBar getMenuBar() {
		Font font = new Font("Arial", Font.PLAIN, 35);
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("New Game");
		menuBar.add(menu);
		
		JMenuItem littleSpider = new JMenuItem("Little Spider");
		littleSpider.addActionListener(new LittleSpiderListener(this));
		menu.add(littleSpider);
		
		JMenuItem golf = new JMenuItem("Golf");
		golf.addActionListener(new GolfListener());
		menu.add(golf);

		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ExitListener());  
		menu.add(exit);
		
		return menuBar;
	}
	
	public void runGUI() {
		JFrame frameNew = new JFrame("Solitare");
		this.frame = frameNew;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(1150,1200);
		frame.setJMenuBar(this.getMenuBar());
		frame.getContentPane().add(this.getPanel()); // add game panel
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	
	public JFrame getFrame() {
		return frame;
	}

}
