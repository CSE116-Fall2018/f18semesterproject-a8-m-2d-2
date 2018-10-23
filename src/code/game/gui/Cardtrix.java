package code.game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import code.cards.Card;
import code.cards.Deck;
public class Cardtrix extends JLayeredPane implements ActionListener {
	
	/**
	 * Required for extended JComponents or something.
	 */
	private static final long serialVersionUID = 1L;
	private GUI gui;
	private Random rand;
	private Integer depth = 0;
	public Timer timer;

	public Cardtrix(GUI gui, int mode) {
		this.gui = gui;
		this.rand = new Random();
		setPreferredSize(new Dimension(GUI.WIN_WIDTH, GUI.WIN_HEIGHT));
		// The reset/new game panel
		JPanel panel = new JPanel();
		panel.setForeground(Color.GRAY);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panel.setOpaque(true);
		// Center it
		int w = 250, h = 250;
		panel.setBounds(GUI.WIN_WIDTH / 2 - w / 2, GUI.WIN_HEIGHT / 2 - h, w, h);
		JLabel text = new JLabel();
		text.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));
		text.setFont(new Font("Arial", Font.PLAIN, 30));
		
		switch(mode) {
		case 0: // game lost
			text.setText("You lost :(");
			panel.add(text);
			this.add(panel, Integer.valueOf(60));
			break;
		case 1: // game won
			text.setText("You won!");
			panel.add(text);
			this.add(panel, Integer.valueOf(60));
			break;
		case 2: // for fun
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
		this.gui.getPanel().removeAll();
		this.gui.getPanel().add(this);
		this.gui.getPanel().validate();
		this.gui.getPanel().repaint();
	}
}
