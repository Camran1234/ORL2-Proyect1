/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.UI;

import java.awt.Rectangle;

/**
 *Interface to get the number of lines and make a rectangle with line counter in the textarea
 * @author camran1234
 */
public interface LineNumberModel {
    
    public int getNumberLines();
    
    /**
        * Returns a Rectangle defining the location in the view of the parameter line. Only the y and height fields are required by callers.
        * @param line
        * @return A Rectangle defining the view coordinates of the line.
     */
    public Rectangle getLineRect(int line);
    
}
