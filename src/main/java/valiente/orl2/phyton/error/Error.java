/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.error;

/**
 *
 * @author camran1234
 */
public class Error {
    int line, column;
    String description="";
    String type="";
    public Error(String type,int line, int column){
        this.line = line;
        this.column = column;
        this.type = type;
    }
    
    public Error(int line, int column){
            this.line = line;
            this.column = column;
    }
    
    public String execute(){
        return "";
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setType(String type){
        this.type=type;
    }
    
    public String getType(){
        return this.type;
    }
    
}
