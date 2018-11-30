package code.game.gui;

import javax.swing.*;

import code.cards.Card;
import code.cards.Deck;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class BouncyScreen extends JLayeredPane implements ActionListener {

	/**
	 * Required for extended JComponents or something.
	 */
	private static final long serialVersionUID = 1L;
	private GUI gui;

	/**
	 * Values associated with the vertical position
	 */
	private double yVelo = 1; private int yMin = GUI.WIN_HEIGHT-360; private double y = 0; 
	/**
	 * Values associated with the horizontal position
	 */
	private double xVelo = 10; private int xMin = 10; private double x = xMin; private int xMax = GUI.WIN_WIDTH-100;
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
	LinkedList<Card> draw;
	ArrayList<Integer> xInt;
	ArrayList<Integer> yInt;
	/**
	 * Number of times actionPerformed has been triggered in Timer t
	 */
	private int count;
	private	int iter = 0;



	/**
	 * Runs advance() every 30 milliseconds
	 */
	Timer t = new Timer(3, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(count < 51) {
				draw.add(deck.takeCard());
				count++;
				advance();
				redraw();
			}
			else {
				Deck BobRoss = new Deck(null);
				for(int c = 0; c < 52; c++) {
					BobRoss.getAllCards().get(c).flip();
				}
				setDeck(BobRoss);
				if(iter<4) 
					iter++;
				setCount(0);
			}
			if(iter == 4) {
				removeHead();
			}
			if(Math.abs(xVelo) < 2)
				t.stop();
		}


	});

	public BouncyScreen(GUI gui) {
		this.gui = gui;
		setPreferredSize(new Dimension(GUI.WIN_WIDTH, GUI.WIN_HEIGHT));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.count = 0;
		this.draw = new LinkedList<>();
		this.xInt = new ArrayList<>();
		this.yInt = new ArrayList<>();
		deck = new Deck(null);
		deck.shuffle();
		for(int c = 0; c < 52; c++) {
			deck.getAllCards().get(c).flip();
		}
		startTimer();
		this.gui.setPanel(this);
	}

	/**
	 * Draws the Cards
	 */
	private void redraw() {
		Card c = deck.getAllCards().get(0);
		for(int a = 0; a < 1; a++) {
			c.setBounds((int)x, (int)y, c.getIcon().getIconWidth(), c.getIcon().getIconHeight());
			this.add(c,a,0);
		}
	}

	public void removeHead() {
		draw.removeFirst();
	}
	
	public GUI getGUI() {
		return this.gui;
	}

	private void setCount(int i) {
		this.count = i;
	}

	public void setDeck(Deck RossBob) {
		this.deck = RossBob;
	}

	public void startTimer() {
		t.start();
	}
	
	public void removeAll() {
		this.removeAll();
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
			yVelo *= -1/b;
			y = yMin-1;
			if(-yVelo < 2)
				yVelo = 0;
		}
		xInt.add(draw.size()-1,(int) x);
		yInt.add(draw.size()-1,(int) y);
		xVelo *= b;
		yVelo *= b;
		yVelo += g;
	}

}
