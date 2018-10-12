package code.game.gui;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import code.cards.Card;
import code.game.golf.*;

public class GolfGUI {
	JPanel golf;
	Golf game;
	JLabel[] tabs;
	JLabel homecell;
	JLabel stockpile;
	
	public GolfGUI() {
		this.golf = new JPanel();
		this.golf.setLayout(new GridLayout(7, 2));
		this.game = new Golf();
		this.tabs = new JLabel[7];
		this.refresh();
	}
	
	public void refresh() {
		Tableau[] tabArr = this.game.getTableaus();
		
		if (this.homecell == null) {
			this.homecell = new JLabel();
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
		
		GridBagConstraints c = new GridBagConstraints();
		
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
