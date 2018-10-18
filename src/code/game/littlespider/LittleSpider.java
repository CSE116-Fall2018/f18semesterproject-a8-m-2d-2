package code.game.littlespider;


import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import code.cards.Card;
import code.cards.Deck;
import code.game.Game;
import code.game.gui.GUI;


/**
 * This class holds the code for the Little Spider game. Its constructor initializes four homecell piles and eight tableau piles.
 * 
 * @author Drew Fiutko
 */
public class LittleSpider extends Game implements ActionListener{
	
	/**
	 * Required.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Holds an array of Homecell instances that are used by the game.
	 */
	private Homecell[] homecells;
	
	/**
	 * Holds an array of Tableau instances that are used by the game.
	 */
	private Tableau[] tableaus;
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
	private void init() {

		Deck deck = new Deck();
		deck.shuffle();
		
		homecells = new Homecell[4];
		
		Card c1 = deck.getSpecificCard("diamond", "ace");
		c1.setFaceUp();
		Homecell homecell1 = new Homecell(c1);
		homecells[0] = homecell1;
		
		Card c2 = deck.getSpecificCard("heart", "ace");
		c2.setFaceUp();
		Homecell homecell2 = new Homecell(c2);
		homecells[1] = homecell2;
		
		Card c3 = deck.getSpecificCard("club", "king");
		c3.setFaceUp();
		Homecell homecell3 = new Homecell(c3);
		homecells[2] = homecell3;
		
		Card c4 = deck.getSpecificCard("spade", "king");
		c4.setFaceUp();
		Homecell homecell4 = new Homecell(c4);
		homecells[3] = homecell4;
		
		tableaus = new Tableau[8];
		for(int i=0; i<tableaus.length; i++) {
			Tableau tableau = new Tableau();
			for(int j=1; j<=6; j++) {
				Card x = deck.takeCard();
				x.setFaceUp();
				tableau.addCard(x, true);
			}
			tableaus[i]= tableau;
		}
		refresh();
	}
	/**
	 * This method refreshes the game panel during the little spider game 
	 * to ensure the correct Cards are showing at all times.  Called every
	 * time a correct move is made.
	 */
	public void refresh() {
		// Origin starting point to place homecell cards
				Point pos = new Point(175, 20);
				
				// Add homecell piles to top of frame without displaying pile
				for(int i = 0; i < this.homecells.length; i++) {
					Card icon = this.homecells[i].getCard();
					icon.setBounds(pos.x, pos.y, icon.getIcon().getIconWidth(), icon.getIcon().getIconHeight());
					icon.setTop();
					this.add(icon, 0, 0); // depth always 0
					pos.x += X_OFFSET_HOMECELL;
				}
				
				// Add tableaus and reset starting point with displaying pile
				pos.x = 10;
				pos.y = 200;
				for(int i = 0; i < this.tableaus.length; i++) {
					ArrayList<Card> cards = this.tableaus[i].getCards();
					for(int j = 0; j < cards.size(); j++) {
						Integer depth = j;
						Card icon = cards.get(j);
						icon.setBounds(pos.x, pos.y, icon.getIcon().getIconWidth(), icon.getIcon().getIconHeight());
						
						if(j == cards.size() - 1) {
							icon.setTop();
						}
						
						this.add(icon, depth, 0);
						
						pos.y += Y_OFFSET;
					}
					
					pos.y = 200;
					pos.x += X_OFFSET_TABLEAU;
				}
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
	 * Returns array of tableaus.
	 * 
	 * @return Array of tableau instances.
	 */
	public Tableau[] getTableaus() {
		return tableaus;
	}
	/**
	 * Initializes Little Spider and adds it to a blank frame. 
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		init();
		this.gui.getPanel().removeAll();
		this.gui.getPanel().add(this);
		this.gui.getFrame().setSize(925, 800);	
	}
}
