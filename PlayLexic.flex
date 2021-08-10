
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
Lista = [lL][i][s][t][a]
Nombre = [nN][o][m][b][r][e]
Random = [rR][a][n][d][o][m]
Circular = [cC][i][r][c][u][l][a][r]
Pistas = [pP][i][s][t][a][s]
True = [tT][r][u][e]
False = [fF][a][l][s][e]
String = [\"][^\"]*[\"]
Simbolo = [aA-zZ][[aA-zZ]|[0-9]|[_]|[$]]*

%%

<YYINITIAL> {
     
     /*Reserved Words*/
     {Lista}   {return new Symbol(LISTA, yyline+1, yycolumn+1, yytext());}
     {Nombre}  {return new Symbol(NOMBRE, yyline+1, yycolumn+1, yytext());}
     {Random}  {return new Symbol(RANDOM, yyline+1, yycolumn+1, yytext());}
     {Circular}     {return new Symbol(CIRCULAR, yyline+1, yycolumn+1, yytext());}
     {Pistas}   {return new Symbol(PISTA, yyline+1, yycolumn+1, yytext());}
     {True}    {return new Symbol(TRUE, yyline+1, yycolumn+1, yytext());}
     {False}   {return new Symbol(FALSE, yyline+1, yycolumn+1, yytext());}
     {String}  {return new Symbol(STRING, yyline+1, yycolumn+1, yytext());}
     {Simbolo} {return new Symbol(SIMBOLO, yyline+1, yycolumn+1, yytext());}

     /*Simbolos*/
     "["  {return new Symbol(OPEN_BRACKET, yyline+1, yycolumn+1, yytext());}
     "]"  {return new Symbol(CLOSE_BRACKET, yyline+1, yycolumn+1, yytext());}
     "|"  {return new Symbol(PIPE, yyline+1, yycolumn+1, yytext());}
     "{"    {return new Symbol(OPEN_CURLY, yyline+1, yycolumn+1, yytext());}
     "}"    {return new Symbol(CLOSE_CURLY, yyline+1, yycolumn+1, yytext());}
     ":"  {return new Symbol(SEMI_COLON, yyline+1, yycolumn+1, yytext());}
     ","  {return new Symbol(COMA, yyline+1, yycolumn+1, yytext());}
     

}

[^ \n\r\s\\[\\]\\(\\)"|"]+ {/*Agregar manejo de errores*/}