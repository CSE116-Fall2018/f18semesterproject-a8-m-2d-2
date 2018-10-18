package code.game.gui.control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
     	JPanel panel = new JPanel();
     	Color current = gui.getColor();
     	
     	JTextField red = new JTextField(3);
     	red.setText(String.valueOf(current.getRed()));
     	JTextField green = new JTextField(3);
     	green.setText(String.valueOf(current.getGreen()));
     	JTextField blue = new JTextField(3);
     	blue.setText(String.valueOf(current.getBlue()));
     	panel.add(new JLabel("R"));
     	panel.add(red);
     	panel.add(new JLabel("G"));
     	panel.add(green);
     	panel.add(new JLabel("B"));
     	panel.add(blue);
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
     	panel.add(button);
     	colors.add(panel);
     	colors.pack();
     	colors.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     	colors.setSize(400,200);
     	colors.setVisible(true);
     	colors.setLocationRelativeTo(gui.getFrame());
	}

	
	
}
