package code.game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class HomecellGUI extends JLabel implements ActionListener, MouseListener {
	protected boolean top;
	
	public HomecellGUI(URL filePath) {
		this.top = false;
		this.addMouseListener(this);
		Image img = new ImageIcon(filePath).getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH);
		ImageIcon resizedImg = new ImageIcon(img);
		this.setIcon(resizedImg);
		this.setPreferredSize(new Dimension(100, 140));
		this.setOpaque(true);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.TOP);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!top) {
			return;
		}
		// TODO Handle functionality properly
		this.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void setTop() {
		this.top = true;
	}

}
