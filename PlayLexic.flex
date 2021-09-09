package valiente.orl2.Lista.parser;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import java_cup.runtime.*;
import valiente.orl2.phyton.error.LexicalError;
import java.util.ArrayList;
import static valiente.orl2.Lista.parser.PlaySym.*;
%%
%class PlayLexic
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

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator}| [ \t\f]

/*Reserved Words*/
Lista = [lL][i][s][t][a]
Nombre = [nN][o][m][b][r][e]
Random = [rR][a][n][d][o][m]
Circular = [cC][i][r][c][u][l][a][r]
Pistas = [pP][i][s][t][a][s]
True = [tT][r][u][e]
False = [fF][a][l][s][e]
String = [\"][^\"]*[\"]
Simbolo = ([a-zA-Z])([a-zA-Z0-9]|"_")*

%%

<YYINITIAL> {
     
     /*Simbolos*/
     "["  {System.out.println(yytext()); if(error==null){ return new Symbol(OPEN_BRACKET, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
     "]"  {System.out.println(yytext()); if(error==null){ return new Symbol(CLOSE_BRACKET, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
     "|"  {System.out.println(yytext()); if(error==null){ return new Symbol(PIPE, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
     "{"    {System.out.println(yytext()); if(error==null){ return new Symbol(OPEN_CURLY, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
     "}"    {System.out.println(yytext()); if(error==null){ return new Symbol(CLOSE_CURLY, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
     ":"  {System.out.println(yytext()); if(error==null){ return new Symbol(SEMI_COLON, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
     ","  {System.out.println(yytext()); if(error==null){ return new Symbol(COMA, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}

     /*Reserved Words*/
     {Lista}   {System.out.println(yytext()); if(error==null){ return new Symbol(LISTA, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
     {Nombre}  {System.out.println(yytext()); if(error==null){ return new Symbol(NOMBRE, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
     {Random}  {System.out.println(yytext()); if(error==null){ return new Symbol(RANDOM, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
     {Circular}     {System.out.println(yytext()); if(error==null){ return new Symbol(CIRCULAR, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
     {Pistas}   {System.out.println(yytext()); if(error==null){ return new Symbol(PISTA, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
     {True}    {System.out.println(yytext()); if(error==null){ return new Symbol(TRUE, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
     {False}   {System.out.println(yytext()); if(error==null){ return new Symbol(FALSE, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
     {String}  {System.out.println(yytext()); if(error==null){ return new Symbol(STRING, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}
     {Simbolo} {System.out.println("SIMBOLO: "+yytext()); if(error==null){ return new Symbol(SIMBOLO, yyline+1, yycolumn+1, yytext());}else{
            error.setLexema(yytext());
        }}

     
     
     {WhiteSpace} { if(error!=null){list.add(error); error=null;}}
}

[^] {
     if(error==null){
        error = new LexicalError(yyline+1, yycolumn+1);
        error.setDescription("Lexema no reconocido");
    }
    error.setLexema(yytext());
}