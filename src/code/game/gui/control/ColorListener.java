package code.game.gui.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import code.game.gui.GUI;

public class ColorListener implements ActionListener{

	private GUI gui;
	
	public ColorListener(GUI gui) {
		this.gui = gui;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFrame colors = new JFrame("Background Color Select");
     	JPanel panelSet = new JPanel();
     	Color current = gui.getColor();
     	
     	JTextField red = new JTextField(3);
     	red.setDocument(new LengthLimitedDocument(3));
     	red.setText(String.valueOf(current.getRed()));
     	
     	JTextField green = new JTextField(3);
     	green.setDocument(new LengthLimitedDocument(3));
     	green.setText(String.valueOf(current.getGreen()));
     	
     	JTextField blue = new JTextField(3);
     	blue.setDocument(new LengthLimitedDocument(3));
     	blue.setText(String.valueOf(current.getBlue()));
     	
     	panelSet.add(new JLabel("R"));
     	panelSet.add(red);
     	panelSet.add(new JLabel("G"));
     	panelSet.add(green);
     	panelSet.add(new JLabel("B"));
     	panelSet.add(blue);
     	JButton button = new JButton("SET");
     	button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color color = new Color(Integer.valueOf(red.getText()), Integer.valueOf(green.getText()), Integer.valueOf(blue.getText()));
				gui.setColor(color);
				gui.getPanel().setBackground(color);
				colors.dispose();
			}
     		
     	});
     	panelSet.add(button);
     	
     	JPanel panelPreview = new JPanel();
     	JLabel selectColor = new JLabel("                 ");
     	selectColor.setFont(gui.font);
     	selectColor.setOpaque(true);
     	selectColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
     	selectColor.setBackground(current);
     	panelPreview.add(selectColor);
     	JButton preview = new JButton("Preview");
     	preview.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Color color = new Color(Integer.valueOf(red.getText()), Integer.valueOf(green.getText()), Integer.valueOf(blue.getText()));
				selectColor.setBackground(color);
				
			}
     		
     	});
     	panelPreview.add(preview);
     	
     	colors.add(panelSet, BorderLayout.NORTH);
     	colors.add(panelPreview, BorderLayout.CENTER);
     	colors.pack();
     	colors.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     	colors.setSize(400,200);
     	colors.setVisible(true);
     	colors.setLocationRelativeTo(gui.getFrame());
	}

	
	
}
