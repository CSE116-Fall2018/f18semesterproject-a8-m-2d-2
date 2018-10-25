package code.game.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

	/**
	 * Required when extended JComponents or something
	 */
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
		
		golf.setAlignmentX((float) 0.5);
		golf.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
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
		
		littleSpider.setAlignmentX((float) 0.5);
		littleSpider.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
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
		
		add(header);
		add(golf);
		add(littleSpider);
	}

	/**
	 * The Action that adds this class to the main GUI panel,
	 * after removing all other elements, and then repaints and
	 * validates.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.gui.getPanel().removeAll();
		this.gui.getPanel().add(this);
		this.gui.getPanel().validate();
		this.gui.getPanel().repaint();
	}

}
