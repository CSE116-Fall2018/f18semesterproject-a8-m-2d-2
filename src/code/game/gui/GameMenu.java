package code.game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import code.game.golf.Golf;
import code.game.littlespider.LittleSpider;

/**
 * The main game menu displayed when the game first opens up.
 * 
 * @author Matt Ferrera
 * 
 */
public class GameMenu extends JPanel implements ActionListener {

	/** Required when extended JComponents or something */
	private static final long serialVersionUID = 1L;
	/** The current GUI instance */
	private GUI gui;
	
	/**
	 * Creates a main game menu, adding a header icon and buttons
	 * for each respective game.
	 * 
	 * @param gui the current GUI instance.
	 */
	public GameMenu(GUI gui) {
		this.gui = gui;
		setLayout(new GridLayout(0, 1));
		setPreferredSize(new Dimension(GUI.WIN_WIDTH, GUI.WIN_HEIGHT));
		
		// Set up header
		JLabel header = new JLabel();
		URL path;
		try {
			path = getClass().getResource("/header.png");
			header.setIcon(new ImageIcon(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Center it & add spacing around it
		header.setAlignmentX((float) 0.5);
		header.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
		
		// Golf button
		JButton golf = new JButton();
		try {
			path = getClass().getResource("/golf.png");
			golf.setIcon(new ImageIcon(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		golf.setContentAreaFilled(false);
		golf.setOpaque(false);
		golf.setBorderPainted(false);
		golf.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		golf.setAlignmentX((float) 0.5);
		golf.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {
				try {
					URL path = getClass().getResource("/golf_over.png");
					golf.setIcon(new ImageIcon(path));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				try {
					URL path = getClass().getResource("/golf.png");
					golf.setIcon(new ImageIcon(path));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			
		});
		golf.addActionListener(new Golf(this.gui));
		
		// LittleSpider button
		JButton littleSpider = new JButton();
		try {
			path = getClass().getResource("/littlespider.png");
			littleSpider.setIcon(new ImageIcon(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		littleSpider.setContentAreaFilled(false);
		littleSpider.setOpaque(false);
		littleSpider.setBorderPainted(false);
		littleSpider.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		littleSpider.setAlignmentX((float) 0.5);
		littleSpider.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {
				try {
					URL path = getClass().getResource("/littlespider_over.png");
					littleSpider.setIcon(new ImageIcon(path));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				try {
					URL path = getClass().getResource("/littlespider.png");
					littleSpider.setIcon(new ImageIcon(path));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			
		});
		littleSpider.addActionListener(new LittleSpider(this.gui));
		
		JLabel HSheader = new JLabel("Fewest Moves:");
		HSheader.setFont(new Font("Arial", Font.PLAIN, 50));
		HSheader.setForeground(Color.LIGHT_GRAY);
		HSheader.setAlignmentX(CENTER_ALIGNMENT);
		HSheader.setBorder(BorderFactory.createEmptyBorder(50, 0, 10, 0));
		
		JLabel hs = new JLabel();
		hs.setText(getHighScores());
		hs.setFont(new Font("Arial", Font.PLAIN, 40));
		hs.setForeground(Color.LIGHT_GRAY);
		hs.setAlignmentX(CENTER_ALIGNMENT);
		hs.setHorizontalAlignment(JLabel.CENTER);

		add(header);
		add(golf);
		add(littleSpider);
		add(HSheader);
		add(hs);
	}

	/**
	 *  Returns the high score text on the JLayeredPane
	 *  
	 *  @return String the high score text
	 */
	public String getHighScores() {
		
		try {
			@SuppressWarnings("resource")
			BufferedReader brTest = new BufferedReader(new FileReader("resources/highscore.csv"));
			String data1 = brTest.readLine();
			String[] data = data1.split(",");
			String golf = data[0];
			String littlespider = data[1];
//			String newGame = data[2];
			String retVal = "<html>Golf: " + golf + "<br/>" + 
					"Little Spider: " + littlespider + "<br/>";
			//+ "Game to be Added: " + newGame + "</html>";
			return retVal;
			
		} catch (FileNotFoundException e) {
			return "No \"\\highscore.csv\" file found";
		} catch (IOException e) {
			return "Error reading file";
		}
	}
	/**
	 * The Action that adds this class to the main GUI panel,
	 * after removing all other elements, and then repaints and
	 * validates.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.gui.getPanel().removeAll();
		this.gui.setPanel(this);
		this.gui.getPanel().validate();
		this.gui.getPanel().repaint();
	}

}
