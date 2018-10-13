package code.game.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

//import com.sun.prism.Image;

import code.game.littlespider.Homecell;

public class LittleSpider {

	public JPanel getGame() {
		JPanel panel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		
		code.game.littlespider.LittleSpider game = new code.game.littlespider.LittleSpider();
		Homecell[] homecells = game.getHomecells();
		
		// get card info
		
		JLabel title = new JLabel("Little Spider");
		title.setFont(new Font("Arial", Font.BOLD, 75));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(title, c);
		
		JLabel h1 = getImageLabel("/ad.png");
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		panel.add(h1, c);
		
		JLabel h2 = getImageLabel("/ah.png");
		c.gridx = 1;
		c.gridy = 1;
		panel.add(h2, c);
		
		JLabel h3 = getImageLabel("/kc.png");
		c.gridx = 2;
		c.gridy = 1;
		panel.add(h3, c);
		
		JLabel h4 = getImageLabel("/ks.png");
		c.gridx = 3;
		c.gridy = 1;
		panel.add(h4, c);
		
		
		JLabel retVal = getImageLabel("/10h.png");
		c.gridx = 0;
		c.gridy = 2;
		panel.add(retVal, c);
		
		JLabel output = new JLabel("");
		panel.add(output);
		output.setFont(new Font("Arial", Font.PLAIN, 35));
		
		return panel;
	}
	
	public JLabel getImageLabel(String file) {
		JLabel retVal = new JLabel();
	    java.net.URL imgURL = this.getClass().getResource(file); // file is in form "/filename.git" with new source folder configuration
	    if (imgURL == null) {
	      throw new IllegalArgumentException("Couldn't find file: " + imgURL);
	    }
	    ImageIcon cardImage = new ImageIcon(imgURL);
	    java.awt.Image image = cardImage.getImage();
	    java.awt.Image newimg = image.getScaledInstance(219, 291,  java.awt.Image.SCALE_SMOOTH); // 73,97 (original dimensions) resizes card by a 
	    cardImage = new ImageIcon(newimg);  													// factor of 3 so that it is not tiny
	    
	    retVal.setIcon(cardImage);
	    Dimension d = new Dimension(cardImage.getIconWidth() + 20, cardImage.getIconHeight() + 20); // creates a small separation between cards
	    retVal.setSize(d);
	    retVal.setPreferredSize(d);
	    retVal.setMaximumSize(d);
	    retVal.setMinimumSize(d);
		return retVal;
	}
}
