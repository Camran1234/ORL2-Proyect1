package valiente.orl2.phyton.parser;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import java_cup.runtime.*;
import static valiente.orl2.phyton.parser.PhytonSym.*;
import valiente.orl2.phyton.error.LexicalError;
import java.util.ArrayList;
%%
%class PhytonLexic
%cup
%unicode
%line
%column
%public

%{
    ArrayList<LexicalError> list = new ArrayList();
    LexicalError error;

    public ArrayList<LexicalError> getList(){
        return list;
    }
%}

/* Regular Expression */
WhiteSpace = [ \r\b\f]
/*Commentary*/
Commentary = >>[^\n]+
BlockCommentary = <-([^-]|-[^>]|-+[^->])*-*->

/*Numbers*/
Number =  (0|([1-9][0-9]*))
Decimal = (([1-9][0-9]*|0?)[.])[0-9]{1,5}
Simbolo = ([a-zA-Z])([a-zA-Z0-9]|"_")*
String = [\"][^\"]*[\"]
Char = [\'][^\']*[\']

%%

<YYINITIAL> {
   
        /*Numbers*/
    {Number}    {System.out.println("NUMERO "+yytext());if(error==null){ return new Symbol(NUMBER, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    {Decimal}   {System.out.println("DECIMAL "+yytext());if(error==null){ return new Symbol(DECIMAL, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}

/*Condiciones*/
    [sS]"ino"  {System.out.println(yytext());
                if(error==null){
                    return new Symbol(ELSE, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }
                
    }
    [sS]"witch"    {System.out.println(yytext());
        if(error==null){
            return new Symbol(SWITCH, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }
        
        }
    [cC]"aso"  {System.out.println(yytext());if(error==null){ return new Symbol(CASE, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [sS]"alir"  {System.out.println(yytext());if(error==null){ return new Symbol(EXIT, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [dD]"efault"   {System.out.println(yytext());if(error==null){ return new Symbol(DEFAULT, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}

    /*Notas*/
    [dD]"o#" {System.out.println(yytext());if(error==null){ return new Symbol(DOR, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [rR]"e#" {System.out.println(yytext());if(error==null){ return new Symbol(RER, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [dD]"o"    {System.out.println(yytext());if(error==null){ return new Symbol(DO, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [rR]"e"    {System.out.println(yytext());if(error==null){ return new Symbol(RE, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [mM]"i"    {System.out.println(yytext());if(error==null){ return new Symbol(MI, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [fF]"a"    {System.out.println(yytext());if(error==null){ return new Symbol(FA, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [lL]"a"    {System.out.println(yytext());if(error==null){ return new Symbol(LA, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [sS]"ol"   {System.out.println(yytext());if(error==null){ return new Symbol(SOL, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
        
    [sS]"i"    {System.out.println(yytext());if(error==null){ return new Symbol(SI, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}

    /*Reserved Words*/
    [vV]"erdadero"|[tT]"rue" {System.out.println(yytext());if(error==null){ return new Symbol(TRUE, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [fF]"also"|[fF]"alse" {System.out.println(yytext());if(error==null){ return new Symbol(FALSE, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [pP]"ista" {System.out.println(yytext());if(error==null){ return new Symbol(PISTA, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [eE]"xtiende"  {System.out.println(yytext());if(error==null){ return new Symbol(EXTIENDE, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [rR]"eproducir"    {System.out.println(yytext());if(error==null){ return new Symbol(REPRODUCIR, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [eE]"sperar"   {System.out.println(yytext());if(error==null){ return new Symbol(ESPERAR, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [oO]"rdenar"   {System.out.println(yytext());if(error==null){ return new Symbol(ORDENAR, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [aA]"scendente"  {System.out.println(yytext());if(error==null){ return new Symbol(ASCENDENTE, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [dD]"escendente" {System.out.println(yytext());if(error==null){ return new Symbol(DESCENDENTE, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [pP]"ares" {System.out.println(yytext());if(error==null){ return new Symbol(PARES, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [iI]"mpares"  {System.out.println(yytext());if(error==null){ return new Symbol(IMPARES, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [pP]"rimos"  {System.out.println(yytext());if(error==null){ return new Symbol(PRIMOS, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [sS]"umarizar" {System.out.println(yytext());if(error==null){ return new Symbol(SUMARIZAR, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [lL]"ongitud"  {System.out.println(yytext());if(error==null){ return new Symbol(LONGITUD, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [mM]"ensaje"   {System.out.println(yytext());if(error==null){ return new Symbol(MENSAJE, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [pP]"rincipal" {System.out.println(yytext());if(error==null){ return new Symbol(PRINCIPAL, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [kK]"eep"  {System.out.println(yytext());if(error==null){ return new Symbol(KEEP, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [vV]"ar"  {System.out.println(yytext());if(error==null){ return new Symbol(VAR, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [eE]"ntero"  {System.out.println(yytext());if(error==null){ return new Symbol(ENTERO, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [dD]"oble"  {System.out.println(yytext());if(error==null){ return new Symbol(DOBLE, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [bB]"oolean"  {System.out.println(yytext());if(error==null){ return new Symbol(BOOLEAN, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [cC]"aracter"  {System.out.println(yytext());if(error==null){ return new Symbol(CARACTER, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [cC]"adena"  {System.out.println(yytext());if(error==null){ return new Symbol(CADENA, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [aA]"rreglo"  {System.out.println(yytext());if(error==null){ return new Symbol(ARREGLO, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    {String}  {System.out.println(yytext());if(error==null){ return new Symbol(STRING, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    {Char}  {System.out.println(yytext());if(error==null){ return new Symbol(CHAR, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [pP]"rincipal" {System.out.println(yytext());if(error==null){ return new Symbol(PRINCIPAL, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [rR]"etorna"   {System.out.println(yytext());if(error==null){ return new Symbol(RETORNA, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    

    /*Ciclos*/
    [pP]"ara" {System.out.println(yytext());if(error==null){ return new Symbol(FOR, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [mM]"ientras" {System.out.println(yytext());if(error==null){ return new Symbol(WHILE, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [hH]"acer" {System.out.println(yytext());if(error==null){ return new Symbol(HACER, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    [cC]"ontinuar"  {System.out.println(yytext());if(error==null){ return new Symbol(CONTINUE,yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    {Simbolo}  {System.out.println("SIMBOLO "+yytext());
        if(error==null){ 
            return new Symbol(SIMBOLO, yyline+1, yycolumn+1, yytext());
        }else{
            error.setLexema(yytext());
        }
        }

    /*Operadores relacionales*/
    "=" {System.out.println(yytext());if(error==null){ return new Symbol(EQUAL, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    "==" {System.out.println(yytext());if(error==null){ return new Symbol(EQUALIZATION, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    "!="    {System.out.println(yytext());if(error==null){ return new Symbol(DIFFERENTIATION, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    ">="    {System.out.println(yytext());if(error==null){ return new Symbol(GREATER_THAN, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    "<="    {System.out.println(yytext());if(error==null){ return new Symbol(LESSER_THAN, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    "!!"    {System.out.println(yytext());if(error==null){ return new Symbol(NULL_, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    ">" {System.out.println(yytext());if(error==null){ return new Symbol(GREATER, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    "<" {System.out.println(yytext());if(error==null){ return new Symbol(LESSER, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}

    /*Operadores de Incremento/Decremento */
    "+=" {System.out.println(yytext());if(error==null){ return new Symbol(EQUAL_MORE,yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    "++" {System.out.println(yytext());if(error==null){ return new Symbol(INCREASE, yyline+1,yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    "--" {System.out.println(yytext());if(error==null){ return new Symbol(DECREASE, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}


    /*Operadores aritmeticos*/
    "^" {System.out.println(yytext());if(error==null){ return new Symbol(POW, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    "+" {System.out.println(yytext());if(error==null){ return new Symbol(ADD, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    "-" {System.out.println(yytext());if(error==null){ return new Symbol(MINUS, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    "*" {System.out.println(yytext());if(error==null){ return new Symbol(MULTIPLY, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    "/" {System.out.println(yytext());if(error==null){ return new Symbol(DIV, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    "%" {System.out.println(yytext());if(error==null){ return new Symbol(MODULE, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}

    /*Operadores logicos*/
    "!&&" {System.out.println(yytext());if(error==null){ return new Symbol(NAND, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    "&&" {System.out.println(yytext());if(error==null){ return new Symbol(AND, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    "!||" {System.out.println(yytext());if(error==null){ return new Symbol(NOR, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    "||" {System.out.println(yytext());if(error==null){ return new Symbol(OR, yyline+1, yycolumn+1,yytext());}else{
            error.setLexema(yytext());
        }}
    "&!" {System.out.println(yytext());if(error==null){ return new Symbol(XOR, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    "!" {System.out.println(yytext());if(error==null){ return new Symbol(NOT, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    
    /*Especiales*/
    "\t"    {System.out.println("TAB"); 
            if(error!=null){list.add(error); error=null;}
            return new Symbol(TAB, yyline+1, yycolumn+1, yytext());}
    "\n"    {System.out.println("SALTO");
        if(error!=null){list.add(error); error=null;}
        return new Symbol(SPACE, yyline+1, yycolumn+1, yytext());}

    /*Signos*/
     "[" {System.out.println(yytext());
            if(error==null){ 
                return new Symbol(OPEN_BRACKET, yyline+1, yycolumn+1, yytext());
            }else{
                error.setLexema(yytext());
            }
        }
    "]" {System.out.println(yytext());if(error==null){ return new Symbol (CLOSE_BRACKET, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    "{" {System.out.println(yytext());if(error==null){ return new Symbol (OPEN_CURLY, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    "}" {System.out.println(yytext());if(error==null){ return new Symbol (CLOSE_CURLY, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    "(" {System.out.println(yytext());if(error==null){ return new Symbol(OPEN_PARENTHESIS, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    ")" {System.out.println(yytext());if(error==null){ return new Symbol(CLOSE_PARENTHESIS, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    ";" {System.out.println(yytext());if(error==null){ return new Symbol(COLON, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
    "," {System.out.println(yytext());if(error==null){ return new Symbol(COMA, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}



    {WhiteSpace}    {if(error!=null){list.add(error); error=null;}}
    
    /*Comentarios*/
    {Commentary}  {/*empty*/}
    {BlockCommentary}  {/*empty*/}    
}

/*Errores*/
[^]   {
    if(error==null){
        error = new LexicalError(yyline+1, yycolumn+1);
        error.setDescription("Lexema no reconocido");
    }
    error.setLexema(yytext());
}