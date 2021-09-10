package com.example.comunicacion.parser;
import com.example.comunicacion.errors.LexicalError;
import java.util.ArrayList;
import static com.example.comunicacion.parser.CommunicationSym.*;
import java_cup.runtime.Symbol;
%%
%class CommunicationLexic
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
Solicitud = [sS][o][l][i][c][i][t][u][d]
Tipo = [tT][i][p][o]
Simbolo = ([a-zA-Z])([a-zA-Z0-9]|"_")*
Nombre = [nN][o][m][b][r][e]
Listas = [lL][i][s][t][a][s]
Lista = [lL][i][s][t][a]
Pistas = [pP][i][s][t][a][s]
Aleatoria = [aA][l][e][a][t][o][r][i][a]
Duracion = [dD][u][r][a][c][i][o][n]
Canal = [cC][a][n][a][l]
Numero = [nN][u][m][e][r][o]
Nota = [nN][o][t][a]
Frecuencia = [fF][r][e][c][u][e][n][c][i][a]
Datos = [dD][a][t][o][s]
Octava = [oO][c][t][a][v][a]
Pista = [pP]"ista"
Do = [dD]"o"
Do_ = [dD]"o#"
Re = [rR]"e"
Re_ = [rR]"e#"
Mi = [mM]"i"
Mi_ = [mM]"i#"
Fa = [fF]"a"
Fa_ = [fF]"a#"
Sol = [sS]"ol"
Sol_ = [sS]"ol#"
La = [lL]"a"
La_ = [lL]"a#"
Si = [sS]"i"
Si_ = [sS]"i#"
Circular = [cC]"ircular"
Note = {Do}|{Do_}|{Re}|{Re_}|{Mi}|{Mi_}|{Fa}|{Fa_}|{Sol}|{Sol_}|{La}|{La_}|{Si}|{Si_}

/*Datos*/
String = [\"][^\"]*[\"]
Decimal = (([1-9][0-9]*|0?)[.])[0-9]{1,5}
Entero = [0-9]+

%%

<YYINITIAL> {

    /*Datos*/
    {String}    {System.out.println(yytext());
                if(error==null){
                    return new Symbol(STRING, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}
    {Decimal}  {System.out.println(yytext());
                if(error==null){
                    return new Symbol(DECIMAL, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}
    {Entero}  {System.out.println(yytext());
                if(error==null){
                    return new Symbol(NUMBER, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}

    /*Reserved Words*/
    {Pista} {System.out.println(yytext());
                if(error==null){
                    return new Symbol(PISTA, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}
    {Circular} {System.out.println(yytext());
                if(error==null){
                    return new Symbol(CIRCULAR, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}
    {Solicitud} {System.out.println(yytext());
                if(error==null){
                    return new Symbol(SOLICITUD, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}
    {Tipo}  {System.out.println(yytext());
                if(error==null){
                    return new Symbol(TIPO, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}
    {Nombre}    {System.out.println(yytext());
                if(error==null){
                    return new Symbol(NOMBRE, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}
    {Listas}  {System.out.println(yytext());
                if(error==null){
                    return new Symbol(LISTAS, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}
    {Lista}  {System.out.println(yytext());
                if(error==null){
                    return new Symbol(LISTA, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}
    {Pistas}    {System.out.println(yytext());
                if(error==null){
                    return new Symbol(PISTAS, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}
    {Aleatoria}  {System.out.println(yytext());
                if(error==null){
                    return new Symbol(ALEATORIA, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}
    {Duracion}  {System.out.println(yytext());
                if(error==null){
                    return new Symbol(DURACION, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}
    {Canal} {System.out.println(yytext());
                if(error==null){
                    return new Symbol(CANAL, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}
    {Numero} {System.out.println(yytext());
                if(error==null){
                    return new Symbol(NUMERO, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}
    {Note}  {System.out.println(yytext());
                if(error==null){
                    return new Symbol(NOTE, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}
    {Nota}  {System.out.println(yytext());
                if(error==null){
                    return new Symbol(NOTA, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}
    {Frecuencia}    {System.out.println(yytext());
                if(error==null){
                    return new Symbol(FRECUENCIA, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}
    {Datos} {System.out.println(yytext());
                if(error==null){
                    return new Symbol(DATOS, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}
    {Octava}    {System.out.println(yytext());
                if(error==null){
                    return new Symbol(OCTAVA, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}
    {Simbolo}   {System.out.println(yytext());
                if(error==null){
                    return new Symbol(SIMBOLO, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}


    /*Simbolos*/
    "<" {System.out.println(yytext());
                if(error==null){
                    return new Symbol(LESS, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}
    ">" {System.out.println(yytext());
                if(error==null){
                    return new Symbol(GREATER, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}
    "/" {System.out.println(yytext());
                if(error==null){
                    return new Symbol(DIV, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}
    "=" {System.out.println(yytext());
                if(error==null){
                    return new Symbol(EQUAL, yyline+1, yycolumn+1, yytext()); }else{
            error.setLexema(yytext());
        }}

    {WhiteSpace}    {/*empty*/}
}

[^] {
    if(error==null){
        error = new LexicalError(yyline+1, yycolumn+1);
        error.setDescription("Lexema no reconocido");
    }
    error.setLexema(yytext());
}