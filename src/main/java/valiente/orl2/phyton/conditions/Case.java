/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.conditions;

import java.util.ArrayList;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.values.Operation;


/**
 *
 * @author camran1234
 */
public class Case {
    private int indentation, line, column=0;
    ArrayList<Instruction> instructions = new ArrayList();
    private Operation value;
    
    
    public Case(int indentation, int line, int column){
        this.indentation = indentation;
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

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(ArrayList<Instruction> instructions) {
        this.instructions = instructions;
    }

    public Operation getValue() {
        return value;
    }

    public void setValue(Operation value) {
        this.value = value;
    }
    
    
    
    public void execute(){
        /*empty*/
    }
}
