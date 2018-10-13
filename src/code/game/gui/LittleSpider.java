package code.game.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


import code.game.littlespider.Tableau;
import code.game.gui.control.ClickListener;
import code.game.littlespider.Homecell;

public class LittleSpider {
	
	private JLabel[] homecellLabels;
	private JLabel[] tableauLabels;
	private code.game.littlespider.LittleSpider game;
	private Homecell[] homecells;
	private Tableau[] tableaus;
	private JLabel output;
	
	public LittleSpider() {
		homecellLabels = new JLabel[4];
		tableauLabels = new JLabel[8];
		game = new code.game.littlespider.LittleSpider();
		homecells = game.getHomecells();
		tableaus = game.getTableaus();
		output = new JLabel();
	}

	public JPanel getGame() {
		JPanel panel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel title = new JLabel(" Little Spider");
		title.setFont(new Font("Arial", Font.BOLD, 75));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(20,20,20,20);
		panel.add(title, c);
		
		JLabel home = new JLabel();
		home.setFont(new Font("Arial", Font.PLAIN, 60));
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(home, c);
		
		JLabel tab = new JLabel();
		tab.setFont(new Font("Arial", Font.PLAIN, 60));
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(tab, c);
		
		int x = 0;
		for(int i=0; i<homecells.length; i++) {
			Homecell h = homecells[i];
			code.cards.Card cc = h.getCard();
			JLabel lab = cc.getIcon();
			c.gridx = x;
			c.gridy = 2;
			c.gridwidth = 1;
			c.fill = GridBagConstraints.NONE;
			lab.addMouseListener(new ClickListener(this, lab));
			panel.add(lab, c);
			homecellLabels[i] = lab;;
			x=x+1;
		}
		
		x = 0;
		int y = 4;
		for(int i=0; i<tableaus.length; i++) {
			Tableau h = tableaus[i];
			code.cards.Card cc = h.getCard();
			JLabel lab = cc.getIcon();
			c.gridx = x;
			c.gridy = y;
			c.gridwidth = 1;
			c.fill = GridBagConstraints.NONE;
			lab.addMouseListener(new ClickListener(this, lab));
			panel.add(lab, c);
			x=x+1;
			tableauLabels[i] = lab;
			if(x==4) {
				x=0;
				y=5;
			}
		}
	
		panel.add(output);
		output.setFont(new Font("Arial", Font.PLAIN, 35));
		panel.setBackground(GUI.BG_COLOR);
		return panel;
	}
	
	
	public void Selected(JLabel label) {
		label.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
                Color.BLACK));
	      label.repaint();
	}
}
