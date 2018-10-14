package code.game.gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import code.cards.Card;

@SuppressWarnings("serial")
public class HomecellGUI extends JLabel implements ActionListener, MouseListener {
	
	public HomecellGUI(URL filePath) {
		this.addMouseListener(this);
		Image img = new ImageIcon(filePath).getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH);
		ImageIcon resizedImg = new ImageIcon(img);
		this.setIcon(resizedImg);
		this.setPreferredSize(new Dimension(100, 140));
		this.setOpaque(true);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.TOP);
	}
	
	/**
	 * Sets the icon of the homecell to the most recently added card.
	 * 
	 * @param Card card the most recently added card.
	 */
	public void setTopCard(Card card) {
		this.setIcon(card.getIcon());
	}

	/**
	 * Accepts or rejects cards?
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO this method
	}

	/**
	 * This method does nothing.
	 */
	@Override
	public void mousePressed(MouseEvent e) {}

	/**
	 * This method does nothing.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {}

	/**
	 * This method does nothing.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/**
	 * This method does nothing.
	 */
	@Override
	public void mouseExited(MouseEvent e) {}

	/**
	 * This method does nothing.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {}
}
