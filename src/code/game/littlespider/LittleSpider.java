package code.game.littlespider;


import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JLabel;

import code.cards.Card;
import code.cards.Deck;
import code.game.Game;
import code.game.gui.GUI;
import code.game.gui.control.EndGame;


/**
 * This class holds the code for the Little Spider game. Its constructor initializes four homecell piles and eight tableau piles.
 * 
 * @author Drew Fiutko
 */
public class LittleSpider extends Game {
	
	/**
	 * Required.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Holds an array of Homecell instances that are used by the game.
	 */
	private Homecell[] homecells;
	/**
	 * The amount of vertical offset per card in same pile.
	 */
	private static final int Y_OFFSET = 25;
	/**
	 * The horizontal offset to the right of each tableau.
	 */
	private static final int X_OFFSET_TABLEAU = 110;
	/**
	 * The horizontal offset to the right of each homecell.
	 */
	private static final int X_OFFSET_HOMECELL = 150;
	/**
	 * Starts a little spider game.
	 */
	public LittleSpider(GUI gui) {
		super(gui);
	}
	/**
	 * Creates Homecell and Tableau piles for Little Spider.
	 */
	protected void init() {

		Deck deck = new Deck(this);
		deck.shuffle();
		
		homecells = new Homecell[4];
		
		Card c1 = deck.getSpecificCard("diamond", "ace");
		c1.setFaceUp();
		Homecell homecell1 = new Homecell(c1, this);
		homecells[0] = homecell1;
		homecell1.setHomecellNum(0);
		
		Card c2 = deck.getSpecificCard("heart", "ace");
		c2.setFaceUp();
		Homecell homecell2 = new Homecell(c2, this);
		homecells[1] = homecell2;
		homecell2.setHomecellNum(1);
		
		Card c3 = deck.getSpecificCard("club", "king");
		c3.setFaceUp();
		Homecell homecell3 = new Homecell(c3, this);
		homecells[2] = homecell3;
		homecell3.setHomecellNum(2);
		
		Card c4 = deck.getSpecificCard("spade", "king");
		c4.setFaceUp();
		Homecell homecell4 = new Homecell(c4, this);
		homecells[3] = homecell4;
		homecell4.setHomecellNum(3);
		
		tableaus = new Tableau[8];
		for(int i=0; i<tableaus.length; i++) {
			Tableau tableau = new Tableau();
			for(int j=1; j<=6; j++) {
				Card x = deck.takeCard();
				x.setFaceUp();
				x.setTableauNum(i);
				tableau.addCard(x, true);
			}
			tableaus[i]= tableau;
		}
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
}
