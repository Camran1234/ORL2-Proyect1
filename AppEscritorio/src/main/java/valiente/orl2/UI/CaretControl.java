/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.UI;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.event.*;

public class CaretControl {
    // Two controls, one is the editor and the other is our little status bar at the bottom.
    // When we update the editor, the change in caret will update the status text field.
    private JTextPane editor;
    private JTextField status;

    // Start of our caretDemo class
    public CaretControl(JTextPane textPane, JTextField textField, TextLineNumber textLineNumber ){

        editor = textPane;
        status = textField;
        
        // Add a caretListener to the editor. This is an anonymous class because it is inline and has no specific name.
        editor.addCaretListener(new CaretListener() {
            // Each time the caret is moved, it will trigger the listener and its method caretUpdate.
            // It will then pass the event to the update method including the source of the event (which is our textarea control)
            public void caretUpdate(CaretEvent e) {
                JTextArea editArea = (JTextArea)e.getSource();

                // Lets start with some default values for the line and column.
                int linenum = 1;
                int columnnum = 1;

                // We create a try catch to catch any exceptions. We will simply ignore such an error for our demonstration.
                try {
                    // First we find the position of the caret. This is the number of where the caret is in relation to the start of the JTextArea
                    // in the upper left corner. We use this position to find offset values (eg what line we are on for the given position as well as
                    // what position that line starts on.
                    int caretpos = editArea.getCaretPosition();
                    linenum = editArea.getLineOfOffset(caretpos);

                    // We subtract the offset of where our line starts from the overall caret position.
                    // So lets say that we are on line 5 and that line starts at caret position 100, if our caret position is currently 106
                    // we know that we must be on column 6 of line 5.
                    columnnum = caretpos - editArea.getLineStartOffset(linenum);

                    // We have to add one here because line numbers start at 0 for getLineOfOffset and we want it to start at 1 for display.
                    linenum += 1;
                }
                catch(Exception ex) { }

                // Once we know the position of the line and the column, pass it to a helper function for updating the status bar.
                updateStatus(linenum, columnnum);
            }
        });

        // Add the fields to the layout, the editor in the middle and the status at the bottom.

        // Give the status update value
        updateStatus(1,1);
    }

    // This helper function updates the status bar with the line number and column number.
    private void updateStatus(int linenumber, int columnnumber) {
        status.setText("Line: " + linenumber + " Column: " + columnnumber);
    }

}