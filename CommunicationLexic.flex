
%%
%class PhytonLexic
%cup
%unicode
%line
%column
%public

%{

%}

/* Regular Expression */

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator}| [ \t\f]

/*Reserved Words*/
Solicitud = [sS][o][l][i][c][i][t][u][d]
Tipo = [tT][i][p][o]
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

/*Datos*/
String = [\"][^\"]*[\"]
Decimal = (([1-9][0-9]*|0?)[.])[0-9]+
Entero = [0-9]+

%%

<YYINITIAL> {

    /*Datos*/
    {String}    {return new Symbol(STRING, yyline+1, yycolumn+1, yytext());}
    {Decimal}  {return new Symbol(DECIMAL, yyline+1, yycolumn+1, yytext());}
    {Entero}  {return new Symbol(ENTERO, yyline+1, yycolumn+1, yytext());}

    /*Reserved Words*/
    {Solicitud} {return new Symbol(SOLICITUD, yyline+1, yycolumn+1, yytext());}
    {Tipo}  {return new Symbol(TIPO, yyline+1, yycolumn+1, yytext());}
    {Nombre}    {return new Symbol(NOMBRE, yyline+1, yycolumn+1, yytext());}
    {Listas}  {return new Symbol(LISTAS, yyline+1, yycolumn+1, yytext());}
    {Lista}  {return new Symbol(LISTA, yyline+1, yycolumn+1, yytext());}
    {Pistas}    {return new Symbol(PISTAS, yyline+1, yycolumn+1, yytext());}
    {Aleatoria}  {return new Symbol(ALEATORIA, yyline+1, yycolumn+1, yytext());}
    {Duracion}  {return new Symbol(DURACION, yyline+1, yycolumn+1, yytext());}
    {Canal} {return new Symbol(CANAL, yyline+1, yycolumn+1, yytext());}
    {Numero} {return new Symbol(NUMERO, yyline+1, yycolumn+1, yytext());}
    {Nota}  {return new Symbol(NOTA, yyline+1, yycolumn+1, yytext());}
    {Frecuencia}    {return new Symbol(FRECUENCIA, yyline+1, yycolumn+1, yytext());}
    {Datos} {return new Symbol(DATOS, yyline+1, yycolumn+1, yytext());}
    {Octava}    {return new Symbol(OCTAVA, yyline+1, yycolumn+1, yytext());}

    /*Simbolos*/
    "<" {return new Symbol(LESSER, yyline+1, yycolumn+1, yytext());}
    ">" {return new Symbol(GREATER, yyline+1, yycolum+1, yytext());}
    "/" {return new Symbol(DIV, yyline+1, yycolumn+1, yytext());}
    "=" {return new Symbol(EQUAL, yyline+1, yycolumn+1, yytext());}

    {WhiteSpace}    {/*empty*/}
}

[^ <>/=\n\r\t\s]+ {/*ignore or something lexical error handling*/}