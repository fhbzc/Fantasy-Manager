package Basketballmanager.util;

import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class JTextAreaAutoMove {
	/**
	 * Utility class:
	 * automatically scrolling TextArea
	 * @param target
	 */
	public static void AutoMove(JTextArea target)
	{
		target.getDocument().addDocumentListener(new DocumentListener(){

			@Override
			public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
				target.setCaretPosition(target.getText().length());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			}
			});
	}
}
