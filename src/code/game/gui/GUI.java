package code.game.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
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
	public static final Color BG_COLOR = new Color(0,100,0);
	
	public GUI() {
		this.panel = new JPanel();
		this.panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		this.panel.setBackground(BG_COLOR);
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
		golf.addActionListener(new GolfListener(this));
		menu.add(golf);

		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ExitListener());  
		menu.add(exit);
		
		return menuBar;
	}
	
	public void runGUI() {
		JFrame frameNew = new JFrame("Solitare");
		this.frame = frameNew;
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1150,1200);
		frame.setJMenuBar(this.getMenuBar());
		frame.add(this.panel, BorderLayout.CENTER); // add game panel
		frame.setVisible(true);
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
