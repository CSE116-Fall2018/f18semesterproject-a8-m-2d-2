package code.game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import code.cards.Card;
import code.cards.Deck;
import code.game.Game;
public class Cardtrix extends JLayeredPane implements ActionListener {
	
	/**
	 * Required for extended JComponents or something.
	 */
	private static final long serialVersionUID = 1L;
	private GUI gui;
	private Game game;
	private Random rand;
	private Integer depth = 0;
	public static final int GAME_LOST = 0;
	public static final int GAME_WON = 1;
	public static final int EASTER_EGG = 2;
	public Timer timer;

	public Cardtrix(GUI gui, Game game, int mode) {
		this.gui = gui;
		this.game = game;
		this.rand = new Random();
		setPreferredSize(new Dimension(GUI.WIN_WIDTH, GUI.WIN_HEIGHT));
		
		// The reset/new game panel
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setForeground(Color.GRAY);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panel.setOpaque(true);
		
		// Center it
		int w = 250, h = 150;
		panel.setBounds(GUI.WIN_WIDTH / 2 - w / 2, GUI.WIN_HEIGHT / 2 - h, w, h);
		
		// The text header of the dialog
		JLabel text = new JLabel();
		text.setBorder(BorderFactory.createEmptyBorder(20, 10, 30, 10));
		text.setFont(new Font("Arial", Font.PLAIN, 30));
		text.setAlignmentX((float) 0.5);
		
		// The panel holding the buttons
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));
		buttons.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		buttons.add(Box.createHorizontalGlue());
		
		// The new game button on the dialog
		JButton newGame = new JButton("New Game");
		newGame.addActionListener(this.game);
		// Main Menu button
		JButton mainMenu = new JButton("Main Menu");
		mainMenu.addActionListener(new GameMenu(this.gui));
		
		switch(mode) {
		case GAME_LOST:
			text.setText("You lost :(");
			panel.add(text);
			panel.add(Box.createHorizontalGlue());
			buttons.add(newGame);
			buttons.add(mainMenu);
			this.add(panel, Integer.valueOf(60));
			panel.add(buttons);
			break;
		case GAME_WON:
			text.setText("You won!");
			panel.add(text);
			panel.add(Box.createHorizontalGlue());
			buttons.add(newGame);
			buttons.add(mainMenu);
			panel.add(buttons);
			this.add(panel, Integer.valueOf(60));
			break;
		case EASTER_EGG:
			// do nothing
			break;
		}
	}

	private void matrix() {
		Deck d = new Deck(null);
		d.shuffle();
		ArrayList<Card> deck = d.getAllCards();
		
		for (int i = 0; i < deck.size(); i++) {
			// Carry `this` in method scope
			JLayeredPane self = this;
			Card c = deck.get(i);
			c.setFaceUp();

			// Give a random x-coord and set y-coord just above frame
			int w = c.getIcon().getIconWidth(),
				h = c.getIcon().getIconHeight(),
				x = rand.nextInt(GUI.WIN_WIDTH) - w / 2,
				y = rand.nextInt(GUI.WIN_HEIGHT) - GUI.WIN_HEIGHT - h;
			// Set these as the bounds
			c.setBounds(x, y, w, h);

			// Timer hits every 23 ms
			Timer timer = new Timer(20, new ActionListener() {
				// Create a random `yVar` to move card by every tick, randomly 2-6
				int yVar = rand.nextInt(6) + 2;
				@Override
				public void actionPerformed(ActionEvent e) {
					// Move the card down yVar. This is a constant speed
					c.setBounds(x, c.getY() + yVar, w, h);
					// Remove the element after it's past the bottom of the window
					if (c.getY() > GUI.WIN_HEIGHT) {
						self.remove(c);
					}
				}});
			// Add it & increment depth for next card
			this.add(c, depth++, 0);
			timer.start();
		}
		// Reset depth back to 0 after this deck has been placed
		depth = 0;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		matrix();
		// Run matrix() every 4 seconds
		this.timer = new Timer(4000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				matrix();
			}});
		this.timer.start();
		this.gui.setPanel(this);
	}
}
