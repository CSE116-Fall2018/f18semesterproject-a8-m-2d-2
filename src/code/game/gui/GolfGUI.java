package code.game.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
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
		golf.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		// 2 rows 7 columns, 20 vertical + horizontal margin spacing
		this.golf.setLayout(new GridLayout(2, 7, 30, 30));
		this.game = new Golf();
		this.tabs = new JLabel[7];
		this.refresh();
	}
	
	public void refresh() {
		Tableau[] tabArr = this.game.getTableaus();
		
		// Set the homecell icon
		if (this.homecell == null) {
			this.homecell = new JLabel();
			URL imgURL = getClass().getResource("/e.png");
			Image img = new ImageIcon(imgURL).getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH);
			ImageIcon resizedImg = new ImageIcon(img);
			homecell.setIcon(resizedImg);
			homecell.setHorizontalAlignment(JLabel.CENTER);
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
