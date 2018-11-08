package code.game.fortythieves;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JLabel;

import code.cards.Card;
import code.cards.Deck;
import code.game.Game;
import code.game.fortythieves.Homecell;
import code.game.fortythieves.Stockpile;
import code.game.gui.GUI;
import code.game.gui.control.EndGame;

/**
 * This class holds the code for the Little Spider game. Its constructor initializes four homecell piles and eight tableau piles.
 * 
 * @author Drew Fiutko
 */
public class FortyThieves extends Game {
	
	/** Required. */
	private static final long serialVersionUID = 1L;
	/** Holds an array of Homecell instances that are used by the game. */
	private Homecell[] homecells;
	/** Homecell is the Homecell object where cards will be placed throughout the game. */
	private Wastepile wastepile;
	/** Stockpile is the game's Stockpile object. */
	private Stockpile stockpile;
	/** The amount of vertical offset per card in same pile. */
	private static final int Y_OFFSET = 25;
	/** The horizontal offset to the right of each tableau. */
	private static final int X_OFFSET_TABLEAU = 110;
	/** The horizontal offset to the right of each homecell. */
	private static final int X_OFFSET_HOMECELL = 150;
	
	/** 
	 * Starts a little spider game. 
	 */
	public FortyThieves(GUI gui) {
		super(gui);
	}
	
	/**
	 * Creates Homecell and Tableau piles for Little Spider.
	 */
	protected void init() {
		homecells = new Homecell[8];
		this.setTableauSelected(null);
		
		ArrayList<Card> allCards = new ArrayList<>();
	
		Deck deck = new Deck(this);
		deck.shuffle();

		Card c1 = deck.getSpecificCard("diamond", "ace");
		c1.flip();
		Homecell homecell = new Homecell(c1, this);
		homecells[0] = homecell;
		homecell.setHomecellNum(0);

		Card c2 = deck.getSpecificCard("heart", "ace");
		c2.flip();
		Homecell homecell2 = new Homecell(c2, this);
		homecells[1] = homecell2;
		homecell2.setHomecellNum(1);

		Card c3 = deck.getSpecificCard("club", "ace");
		c3.flip();
		Homecell homecell3 = new Homecell(c3, this);
		homecells[2] = homecell3;
		homecell3.setHomecellNum(2);

		Card c4 = deck.getSpecificCard("spade", "ace");
		c4.flip();
		Homecell homecell4 = new Homecell(c4, this);
		homecells[3] = homecell4;
		homecell4.setHomecellNum(3);
		
		allCards.addAll(deck.getAllCards());
		deck = new Deck(this);
		deck.shuffle();
		
		Card c5 = deck.getSpecificCard("diamond", "ace");
		c5.flip();
		Homecell homecell5 = new Homecell(c5, this);
		homecells[4] = homecell5;
		homecell.setHomecellNum(4);

		Card c6 = deck.getSpecificCard("heart", "ace");
		c6.flip();
		Homecell homecell6 = new Homecell(c6, this);
		homecells[5] = homecell6;
		homecell2.setHomecellNum(5);

		Card c7 = deck.getSpecificCard("club", "ace");
		c7.flip();
		Homecell homecell7 = new Homecell(c7, this);
		homecells[6] = homecell7;
		homecell3.setHomecellNum(6);

		Card c8 = deck.getSpecificCard("spade", "ace");
		c8.flip();
		Homecell homecell8 = new Homecell(c8, this);
		homecells[7] = homecell8;
		homecell4.setHomecellNum(7);
		
		allCards.addAll(deck.getAllCards());
		
		tableaus = new Tableau[13];
		for(int i=0; i<tableaus.length; i++) {
			Tableau tableau = new Tableau();
			for(int j=1; j<=3; j++) {
				Card x = allCards.remove(0);
				x.flip();
				x.setTableauNum(i);
				tableau.addCard(x, true);
			}
			tableaus[i]= tableau;
		}
		
		this.wastepile = new Wastepile(this);
		this.stockpile = new Stockpile(this, allCards);
		refresh();
		setMoves(0);
	}
	
	/**
	 * This method refreshes the game panel during the little spider game 
	 * to ensure the correct Cards are showing at all times.  Called every
	 * time a correct move is made.
	 */
	public void refresh() {
		if (gameWon()) {
			EndGame.win(this.gui, this);
			return;
		}
		
		removeAll();
		// Origin starting point to place homecell cards
		Point pos = new Point(175, 20);

		// Add homecell piles to top of frame without displaying pile
		for(int i = 0; i < this.homecells.length; i++) {
			Homecell icon = this.homecells[i];
			icon.setBounds(pos.x, pos.y, icon.getIcon().getIconWidth(), icon.getIcon().getIconHeight());
			this.add(icon, 0, 0); // depth always 0
			pos.x += X_OFFSET_HOMECELL;
		}

		// Add tableaus and reset starting point with displaying pile
		pos.x = 10;
		pos.y = 200;
		for(int i = 0; i < this.tableaus.length; i++) {
			ArrayList<Card> cards = this.tableaus[i].getAllCards();
			Integer depth = 0;
			
			if (cards == null) {
				JLabel space = new JLabel();
				space.setBounds(pos.x, pos.y, 100, 120);
				this.add(space, depth, 0);
				pos.x += X_OFFSET_TABLEAU;
				continue;
			}
			for(int j = cards.size(); j > 0; j--) {
				Card icon = cards.get(j - 1);
				icon.setBounds(pos.x, pos.y, icon.getIcon().getIconWidth(), icon.getIcon().getIconHeight());

				if(j == 1) {
					icon.setTop();
				}

				this.add(icon, depth, 0);

				depth++;
				pos.y += Y_OFFSET;
			}
			depth = 0;
			pos.y = 200;
			pos.x += X_OFFSET_TABLEAU;
		}
		
		int w = 100, h = 140;
		// Add the stockpile and wastepile pile
		if (this.getStockpile().getIcon() != null) {
			this.stockpile.setBounds(350, 450, w, h);
		}
		
		this.add(this.stockpile, Integer.valueOf(0), 0);	
		this.wastepile.setBounds(470, 450, w, h);
		this.add(this.wastepile, Integer.valueOf(0), 0);

		this.errorLabel.setBounds(325, 700, 300, 100);
		this.add(this.errorLabel, 0, 0);
		this.gui.getPanel().validate();
		this.gui.getPanel().repaint();
	}
	
	/**
	 * Returns homecell array.
	 * 
	 * @return Array of homecell instances.
	 */
	public Homecell[] getHomecells() {
		return homecells;
	}

	/**
	 * Returns the Homecell pile.
	 * 
	 * @return Homecell The homecell pile.
	 */
	public Wastepile getWastepile() {
		return wastepile;
	}
	
	/**
	 * Returns the Stockpile pile.
	 * 
	 * @return Stockpile The stockpile pile.
	 */
	public Stockpile getStockpile() {
		return stockpile;
	}
}
