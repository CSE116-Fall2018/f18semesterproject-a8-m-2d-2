package code.game.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

import code.game.golf.Golf;
import code.game.littlespider.LittleSpider;
import code.game.gui.control.ColorControl;
import code.game.gui.control.ColorListener;
import code.game.gui.control.ExitListener;

public class GUI {
	
	private JPanel panel;
	private JFrame frame;
	public Color BG_COLOR;
	
	public GUI() {
		this.panel = new JPanel();
		this.panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBackground(new Color(0,100,0));
		BG_COLOR = new Color(0,100,0);
	}

	public JMenuBar getMenuBar() {
		Font font = new Font("Arial", Font.PLAIN, 35);
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("New Game");
		menuBar.add(menu);
		
		JMenuItem littleSpider = new JMenuItem("Little Spider");
		littleSpider.addActionListener(new LittleSpider(this));
		menu.add(littleSpider);
		
		JMenuItem golf = new JMenuItem("Golf");
		golf.addActionListener(new Golf(this));
		menu.add(golf);

		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ExitListener());  
		menu.add(exit);
		
		JMenu colorMenu = new JMenu("Settings");
		menuBar.add(colorMenu);
		
		JMenuItem defaultt = new JMenuItem("Default color");
		defaultt.addActionListener(new ColorControl(new Color(0,100,0), this));
		colorMenu.add(defaultt);
		
		JMenuItem purple = new JMenuItem("Purple");
		purple.addActionListener(new ColorControl(new Color(153,11,175), this));
		colorMenu.add(purple);
		
		JMenuItem yellow = new JMenuItem("Yellow");
		yellow.addActionListener(new ColorControl(new Color(236,229,25), this));
		colorMenu.add(yellow);
		
		JMenuItem blue = new JMenuItem("Blue");
		blue.addActionListener(new ColorControl(new Color(0,0,200), this));
		colorMenu.add(blue);
		
		JMenuItem pink = new JMenuItem("Pink");
		pink.addActionListener(new ColorControl(new Color(225,210,210), this));
		colorMenu.add(pink);
		
		JMenuItem custom = new JMenuItem("Custom Color");
		custom.addActionListener(new ColorListener(this));
		colorMenu.add(custom);
		
		return menuBar;
	}
	
	public void refresh() {
		
	}
	
	public void runGUI() {
		this.frame = new JFrame("Solitare");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1150,1200);
		frame.setJMenuBar(this.getMenuBar());
		frame.add(this.panel, BorderLayout.CENTER); // add game panel
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	
	/**
	 * Returns a JLabel with the empty pile icon on it to be displayed on the GUI
	 * This empty pile icon can be called with obj.getIcon()
	 * 
	 * @return JLabel returns a JLabel with empty pile icon
	 */
	public static ImageIcon getEmptyIcon() {
		URL filePath = GUI.class.getResource("/e.png"); // empty.png
		
		if (filePath == null) {
			throw new IllegalArgumentException("Could not find card image file " + filePath);
		}
		
		return new ImageIcon(filePath);
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

	public void setColor(Color c) {
		BG_COLOR = c;
	}
	public Color getColor() {
		return BG_COLOR;
	}
}
