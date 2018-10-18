package code.game.gui.control;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LengthLimitedDocument extends PlainDocument {

	/**
	 * Required.
	 */
	private static final long serialVersionUID = 1L;
	private int _limit;

    public LengthLimitedDocument(int limit) {
        super();
        _limit = limit;
    }
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str != null && (getLength() + str.length()) <= _limit) {
            super.insertString(offset, str, attr);
        }
    }
}
