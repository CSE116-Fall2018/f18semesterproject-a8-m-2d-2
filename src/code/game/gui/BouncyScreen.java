package code.game.gui;

import javax.swing.*;

import code.cards.Card;
import code.cards.Deck;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BouncyScreen extends JLayeredPane implements ActionListener {

	/**
	 * Required for extended JComponents or something.
	 */
	private static final long serialVersionUID = 1L;
	private GUI gui;

	/**
	 * Values associated with the vertical position
	 */
	private double yVelo = 1; private int yMin = GUI.WIN_HEIGHT-400; private double y = 0; 
	/**
	 * Values associated with the horizontal position
	 */
	private double xVelo = 5; private int xMin = 10; private double x = xMin; private int xMax = GUI.WIN_WIDTH-100;
	/**
	 * Acceleration due to gravity
	 */
	private double g = 1.8;
	/**
	 * Simulated air resistance constant
	 */
	private double b = 0.999;

	private Deck deck;
	/**
	 * The Cards that need to be drawn and their respective x and y coordinates
	 */
	ArrayList<Card> draw = new ArrayList<>();
	ArrayList<Integer> xInt = new ArrayList<>();
	ArrayList<Integer> yInt = new ArrayList<>();
	/**
	 * Number of times actionPerformed has been triggered in Timer t
	 */
	private int count = 0;
	


	/**
	 * Runs advance() every 30 milliseconds
	 */
	Timer t = new Timer(20, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(count%10 == 0)
				draw.add(deck.takeCard());
			if(count < 510) {
				count++;
				advance();
				redraw();
			}
			else
				t.stop();
		}
	});

	public BouncyScreen(GUI gui) {
		this.gui = gui;
		setPreferredSize(new Dimension(GUI.WIN_WIDTH, GUI.WIN_HEIGHT));
		deck = new Deck(null);
		deck.shuffle();
		for(int c = 0; c < 52; c++) {
			xInt.add(0);
			yInt.add(0);
			deck.getAllCards().get(c).flip();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		startTimer();
		this.gui.setPanel(this);
	}

	/**
	 * Draws the Cards
	 */
	private void redraw() {
		Card c = deck.getAllCards().get(0);
		for(int a = 0; a < draw.size(); a++) {
			c.setBounds((int)x, (int)y, c.getIcon().getIconWidth(), c.getIcon().getIconHeight());
			this.add(c,a,0);
		}
	}





	public void startTimer() {
		t.start();
	}

	/**
	 * Translates the top JLabel by (xVelo, yVelo) and updates the velocity values
	 */
	private void advance() {
		x += xVelo;
		if(x <= xMin || x >= xMax) {
			xVelo *= -1;
		}
		y += yVelo;
		if(y >= yMin) {
			yVelo *= -0.95;
			y = yMin-1;
		}
		xInt.set(draw.size(),(int) x);
		yInt.set(draw.size(),(int) y);
		xVelo *= b;
		yVelo *= b;
		yVelo += g;
	}

}
