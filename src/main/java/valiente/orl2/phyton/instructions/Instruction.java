/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.instructions;

import java.util.ArrayList;
import valiente.orl2.phyton.conditions.Else;
import valiente.orl2.phyton.conditions.ElseIf;
import valiente.orl2.phyton.conditions.If;
import valiente.orl2.phyton.cycles.DoWhile;
import valiente.orl2.phyton.cycles.For;
import valiente.orl2.phyton.cycles.While;
import valiente.orl2.phyton.error.SyntaxError;

/**
 *
 * @author camran1234
 */
public class Instruction {
    int indentation=0;
    int line, column=0;
    ArrayList<Instruction> instructions = new ArrayList();
    Instruction father = null;
    
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

    public void setFather(Instruction father){
        this.father = father;
    }
    
    public Instruction getFather(){
        return this.father;
    }
    /*//Es un error porque la indentacion no concuerda
                                        SyntaxError newError = new SyntaxError(lista.get(index).getLine(), lista.get(index).getColumn());
                                        newError.setType("Inicio ilegal de la expresion");
                                        newError.setDescription("Se esperaba que la instruccion estuviera dentro de una instruccion contenedora");
                                        erroresLista.add(newError);*/
    /**
     * Retorna una instruccion si se pudo agregar la instruccion despues de una funcion
     * Retorna nulo si no se pudo agregar la instruccion, ni si quiera si se pudo agregar en una funcion o principal
     * @param instruction
     * @param erroresLista
     * @return 
     */
    public Instruction setTheInstruction(Instruction instruction, ArrayList<SyntaxError> erroresLista){
        Instruction returnInstruction=null;
        //Significa que esta adentro de esta instruccion
        if(instruction.getIndentation() == this.indentation +1){
            this.addInstruction(instruction);
            instruction.setFather(this);
            if(instruction instanceof If || instruction instanceof While || instruction instanceof DoWhile
                    || instruction instanceof For){
                    return instruction;
            }
            return this;
        }else if(instruction.getIndentation() > this.indentation+1){
            //Es un error porque la indentacion no concuerda
            SyntaxError newError = new SyntaxError(instruction.getLine(), instruction.getColumn());
            newError.setType("Inicio ilegal de la expresion");                                      
            newError.setDescription("Se esperaba que la instruccion estuviera dentro de una instruccion contenedora");
            erroresLista.add(newError);
            System.err.println(newError.getDescription());
        }else if(instruction.getIndentation() <= this.indentation){
            
            if(this instanceof Pista){
                if(instruction instanceof VariableChunk){
                    if(instruction.getIndentation() == this.getIndentation() +1){
                        this.addInstruction(instruction);
                        instruction.setFather(this);
                        return this;
                    }else{
                        SyntaxError newError = new SyntaxError(instruction.getLine(), instruction.getColumn());
                        newError.setType("Inicio ilegal de la expresion");
                        newError.setDescription("Se esperaba que la instruccion estuviera dentro de una instruccion contenedora");
                        erroresLista.add(newError);
                        System.err.println(newError.getDescription());
                    }
                }else{
                    SyntaxError newError = new SyntaxError(instruction.getLine(), instruction.getColumn());
                    newError.setType("Inicio ilegal de la expresion");
                    newError.setDescription("Se esperaba que la instruccion estuviera dentro de una instruccion contenedora");
                    erroresLista.add(newError);
                    System.err.println(newError.getDescription());
                }
                
                
            }else{
                //Asignamos
                if(this.indentation == instruction.getIndentation()){
                    if(instruction instanceof ElseIf){
                        //Manejo de errores para else if
                        if(this instanceof If){                                
                            If newIf = (If) this;
                            newIf.setNewElse(instruction, erroresLista);                            
                            //returnInstruction = this.father;
                            return instruction;
                        }else if((ElseIf) this.getFather() instanceof If){
                            If newIf = (If) this;
                            newIf.setNewElse(instruction,erroresLista);
                            return instruction;
                        }else{
                            SyntaxError newError = new SyntaxError(instruction.getLine(), instruction.getColumn());
                            newError.setType("Inicio ilegal de la expresion");
                            newError.setDescription("Se esperaba un if antes");
                            erroresLista.add(newError);
                            System.err.println(newError.getDescription());
                        }
                    }else if(instruction instanceof Else){
                        //Manejo de errores para Else
                        if(this instanceof ElseIf){
                            If oldIf = (If) this.getFather();
                            oldIf.setNewElse(instruction, erroresLista);
                            //returnInstruction = this.father;
                            return instruction;
                        }else if( this instanceof If){
                            If newIf = (If) this;
                            newIf.setNewElse(instruction, erroresLista);
                            //returnInstruction = this.father;
                            return instruction;
                        }else{
                            SyntaxError newError = new SyntaxError(instruction.getLine(), instruction.getColumn());
                            newError.setType("Inicio ilegal de la expresion");
                            newError.setDescription("Se esperaba un if o Else If antes");
                            erroresLista.add(newError);
                            System.err.println(newError.getDescription());
                        }
                    }else if(instruction instanceof While){
                        if(this instanceof DoWhile){
                            DoWhile thisDo = (DoWhile) this;
                            if(thisDo.getConditionWhile()!=null){
                                returnInstruction = this.father.setTheInstruction(instruction, erroresLista);
                            }else{
                                thisDo.setConditionWhile((While) instruction);
                                return thisDo.getFather();
                            }
                            
                        }else{
                          returnInstruction = this.father.setTheInstruction(instruction, erroresLista);  
                        }
                    }else{
                        //Le mandamos los parametros al padre
                        returnInstruction = this.father.setTheInstruction(instruction, erroresLista);
                    }
                }else if(this.indentation > instruction.getIndentation()){
                    returnInstruction = this.father.setTheInstruction(instruction, erroresLista);
                }
            }
            
            
        }
        return returnInstruction;
    }
    
    /**
     * Agrega una instruccion
     * @param instruction 
     */
    public void addInstruction(Instruction instruction){
        this.instructions.add(instruction);
    }
    
    
    public ArrayList<Instruction> getInstruction(){
        return this.instructions;
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
    
    
    
}
