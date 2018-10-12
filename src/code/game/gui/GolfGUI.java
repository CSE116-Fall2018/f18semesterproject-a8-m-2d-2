package code.game.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import code.cards.Card;
import code.cards.Pile;
import code.game.golf.*;

public class GolfGUI {
	JLabel golf;
	Golf game;
	JLabel[] tabs;
	JLabel homecell;
	JLabel stockpile;
	
	public GolfGUI() {
		this.golf = new JLabel();
		this.game = new Golf();
		
	}
	
	public void refresh() {
		Tableau[] tabArr = this.game.getTableaus();
		this.homecell = this.game.getHomecell().getCard().getIcon();
		this.stockpile = this.game.getStockpile().getCard().getIcon();
		for(int i = 0; i < tabArr.length; i++) {
			Card c = tabArr[i].getCard();
			tabs[i] =  c.getIcon();
		}		
	}
	
	
	public JPanel getGame() {
		JPanel panel = new JPanel();
		
		
		
		
		return panel;
	}
	
}
