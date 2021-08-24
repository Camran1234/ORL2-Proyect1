/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.instructions;

import java.util.ArrayList;

/**
 *
 * @author camran1234
 */
public class Instruction {
    int indentation=0;
    int line, column=0;
    ArrayList<Instruction> instructions = new ArrayList();
    
    public Instruction(){}
    
    public Instruction(int indentation, int line, int column){
        this.indentation = indentation;
        this.line=line;
        this.column=column;
    }

    public Instruction(int line, int column){
        this.line = line;
        this.column = column;
    }

    public int getIndentation() {
        return indentation;
    }

    public void setIndentation(int indentation) {
        this.indentation = indentation;
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
    
    
    
    public void execute(){
        /*empty*/
    }
    
}
