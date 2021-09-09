/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.proyecto1;

import java.io.StringReader;
import valiente.orl2.phyton.parser.*;
import java.util.ArrayList;
import valiente.orl2.phyton.conditions.Case;
import valiente.orl2.phyton.conditions.Switch;
import valiente.orl2.phyton.conditions.Default;
import valiente.orl2.phyton.cycles.For;
import valiente.orl2.phyton.cycles.While;
import valiente.orl2.phyton.error.*;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.instructions.Function;
import valiente.orl2.phyton.instructions.Principal;
import valiente.orl2.phyton.instructions.Pista;
import valiente.orl2.phyton.conditions.If;
import valiente.orl2.phyton.conditions.ElseIf;
import valiente.orl2.phyton.conditions.Else;
import valiente.orl2.phyton.cycles.DoWhile;
import valiente.orl2.phyton.instructions.ThrowFunction;
import valiente.orl2.phyton.instructions.VariableChunk;
import valiente.orl2.phyton.instructions.VariableIndicator;
import valiente.orl2.phyton.table.TableOfValue;
import valiente.orl2.phyton.values.Operation;
import valiente.orl2.phyton.values.Value;
/**
 *
 * @author camran1234
 */
public class Phyton {
    
    public void parse(String text){
        try {
            StringReader string = new StringReader(text);
            PhytonLexic lexico = new PhytonLexic(string);
            PhytonSyntax parser = new PhytonSyntax(lexico);
            TableOfValue.resetErrorsList();
            parser.parse();
            ArrayList<LexicalError> lexicalError = lexico.getList();
            ArrayList<SyntaxError> syntaxError = parser.getList(); 
            TableOfValue.lexicalErrors = lexicalError;
            TableOfValue.syntaxErrors = syntaxError;
            ArrayList<Instruction> instruction = parser.getInstructions();
            instruction = this.Reasignar(instruction, syntaxError);
            PhytonSemantic semantico = new PhytonSemantic(text);
            semantico.setSemantic(instruction);
            semantico.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Funcion solo para reordenar las instrucciones y la indentacion que deben de ir
     * @param lista
     * @param erroresLista
     * @return 
     */
    public ArrayList<Instruction> Reasignar(ArrayList<Instruction> lista, ArrayList<SyntaxError> erroresLista){
        ArrayList<Instruction> aux = new ArrayList();
        Pista pistaFunction = null ;
        Instruction function = null;
        
        int indentation=0;
        
        //Solo agregamos la funcion pista
        for(int index=0; index<lista.size(); index++){            
            if(lista.get(index) instanceof Function || lista.get(index) instanceof Pista || lista.get(index) instanceof Principal ){
                indentation = lista.get(index).getIndentation();
                
                //Para funciones o principal
                if(lista.get(index) instanceof Function || lista.get(index) instanceof Principal){
                    //Si las funciones tienen mayor grado de indentacion que deberia de tener
                    if(indentation!=1){
                        SyntaxError newError = new SyntaxError(lista.get(index).getLine(), lista.get(index).getColumn());
                        newError.setType("Inicio ilegal de la expresion");
                        newError.setDescription("Se esperaba que la funcion este adentro de una clase pista");
                        erroresLista.add(newError);
                        System.err.println(newError.getDescription());
                    }else{                        
                        
                        if(pistaFunction != null){
                            //Agregamos la instruccion
                            pistaFunction.addInstruction(lista.get(index));
                            function = lista.get(index);
                            function.setFather(pistaFunction);
                        }else{
                            SyntaxError newError = new SyntaxError(lista.get(index).getLine(), lista.get(index).getColumn());
                            newError.setType("Inicio ilegal de la expresion");
                            newError.setDescription("Se esperaba que estuviera dentro de una pista");
                            erroresLista.add(newError);
                            System.err.println(newError.getDescription());
                        }                               
                    }
                }else{
                    //Para la pista
                    //Obtenemos la pista
                    aux.add(lista.get(index));
                    pistaFunction = (Pista) lista.get(index);
                }
                
            }else{
                
                if(function ==null){
                    if(lista.get(index) instanceof VariableChunk){
                        if(lista.get(index).getIndentation() == pistaFunction.getIndentation() +1){
                            pistaFunction.addInstruction(lista.get(index));
                            lista.get(index).setFather(pistaFunction);
                        }else{
                            SyntaxError newError = new SyntaxError(lista.get(index).getLine(), lista.get(index).getColumn());
                            newError.setType("Inicio ilegal de la expresion");
                            newError.setDescription("Se esperaba una funcion");
                            erroresLista.add(newError);
                            System.err.println(newError.getDescription());
                        }
                        
                    }else{
                        SyntaxError newError = new SyntaxError(lista.get(index).getLine(), lista.get(index).getColumn());
                        newError.setType("Inicio ilegal de la expresion");
                        newError.setDescription("Se esperaba una funcion");
                        erroresLista.add(newError);
                        System.err.println(newError.getDescription());
                    }
                }else{
                    Instruction actual = lista.get(index);
                    if(lista.get(index).getIndentation()<2){
                        if(actual instanceof ThrowFunction){
                            if(indentation==1 && ((ThrowFunction) actual).getParameters().size()==0){
                                Function newFunction = new Function(actual.getLine(), actual.getColumn());
                                newFunction.setIndentation(actual.getIndentation());
                                newFunction.setParameters(new ArrayList<Operation>());
                                VariableIndicator indicator = new VariableIndicator(false,"void",new ArrayList<Operation>());
                                newFunction.setParamsIndicator(indicator);
                                Value value = new Value("function",((ThrowFunction) actual).getId(), actual.getLine(), actual.getColumn());
                                newFunction.setName(value);
                                newFunction.setFather(pistaFunction);
                                pistaFunction.addInstruction(newFunction);
                                function = newFunction;
                            }else{
                                SyntaxError newError = new SyntaxError(lista.get(index).getLine(), lista.get(index).getColumn());
                                newError.setType("Inicio ilegal de la expresion");
                                newError.setDescription("Se esperaba una function no llamar un metodo");
                                erroresLista.add(newError);
                                System.err.println(newError.getDescription());
                            }
                        }else{  
                            if(lista.get(index) instanceof VariableChunk){
                                if(lista.get(index).getIndentation() == pistaFunction.getIndentation() +1){
                                    pistaFunction.addInstruction(lista.get(index));
                                    lista.get(index).setFather(pistaFunction);
                                }else{
                                    SyntaxError newError = new SyntaxError(lista.get(index).getLine(), lista.get(index).getColumn());
                                    newError.setType("Inicio ilegal de la expresion");
                                    newError.setDescription("Se esperaba una funcion");
                                    erroresLista.add(newError);
                                    System.err.println(newError.getDescription());
                                }

                            }else{
                                SyntaxError newError = new SyntaxError(lista.get(index).getLine(), lista.get(index).getColumn());
                                newError.setType("Inicio ilegal de la expresion");
                                newError.setDescription("Se esperaba una funcion");
                                erroresLista.add(newError);
                                System.err.println(newError.getDescription());
                            }
                            
                        }
                    }else{
                        if( actual instanceof If || actual instanceof While || actual instanceof DoWhile ||  actual instanceof For || 
                                actual instanceof Switch || actual instanceof Case || actual instanceof Default){
                                //Comprobar indentacion y los agrega automaticamente
                                function = function.setTheInstruction(actual, erroresLista);
                        }else {
                                if(function == null){
                                    SyntaxError newError = new SyntaxError(actual.getLine(), actual.getColumn());
                                    newError.setType("Inicio ilegal de la expresion");
                                    newError.setDescription("Se esperaba un bloque antes");
                                    erroresLista.add(newError);
                                    System.err.println(newError.getDescription());
                                }else{
                                    function = function.setTheInstruction(actual,erroresLista);
                                }
                        }
                    }
                    
                    
                }
                              
            }   
        }
        
        return aux;
    }
    
}
