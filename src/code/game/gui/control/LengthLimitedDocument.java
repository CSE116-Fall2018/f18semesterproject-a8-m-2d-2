package code.game.gui.control;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Used to make the RGB JTextFields only accept 3 characters.
 * @author drewf
 *
 */
public class LengthLimitedDocument extends PlainDocument {

	/** Required. */
	private static final long serialVersionUID = 1L;
	/** Number of characters to be limited to. */
	private int _limit;

	/**
	 * Constructor that takes in the limit number of characters.
	 * 
	 * @param limit Max number of characters.
	 */
    public LengthLimitedDocument(int limit) {
        super();
        _limit = limit;
    }
    
    /**
     * Inserts content into the document.
     */
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str != null && (getLength() + str.length()) <= _limit) {
            super.insertString(offset, str, attr);
        }
    }
}
