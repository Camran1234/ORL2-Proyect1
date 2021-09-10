/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.error;

import java.util.ArrayList;

/**
 *
 * @author camran1234
 */
public class tokenParser {
    
    public static String checkValue(String texto){
        String[] arreglo = texto.split(",");
        ArrayList<String> tokens = new ArrayList();
        for(String text:arreglo){
            if(text.equalsIgnoreCase("NUMBER")){
                
            }else if(text.equalsIgnoreCase("DECIMAL")){
                if(!tokens.contains("decimal")){
                    tokens.add("decimal");
                }
                
            }else if(text.equalsIgnoreCase("ELSE")){
                if(!tokens.contains("sino")){
                    tokens.add("sino");
                }
            }else if(text.equalsIgnoreCase("SWITCH")){
                if(!tokens.contains("switch")){
                    tokens.add("switch");
                }
            }else if(text.equalsIgnoreCase("CASE")){
                if(!tokens.contains("caso")){
                    tokens.add("caso");
                }
            }else if(text.equalsIgnoreCase("EXIT")){
                if(!tokens.contains("salir")){
                    tokens.add("salir");
                }
            }else if(text.equalsIgnoreCase("DEFAULT")){
                if(!tokens.contains("default")){
                    tokens.add("default");
                }
            }else if(text.equalsIgnoreCase("DOR")){
                if(!tokens.contains("Nota musical(Do, re, mi, fa..)")){
                    tokens.add("Nota musical(Do, re, mi, fa..)");
                }
            }else if(text.equalsIgnoreCase("RER")){
                if(!tokens.contains("Nota musical(Do, re, mi, fa..)")){
                    tokens.add("Nota musical(Do, re, mi, fa..)");
                }
            }else if(text.equalsIgnoreCase("DO")){
                if(!tokens.contains("Nota musical(Do, re, mi, fa..)")){
                    tokens.add("Nota musical(Do, re, mi, fa..)");
                }
            }else if(text.equalsIgnoreCase("RE")){
                if(!tokens.contains("Nota musical(Do, re, mi, fa..)")){
                    tokens.add("Nota musical(Do, re, mi, fa..)");
                }
            }else if(text.equalsIgnoreCase("MI")){
                if(!tokens.contains("Nota musical(Do, re, mi, fa..)")){
                    tokens.add("Nota musical(Do, re, mi, fa..)");
                }
            }else if(text.equalsIgnoreCase("FA")){
                if(!tokens.contains("Nota musical(Do, re, mi, fa..)")){
                    tokens.add("Nota musical(Do, re, mi, fa..)");
                }
            }else if(text.equalsIgnoreCase("SOL")){
                if(!tokens.contains("Nota musical(Do, re, mi, fa..)")){
                    tokens.add("Nota musical(Do, re, mi, fa..)");
                }
            }else if(text.equalsIgnoreCase("LA")){
                if(!tokens.contains("Nota musical(Do, re, mi, fa..)")){
                    tokens.add("Nota musical(Do, re, mi, fa..)");
                }
            }else if(text.equalsIgnoreCase("SI")){
                if(!tokens.contains("Nota musical(Do, re, mi, fa..)")){
                    tokens.add("Nota musical(Do, re, mi, fa..)");
                }
            }else if(text.equalsIgnoreCase("DOR")){
                if(!tokens.contains("Nota musical(Do, re, mi, fa..)")){
                    tokens.add("Nota musical(Do, re, mi, fa..)");
                }
            }else if(text.equalsIgnoreCase("RER")){
                if(!tokens.contains("Nota musical(Do, re, mi, fa..)")){
                    tokens.add("Nota musical(Do, re, mi, fa..)");
                }
            }else if(text.equalsIgnoreCase("MIR")){
                if(!tokens.contains("Nota musical(Do, re, mi, fa..)")){
                    tokens.add("Nota musical(Do, re, mi, fa..)");
                }
            }else if(text.equalsIgnoreCase("FAR")){
                if(!tokens.contains("Nota musical(Do, re, mi, fa..)")){
                    tokens.add("Nota musical(Do, re, mi, fa..)");
                }
            }else if(text.equalsIgnoreCase("SOLR")){
                if(!tokens.contains("Nota musical(Do, re, mi, fa..)")){
                    tokens.add("Nota musical(Do, re, mi, fa..)");
                }
            }else if(text.equalsIgnoreCase("LAR")){
                if(!tokens.contains("Nota musical(Do, re, mi, fa..)")){
                    tokens.add("Nota musical(Do, re, mi, fa..)");
                }
            }else if(text.equalsIgnoreCase("SIR")){
                if(!tokens.contains("Nota musical(Do, re, mi, fa..)")){
                    tokens.add("Nota musical(Do, re, mi, fa..)");
                }
            }else if(text.equalsIgnoreCase("TRUE")){
                if(!tokens.contains("expresion booleana")){
                    tokens.add("expresion booleana");
                }
            }else if(text.equalsIgnoreCase("FALSE")){
                if(!tokens.contains("expresion booleana")){
                    tokens.add("expresion booleana");
                }
            }else if(text.equalsIgnoreCase("PISTA")){
                if(!tokens.contains("pista")){
                    tokens.add("pista");
                }
            }else if(text.equalsIgnoreCase("EXTIENDE")){
                if(!tokens.contains("funcion")){
                    tokens.add("funcion");
                }
            }else if(text.equalsIgnoreCase("REPRODUCIR")){
                if(!tokens.contains("funcion")){
                    tokens.add("funcion");
                }
            }else if(text.equalsIgnoreCase("ESPERAR")){
                if(!tokens.contains("funcion")){
                    tokens.add("funcion");
                }
            }else if(text.equalsIgnoreCase("ORDENAR")){
                if(!tokens.contains("funcion")){
                    tokens.add("funcion");
                }
            }else if(text.equalsIgnoreCase("ASCENDENTE")){
                if(!tokens.contains("metodo ordenamiento")){
                    tokens.add("metodo ordenamiento");
                }
            }else if(text.equalsIgnoreCase("DESCENDENTE")){
                if(!tokens.contains("metodo ordenamiento")){
                    tokens.add("metodo ordenamiento");
                }
            }else if(text.equalsIgnoreCase("PARES")){
                if(!tokens.contains("metodo ordenamiento")){
                    tokens.add("metodo ordenamiento");
                }
            }else if(text.equalsIgnoreCase("IMPARES")){
                if(!tokens.contains("metodo ordenamiento")){
                    tokens.add("metodo ordenamiento");
                }
            }else if(text.equalsIgnoreCase("PRIMOS")){
                if(!tokens.contains("metodo ordenamiento")){
                    tokens.add("metodo ordenamiento");
                }
            }else if(text.equalsIgnoreCase("SUMARIZAR")){
                if(!tokens.contains("funcion")){
                    tokens.add("funcion");
                }
            }else if(text.equalsIgnoreCase("LONGITUD")){
                if(!tokens.contains("funcion")){
                    tokens.add("funcion");
                }
            }else if(text.equalsIgnoreCase("MENSAJE")){
                if(!tokens.contains("funcion")){
                    tokens.add("funcion");
                }
            }else if(text.equalsIgnoreCase("PRINCIPAL")){
                if(!tokens.contains("funcion")){
                    tokens.add("funcion");
                }
            }else if(text.equalsIgnoreCase("KEEP")){
                if(!tokens.contains("keep")){
                    tokens.add("keep");
                }
            }else if(text.equalsIgnoreCase("VAR")){
                if(!tokens.contains("var")){
                    tokens.add("var");
                }
            }else if(text.equalsIgnoreCase("ENTERO")){
                if(!tokens.contains("variable")){
                    tokens.add("variable");
                }
            }else if(text.equalsIgnoreCase("DOBLE")){
                if(!tokens.contains("variable")){
                    tokens.add("variable");
                }
            }else if(text.equalsIgnoreCase("BOOLEAN")){
                if(!tokens.contains("variable")){
                    tokens.add("variable");
                }
            }else if(text.equalsIgnoreCase("CARACTER")){
                if(!tokens.contains("variable")){
                    tokens.add("variable");
                }
            }else if(text.equalsIgnoreCase("CADENA")){
                if(!tokens.contains("variable")){
                    tokens.add("variable");
                }
            }else if(text.equalsIgnoreCase("ARREGLO")){
                if(!tokens.contains("arreglo")){
                    tokens.add("arrelo");
                }
            }else if(text.equalsIgnoreCase("STRING")){
                if(!tokens.contains("valor")){
                    tokens.add("valor");
                }
            }else if(text.equalsIgnoreCase("CHAR")){
                if(!tokens.contains("valor")){
                    tokens.add("valor");
                }
            }else if(text.equalsIgnoreCase("PRINCIPAL")){
                if(!tokens.contains("principal")){
                    tokens.add("principal");
                }
            }else if(text.equalsIgnoreCase("RETORNA")){
                if(!tokens.contains("retorna")){
                    tokens.add("retorna");
                }
            }else if(text.equalsIgnoreCase("FOR")){
                if(!tokens.contains("ciclo")){
                    tokens.add("ciclo");
                }
            }else if(text.equalsIgnoreCase("WHILE")){
                if(!tokens.contains("ciclo")){
                    tokens.add("ciclo");
                }
            }else if(text.equalsIgnoreCase("HACER")){
                if(!tokens.contains("ciclo")){
                    tokens.add("ciclo");
                }
            }else if(text.equalsIgnoreCase("CONTINUE")){
                if(!tokens.contains("continue")){
                    tokens.add("continue");
                }
            }else if(text.equalsIgnoreCase("SIMBOLO")){
                if(!tokens.contains("identificador")){
                    tokens.add("identificador");
                }
            }else if(text.equalsIgnoreCase("EQUAL")){
                if(!tokens.contains("=")){
                    tokens.add("=");
                }
            }else if(text.equalsIgnoreCase("EQUALIZATION")){
                if(!tokens.contains("==")){
                    tokens.add("==");
                }
            }else if(text.equalsIgnoreCase("DIFFERENTIATION")){
                if(!tokens.contains("!=")){
                    tokens.add("!=");
                }
            }else if(text.equalsIgnoreCase("GREATER_THAN")){
                if(!tokens.contains(">=")){
                    tokens.add(">=");
                }
            }else if(text.equalsIgnoreCase("LESSER_THAN")){
                if(!tokens.contains("<=")){
                    tokens.add("<=");
                }
            }else if(text.equalsIgnoreCase("NULL_")){
                if(!tokens.contains("!!")){
                    tokens.add("!!");
                }
            }else if(text.equalsIgnoreCase("GREATER")){
                if(!tokens.contains(">")){
                    tokens.add(">");
                }
            }else if(text.equalsIgnoreCase("LESSER")){
                if(!tokens.contains("<")){
                    tokens.add("<");
                }
            }else if(text.equalsIgnoreCase("EQUAL_MORE")){
                if(!tokens.contains("+=")){
                    tokens.add("+=");
                }
            }else if(text.equalsIgnoreCase("INCREASE")){
                if(!tokens.contains("++")){
                    tokens.add("++");
                }
            }else if(text.equalsIgnoreCase("DECREASE")){
                if(!tokens.contains("--")){
                    tokens.add("--");
                }
            }else if(text.equalsIgnoreCase("POW")){
                if(!tokens.contains("operador")){
                    tokens.add("operador");
                }
            }else if(text.equalsIgnoreCase("ADD")){
                if(!tokens.contains("operador")){
                    tokens.add("operador");
                }
            }else if(text.equalsIgnoreCase("MINUS")){
                if(!tokens.contains("operador")){
                    tokens.add("operador");
                }
            }else if(text.equalsIgnoreCase("MULTIPLY")){
                if(!tokens.contains("operador")){
                    tokens.add("operador");
                }
            }else if(text.equalsIgnoreCase("DIV")){
                if(!tokens.contains("operador")){
                    tokens.add("operador");
                }
            }else if(text.equalsIgnoreCase("MODULE")){
                if(!tokens.contains("operador")){
                    tokens.add("operador");
                }
            }else if(text.equalsIgnoreCase("NAND")){
                if(!tokens.contains("Operador logico")){
                    tokens.add("Operador logico");
                }
            }else if(text.equalsIgnoreCase("AND")){
                if(!tokens.contains("Operador logico")){
                    tokens.add("Operador logico");
                }
            }else if(text.equalsIgnoreCase("NOR")){
                if(!tokens.contains("Operador logico")){
                    tokens.add("Operador logico");
                }
            }else if(text.equalsIgnoreCase("OR")){
                if(!tokens.contains("Operador logico")){
                    tokens.add("Operador logico");
                }
            }else if(text.equalsIgnoreCase("XOR")){
                if(!tokens.contains("Operador logico")){
                    tokens.add("Operador logico");
                }
            }else if(text.equalsIgnoreCase("NOT")){
                if(!tokens.contains("Operador logico")){
                    tokens.add("Operador logico");
                }
            }else if(text.equalsIgnoreCase("TAB")){
                if(!tokens.contains("Tab")){
                    tokens.add("Tab");
                }
            }else if(text.equalsIgnoreCase("SPACE")){
                if(!tokens.contains("salto de linea")){
                    tokens.add("salto de linea");
                }
            }else if(text.equalsIgnoreCase("OPEN_BRACKET")){
                if(!tokens.contains("[")){
                    tokens.add("[");
                }
            }else if(text.equalsIgnoreCase("CLOSE_BRACKET")){
                if(!tokens.contains("]")){
                    tokens.add("]");
                }
            }else if(text.equalsIgnoreCase("OPEN_CURLY")){
                if(!tokens.contains("{")){
                    tokens.add("{");
                }
            }else if(text.equalsIgnoreCase("CLOSE_CURLY")){
                if(!tokens.contains("}")){
                    tokens.add("}");
                }
            }else if(text.equalsIgnoreCase("OPEN_PARENTHESIS")){
                if(!tokens.contains("(")){
                    tokens.add("(");
                }
            }else if(text.equalsIgnoreCase("CLOSE_PARENTHESIS")){
                if(!tokens.contains(")")){
                    tokens.add(")");
                }
            }else if(text.equalsIgnoreCase("COLON")){
                if(!tokens.contains(";")){
                    tokens.add(";");
                }
            }else if(text.equalsIgnoreCase("COMA")){
                if(!tokens.contains(",")){
                    tokens.add(",");
                }
            }
        }
        StringBuilder string = new StringBuilder();
        for(int index=0; index<tokens.size(); index++){
            String line = tokens.get(index);
            if(index==tokens.size()-1){
                string.append(line).append(".");
            }else{
                string.append(line).append(", ");
            }
        }
        
        return string.toString();
    }
    
}
