package code.game.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLayeredPane;
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

	public Cardtrix(GUI gui) {
		this.gui = gui;
		this.rand = new Random();
		setPreferredSize(new Dimension(GUI.WIN_WIDTH, GUI.WIN_HEIGHT));
	}

	private void matrix() {
		Deck d = new Deck(null);
		d.shuffle();
		ArrayList<Card> deck = d.getAllCards();
		
		for (int i = 0; i < deck.size(); i++) {
			JLayeredPane self = this;
			Card c = deck.get(i);
			c.setFaceUp();

			int x = rand.nextInt(GUI.WIN_WIDTH) - c.getIcon().getIconWidth(),
				y = rand.nextInt(GUI.WIN_HEIGHT) - (GUI.WIN_HEIGHT + 140),
				w = c.getIcon().getIconWidth(),
				h = c.getIcon().getIconHeight();
			
			c.setBounds(x, y, w, h);

			Timer timer = new Timer(23, new ActionListener() {
				int yVar = rand.nextInt(6) + 3;
				@Override
				public void actionPerformed(ActionEvent e) {
					c.setBounds(x, c.getY() + yVar, w, h);
					self.add(c, depth++, 0);
					
					if (c.getY() > GUI.WIN_HEIGHT) {
						self.remove(c);
					}
				}});
			timer.start();
		}
		depth = 0;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		matrix();
		Timer timer = new Timer(3500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				matrix();
			}});
		timer.start();
		this.gui.getPanel().removeAll();
		this.gui.getPanel().add(this);
		this.gui.getPanel().validate();
		this.gui.getPanel().repaint();
	}
}
