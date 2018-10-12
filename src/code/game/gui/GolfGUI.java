package code.game.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import code.cards.Card;
import code.game.golf.Golf;
import code.game.golf.Tableau;

public class GolfGUI {
	JPanel golf;
	Golf game;
	JLabel[] tabs;
	JLabel homecell;
	JLabel stockpile;
	
	public GolfGUI() {
		this.golf = new JPanel();
		golf.setBackground(GUI.BG_COLOR);
		this.golf.setLayout(new GridLayout(2, 7, 20, 20));
		this.game = new Golf();
		this.tabs = new JLabel[7];
		this.refresh();
	}
	
	public void refresh() {
		Tableau[] tabArr = this.game.getTableaus();
		
		// Set the homecell icon
		if (this.homecell == null) {
			this.homecell = new JLabel();
			URL imgURL = getClass().getResource("/gold.gif");
			ImageIcon icn = new ImageIcon(imgURL);
			homecell.setIcon(icn);
			
			Dimension d = new Dimension(icn.getIconWidth() + 10, icn.getIconHeight() + 10);
		    homecell.setSize(d);
		    homecell.setPreferredSize(d);
		    homecell.setMaximumSize(d);
		    homecell.setMinimumSize(d);
		} else {
			this.homecell = this.game.getHomecell().getCard().getIcon();
		}
		this.stockpile = this.game.getStockpile().getCard().getIcon();
		
		for(int i = 0; i < tabArr.length; i++) {
			Card c = tabArr[i].getCard();
			this.tabs[i] =  c.getIcon();
		}
	}
	
	
	public JPanel getGame() {
		for (int i = 0; i < this.tabs.length; i++) {
			this.golf.add(tabs[i]);
		}
		
		this.golf.add(new JLabel());
		this.golf.add(new JLabel());
		this.golf.add(this.stockpile);	
		this.golf.add(new JLabel());
		this.golf.add(this.homecell);
		this.golf.add(new JLabel());
		this.golf.add(new JLabel());
		
		return this.golf;
	}
	
}
