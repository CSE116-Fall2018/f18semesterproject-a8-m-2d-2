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
	
	/** Holds GUI instance for use by the listener. */
	private GUI gui;
	
	/**
	 * Constructor for listener that initializes GUI instance.
	 * 
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
		// Initializing the JComponents on which the color selection window will be displayed
		JFrame colors = new JFrame("Background Color Select");
     	JPanel panelSet = new JPanel();
     	Color current = gui.getColor();
     	// Initializing the JTextField for the red value of the color to be set
     	JTextField red = new JTextField(3);
     	red.setDocument(new LengthLimitedDocument(3));
     	red.setText(String.valueOf(current.getRed()));
     	// Initializing the JTextField for the blue value of the color to be set
     	JTextField green = new JTextField(3);
     	green.setDocument(new LengthLimitedDocument(3));
     	green.setText(String.valueOf(current.getGreen()));
     	// Initializing the JTextField for the green value of the color to be set
     	JTextField blue = new JTextField(3);
     	blue.setDocument(new LengthLimitedDocument(3));
     	blue.setText(String.valueOf(current.getBlue()));
     	
     	// Adding the JComponents to be displayed on the panel
     	panelSet.add(new JLabel("R"));
     	panelSet.add(red);
     	panelSet.add(new JLabel("G"));
     	panelSet.add(green);
     	panelSet.add(new JLabel("B"));
     	panelSet.add(blue);
     	JButton button = new JButton("SET");
     	
     	//Creating the display for an invalid color entry
     	JLabel error = new JLabel();
     	error.setHorizontalAlignment(JLabel.CENTER);
     	error.setFont(gui.FONT);
     	error.setForeground(Color.RED);
     	
     	// Creating the button that allows you to set the color
     	button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// If specified Color(red, green, blue) is improperly formatted,
				// an error message will appear
				if(!checkALL(red, green, blue)) {
					error.setText("ILL FORMATTED COLOR");
				} 
				// If specified Color(red, green, blue) is properly formatted,
				// the game's background color will be set and the frame will close.
				else {
					Color color = new Color(Integer.valueOf(red.getText()), Integer.valueOf(green.getText()), Integer.valueOf(blue.getText()));
					gui.setColor(color);
					gui.getPanel().setBackground(color);
					colors.dispose();
				}
			}
     		
     	});
     	panelSet.add(button);
     	
     	//Creating the panel displaying a preview of 
     	JPanel panelPreview = new JPanel();
     	JLabel selectColor = new JLabel("                 ");
     	selectColor.setFont(gui.FONT);
     	selectColor.setOpaque(true);
     	selectColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
     	selectColor.setBackground(current);
     	panelPreview.add(selectColor);
     	JButton preview = new JButton("Preview");
     	preview.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// If specified Color(red, green, blue) is improperly formatted,
				// an error message will appear
				if(!checkALL(red, green, blue)) {
					error.setText("ILL FORMATTED COLOR");
				}
				// If specified Color(red, green, blue) is properly formatted,
				// the preview color will be set
				else {
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
	}
	
	/**
	 * Check that the JTextFields are properly formed for a color.
	 * 
	 * @param red JTextField for the red component.
	 * @param green JTextField for the green component.
	 * @param blue JTextField for the blue component.
	 * @return Returns true if color is well formed, false otherwise.
	 */
	private boolean checkALL(JTextField red, JTextField green ,JTextField blue) {
		String r;
		String g;
		String b;
		
		//If any of the input parameters are null, the method will return false
		try {
		r = red.getText();
		g = green.getText();
		b = blue.getText();
		} catch (NullPointerException e) {
			return false;
		}
		
		int rint;
		int gint;
		int bint;
		try {
			rint = Integer.valueOf(r);
			gint = Integer.valueOf(g);
			bint = Integer.valueOf(b);
		}catch(NumberFormatException e) {
			return false;
		}
		
		if(rint > 255) red.setText("255");
		else if(rint < 0) red.setText("0");
		
		if(gint > 255) green.setText("255");
		else if(gint < 0) green.setText("0");
		
		if(bint > 255) blue.setText("255");
		else if(bint < 0) blue.setText("0");
		
		return true;
	}
	
	/**
	 * Checks if an int is in the range for a color (0<=x<=255).
	 * 
	 * @param x int to be checked.
	 * @return true if int is well formed, false otherwise.
	 */

	
	
}
