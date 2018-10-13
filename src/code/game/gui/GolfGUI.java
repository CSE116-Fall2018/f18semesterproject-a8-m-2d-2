package code.game.gui;

import java.awt.Dimension;
import java.awt.Point;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import code.cards.Card;
import code.game.golf.Golf;
import code.game.golf.Tableau;

@SuppressWarnings("serial")
public class GolfGUI extends JLayeredPane {
	private Golf game;
	private CardIcon homecell;
	private CardIcon stockpile;
	private static final int Y_OFFSET = 25;
	private static final int X_OFFSET = 110;
	
	public GolfGUI() {
		setPreferredSize(new Dimension(780, 500));
		setBackground(GUI.BG_COLOR);
		setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
		this.game = new Golf();
		this.refresh();
	}
	
	public void refresh() {
		Tableau[] tableaus = this.game.getTableaus();
		// Origin starting point to place cards
		Point pos = new Point(10, 20);
		
		// Set the homecell icon
		if (this.homecell == null) {
			// The default homecell image when no cards have been placed
			URL path = getClass().getResource("/e.png"); // empty.png
			this.homecell = new CardIcon(path);
		} else {
			this.homecell = this.game.getHomecell().getCard().getIcon();
		}
		
		this.stockpile = this.game.getStockpile().getCard().getIcon();
		
		// Iterate through all 7 tableaus
		for(int i = 0; i < tableaus.length; i++) {
			ArrayList<Card> cards = tableaus[i].getAllCards();
			// Iterate through every card in the current tableau
			for(int j = 0; j < cards.size(); j++) {
				// Must be Integer object to denote layer in pane
				Integer depth = j;
				CardIcon icon = cards.get(j).getIcon();
				// Set bounds of this JLabel at (x,y) to width & height of icon
				icon.setBounds(pos.x, pos.y, 
						icon.getIcon().getIconWidth(), 
						icon.getIcon().getIconHeight());
				
				if (j == cards.size() - 1) {
					icon.setTop();
				}
				this.add(icon, depth, 0);
				// Move down Y_OFFSET to stagger the cards in this tableau
				pos.y += Y_OFFSET;
			}
			// Reset y-coord for next Tableau, and move x-coord right
			pos.y = 20;
			pos.x += X_OFFSET;
		}
		
		// Add the stockpile and homecell pile
		this.stockpile.setBounds(280, 300, 
				this.stockpile.getIcon().getIconWidth(), 
				this.stockpile.getIcon().getIconHeight());
		this.add(this.stockpile, Integer.valueOf(0), 0);	
		this.homecell.setBounds(400, 300, 
				this.homecell.getIcon().getIconWidth(), 
				this.homecell.getIcon().getIconHeight());
		this.add(this.homecell, Integer.valueOf(0), 0);
	}
}
