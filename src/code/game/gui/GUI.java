package code.game.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
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
/**
 * Holds initial frame and the game JPanel.
 *
 */
public class GUI {
	/**
	 * JPanel that hold all game content. Defined by Game instance.
	 */
	private JPanel panel;
	/**
	 * Frame with a JMenuBar.
	 */
	private JFrame frame;
	/**
	 * Background color.
	 */
	private Color bgColor;
	/**
	 * Font that is used game wide.
	 */
	public final Font FONT = new Font("Arial", Font.PLAIN, 25);
	/**
	 * The window width.
	 */
	public static final int WIN_WIDTH = 925;
	/**
	 * The window height.
	 */
	public static final int WIN_HEIGHT = 925;
	
	/**
	 * Initializes the game frame and JPanel.
	 */
	public GUI() {
		this.panel = new GameMenu(this);
		this.panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		this.panel.setPreferredSize(new Dimension(GUI.WIN_WIDTH, GUI.WIN_HEIGHT));
		this.panel.setBackground(new Color(0,100,0));
		this.bgColor = new Color(0,100,0);
	}

	/**
	 * Menu Bar that sits at the top of the frame. Can start a new game, exit the frame, and choose a 
	 * new background color.
	 * @return returns the menu bar to be used by the frame.
	 */
	public JMenuBar getMenuBar() {
        UIManager.put("Menu.font", FONT);
        UIManager.put("MenuItem.font", FONT);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("New Game");
		menuBar.add(menu);
		
		JMenuItem littleSpider = new JMenuItem("Little Spider");
		littleSpider.addActionListener(new LittleSpider(this));
		menu.add(littleSpider);
		
		JMenuItem golf = new JMenuItem("Golf");
		golf.addActionListener(new Golf(this));
		menu.add(golf);
		
		JMenuItem matrix = new JMenuItem("Matrix");
		matrix.addActionListener(new Cardtrix(this, new Golf(this), 1));
		menu.add(matrix);

		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ExitListener());
		menu.addSeparator();
		menu.add(exit);
		
		JMenu colorMenu = new JMenu("Background");
		menuBar.add(colorMenu);
		
		JMenuItem defaultt = new JMenuItem("Default color");
		defaultt.addActionListener(new ColorControl(new Color(0,100,0), this));
		colorMenu.add(defaultt);
		
		JMenuItem purple = new JMenuItem("Purple");
		purple.addActionListener(new ColorControl(new Color(182,135,218), this));
		colorMenu.add(purple);
		
		JMenuItem yellow = new JMenuItem("Yellow");
		yellow.addActionListener(new ColorControl(new Color(247,241,153), this));
		colorMenu.add(yellow);
		
		JMenuItem blue = new JMenuItem("Blue");
		blue.addActionListener(new ColorControl(new Color(106,128,215), this));
		colorMenu.add(blue);
		
		JMenuItem pink = new JMenuItem("Pink");
		pink.addActionListener(new ColorControl(new Color(228,163,163), this));
		colorMenu.add(pink);
		
		JMenuItem orange = new JMenuItem("Orange");
		orange.addActionListener(new ColorControl(new Color(238,146,42), this));
		colorMenu.add(orange);
		
		JMenuItem custom = new JMenuItem("Custom Color");
		custom.addActionListener(new ColorListener(this));
		colorMenu.addSeparator();
		colorMenu.add(custom);
		
		return menuBar;
	}
	
	public void refresh() {
		
	}
	/**
	 * Brings all components together to be used by the Main class
	 * and sets frame properties.
	 */
	public void runGUI() {
		this.frame = new JFrame("Solitare");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(GUI.WIN_WIDTH, GUI.WIN_HEIGHT);
		frame.setJMenuBar(this.getMenuBar());
		frame.add(this.panel, BorderLayout.CENTER); // add game panel
		frame.setContentPane(this.panel);
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
	/**
	 * Gets the current game panel.
	 * @return JPanel of the current game.
	 */
	public JPanel getPanel() {
		return panel;
	}
	/**
	 * Sets the game panel to the current state of the game.
	 * @param panel
	 */
	public void setPanel(JLayeredPane panel) {
		this.panel.removeAll();
		this.panel.add(panel);
		this.panel.validate();
		this.panel.repaint();
	}
	/**
	 * Sets the background color of the frame.
	 * @param c New color of the frame.
	 */
	public void setColor(Color c) {
		bgColor = c;
	}
	/**
	 * Gets the current background color of the frame.
	 * @return Color of the frame.
	 */
	public Color getColor() {
		return bgColor;
	}
}
