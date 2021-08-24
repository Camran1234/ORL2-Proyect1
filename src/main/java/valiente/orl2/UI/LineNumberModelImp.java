/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.UI;
import java.awt.Rectangle;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
/**
 *
 * @author camran1234
 */
public class LineNumberModelImp implements LineNumberModel {
        private JTextArea textArea=null;                     
        
        public LineNumberModelImp(JTextArea textArea){
                this.textArea = textArea;
        }
        
        @Override
        public int getNumberLines() {
                return textArea.getLineCount();
        }
        
        @Override
        public Rectangle getLineRect(int line) {
                try{
                        return textArea.modelToView(textArea.getLineStartOffset(line));
                }catch(BadLocationException e){
                        e.printStackTrace();
                        return new Rectangle();
                }	
        }
}
