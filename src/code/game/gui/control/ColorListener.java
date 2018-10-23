package code.game.gui.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import code.game.gui.GUI;
/**
 * Used to set the background of the game to a custom color.
 *
 */
public class ColorListener implements ActionListener{
	
/**
 *  Holds GUI instance for use by the listener.
 */
	private GUI gui;
	/**
	 * Constructor for listener that initializes GUI instance.
	 * @param gui GUI for use in class.
	 */
	public ColorListener(GUI gui) {
		this.gui = gui;
	}
	/**
	 * When the menu item is clicked that this listener controls, a window will pop up 
	 * and give options for creating a new color. This had 3 JTextFields that hold the RGB
	 * color values of the background. Fields start with the values for the current color in the fields.
	 * Also handles ill formed color inputs by the user.  
	 */
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
     	
     	JLabel error = new JLabel();
     	error.setHorizontalAlignment(JLabel.CENTER);
     	error.setFont(gui.font);
     	error.setForeground(Color.RED);
     	
     	button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!checkALL(red, green, blue)) {
					error.setText("ILL FORMATTED COLOR");
				} else {
					Color color = new Color(Integer.valueOf(red.getText()), Integer.valueOf(green.getText()), Integer.valueOf(blue.getText()));
					gui.setColor(color);
					gui.getPanel().setBackground(color);
					colors.dispose();
				}
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
				if(!checkALL(red, green, blue)) {
					error.setText("ILL FORMATTED COLOR");
				}else {
					error.setText("");
					Color color = new Color(Integer.valueOf(red.getText()), Integer.valueOf(green.getText()), Integer.valueOf(blue.getText()));
					selectColor.setBackground(color);
				}
			}
     		
     	});
     	panelPreview.add(preview);
     	
     	colors.add(panelSet, BorderLayout.NORTH);
     	colors.add(panelPreview, BorderLayout.CENTER);
     	colors.add(error, BorderLayout.SOUTH);
     	colors.pack();
     	colors.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     	colors.setSize(400,200);
     	colors.setVisible(true);
     	colors.setLocationRelativeTo(gui.getFrame());
	}
/**
 * Check that the JTextFields are properly formed for a color.
 * @param red JTextField for the red component.
 * @param green JTextField for the green component.
 * @param blue JTextField for the blue component.
 * @return Returns true if color is well formed, false otherwise.
 */
	private boolean checkALL(JTextField red, JTextField green ,JTextField blue) {
		String r;
		String g;
		String b;
		try {
		r = red.getText();
		g = green.getText();
		b = blue.getText();
		} catch (NullPointerException e) {
			return false;
		}
		
		if(!checkLetter(r)) return false;
		if(!checkLetter(g)) return false;
		if(!checkLetter(b)) return false;
		
		int rint = Integer.valueOf(r);
		int gint = Integer.valueOf(g);
		int bint = Integer.valueOf(b);
		
		if(!checkINT(rint)) return false;
		if(!checkINT(gint)) return false;
		if(!checkINT(bint)) return false;
		
		return true;
	}
	/**
	 * Checks if an int is in the range for a color (0<=x<=255).
	 * @param x int to be checked.
	 * @return true if int is well formed, false otherwise.
	 */
	private boolean checkINT(int x) {
		if(x > 255 || x < 0) {
			return false;
		}
		return true;
	}
	/**
	 * Checks if input string has letters. Color components must be int.
	 * @param x String to be checked.
	 * @return true if String is well formed (ints), false otherwise.
	 */
	private boolean checkLetter(String x) {
		x.toLowerCase();
		if (x.matches("[0-9]+") && x.length() >= 1) {
			return true;
		}
		return false;
	}
	
}
