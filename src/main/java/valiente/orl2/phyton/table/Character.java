/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.table;

/**
 *
 * @author camran1234
 */
public class Character {
 
    public static boolean isNumber(String text){
        try {
            int x = Integer.parseInt(text);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    
    public static char transform(String text){
        char result;
        if(isNumber(text)){
            int number = Integer.parseInt(text);
            result = (char) number;
        }else{
            result = text.charAt(0);
        }
        
        return result;
    }
    
}
