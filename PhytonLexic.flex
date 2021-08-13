
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
WhiteSpace     = {LineTerminator} | [ \f]
/*Commentary*/
Commentary = >>[^\n]+
BlockCommentary = <-([^-]|-[^>]|-+[^->])*-*->

/*Numbers*/
Decimal = (([1-9][0-9]*|0?)[.])[0-9]+
Number = [0-9]+

/*Reserved Words*/
Principal = [pP][r][i][n][c][i][p][a][l]
Pista = [pP][i][s][t][a]
Extiende = [eE][x][t][i][e][n][d][e]
Reproducir = [rR][e][p][r][o][d][u][c][i][r]
Esperar = [eE][s][p][e][r][a][r]
Ordenar = [oO][r][d][e][n][a][r]
Ascendente = [aA][s][c][e][n][d][e][n][t][e]
Descendente = [dD][e][s][c][e][n][d][e][n][t][e]
Pares = [pP][a][r][e][s]
Impares = [iI][m][p][a][r][e][s]
Primos = [pP][r][i][m][o][s]
Sumarizar = [sS][u][m][a][r][i][z][a][r]
Longitud = [lL][o][n][g][i][t][u][d]
Mensaje = [mM][e][n][s][a][j][e]
Principal = [pP][r][i][n][c][i][p][a][l]
Keep = [kK][e][e][p]
Var = [vV][a][r]
Entero = [eE][n][t][e][r][o]
Doble = [dD][o][b][l][e]
Boolean = [bB][o][o][l][e][a][n]
Caracter = [cC][a][r][a][c][t][e][r]
Cadena = [cC][a][d][e][n][a]
Arreglo = [aA][r][r][e][g][l][o]
Simbolo = [aA-zZ][[aA-zZ]|[0-9]|[_]|[$]]*
String = [\"][^\"]*[\"]
Char = [\'][^\']*[\']
Retorna = [rR][e][t][o][r][n][a]

/*Condiciones*/
If = [sS][i]
Else = [sS][i][n][o]
Switch = [sS][w][i][t][c][h]
Case = [cC][a][s][o]
Exit = [sS][a][l][i][r]
Default = [dD][e][f][a][u][l][t]

/*Ciclos*/
For = [fF][o][r]
While = [mM][i][e][n][t][r][a][s]
Hacer = [hH][a][c][e][r]
Continue = [cC][o][n][t][i][n][u][a][r]

/*Notas*/
Do# = [dD][o][#]
Re# = [rR][e][#]
Do = [dD][o]
Re = [rR][e]
Mi = [mM][i]
Fa = [fF][a]
Sol = [sS][o][l]
La = [lL][a]
Si = [sS][i]



%%

<YYINITIAL> {
    
    

    /*Numbers*/
    {Decimal}   {return new Symbol(DECIMAL, yyline+1, yycolumn+1, yytext());}
    {Number}    {return new Symbol(NUMBER, yyline+1, yycolumn+1, yytext());}

    /*Reserved Words*/
    {Pista} {return new Symbol(PISTA, yyline+1, yycolumn+1, yytext());}
    {Extiende}  {return new Symbol(EXTIENDE, yyline+1, yycolumn+1, yytext());}
    {Reproducir}    {return new Symbol(REPRODUCIR, yyline+1, yycolumn+1, yytext());}
    {Esperar}   {return new Symbol(ESPERAR, yyline+1, yycolumn+1, yytext());}
    {Ordenar}   {return new Symbol(ORDENAR, yyline+1, yycolumn+1, yytext());}
    {Ascendente}  {return new Symbol(ASCENDENTE, yyline+1, yycolumn+1, yytext());}
    {Descendente} {return new Symbol(DESCENDENTE, yyline+1, yycolumn+1, yytext());}
    {Pares} {return new Symbol(PARES, yyline+1, yycolumn+1, yytext());}
    {Impares}  {return new Symbol(IMPARES, yyline+1, yycolumn+1, yytext());}
    {Primos}  {return new Symbol(PRIMOS, yyline+1, yycolumn+1, yytext());}
    {Sumarizar} {return new Symbol(SUMARIZAR, yyline+1, yycolumn+1, yytext());}
    {Longitud}  {return new Symbol(LONGITUD, yyline+1, yycolumn+1, yytext());}
    {Mensaje}   {return new Symbol(MENSAJE, yyline+1, yycolumn+1, yytext());}
    {Principal} {return new Symbol(PRINCIPAL, yyline+1, yycolumn+1, yytext());}
    {Keep}  {return new Symbol(KEEP, yyline+1, yycolumn+1, yytext());}
    {Var}  {return new Symbol(VAR, yyline+1, yycolumn+1, yytext());}
    {Entero}  {return new Symbol(ENTERO, yyline+1, yycolumn+1, yytext());}
    {Doble}  {return new Symbol(DOBLE, yyline+1, yycolumn+1, yytext());}
    {Boolean}  {return new Symbol(BOOLEAN, yyline+1, yycolumn+1, yytext());}
    {Caracter}  {return new Symbol(CARACTER, yyline+1, yycolumn+1, yytext());}
    {Cadena}  {return new Symbol(CADENA, yyline+1, yycolumn+1, yytext());}
    {Arreglo}  {return new Symbol(ARREGLO, yyline+1, yycolumn+1, yytext());}
    {Simbolo}  {return new Symbol(SIMBOLO, yyline+1, yycolumn+1, yytext());}
    {String}  {return new Symbol(STRING, yyline+1, yycolumn+1, yytext());}
    {Char}  {return new Symbol(CHAR, yyline+1, yycolumn+1, yytext());}
    {Principal} {return new Symbol(PRINCIPAL, yyline+1, yycolumn+1, yytext());}
    {Retorna}   {return new Symbol(RETORNA, yyline+1, yycolumn+1, yytext());}

    /*Condiciones*/
    {If}    {return new Symbol(IF, yyline+1, yycolumn+1, yytext());}
    {Else}  {return new Symbol(ELSE, yyline+1, yycolumn+1, yytext());}
    {Switch}    {return new Symbol(SWITCH, yyline+1, yycolumn+1, yytext());}
    {Case}  {return new Symbol(CASE, yyline+1, yycolumn+1, yytext());}
    {Exit}  {return new Symbol(EXIT, yyline+1, yycolumn+1, yytext());}
    {Default}   {return new Symbol(DEFAULT, yyline+1, yycolumn+1, yytext());}

    /*Ciclos*/
    {For} {return new Symbol(FOR, yyline+1, yycolumn+1, yytext());}
    {While} {return new Symbol(WHILE, yyline+1, yycolumn+1, yytext());}
    {Hacer} {return new Symbol(HACER, yyline+1, yycolumn+1, yytext());}
    {Continue}  {return new Symbol(CONTINUE,yyline+1, yycolumn+1, yytext());}

    /*Notas*/
    {Do#} {return new Symbol(DOR, yyline+1, yycolumn+1, yytext());}
    {Re#} {return new Symbol(RER, yyline+1, yycolumn+1, yytext());}
    {Do}    {return new Symbol(DO, yyline+1, yycolumn+1, yytext());}
    {Re}    {return new Symbol(RE, yyline+1, yycolumn+1, yytext());}
    {Mi}    {return new Symbol(MI, yyline+1, yycolumn+1, yytext());}
    {Fa}    {return new Symbol(FA, yyline+1, yycolumn+1, yytext());}
    {Sol}   {return new Symbol(SOL, yyline+1, yycolumn+1, yytext());}
    {Si}    {return new Symbol(Si, yyline+1, yycolumn+1, yytext());}

    /*Operadores relacionales*/
    "=" {return new Symbol(EQUAL, yyline+1, yycolumn+1, yytext());}
    "==" {return new Symbol(EQUALIZATION, yyline+1, yycolumn+1, yytext());}
    "!="    {return new Symbol(DIFFERENTIATION, yyline+1, yycolumn+1, yytext());}
    ">="    {return new Symbol(GREATER_THAN, yyline+1, yycolumn+1, yytext());}
    "<="    {return new Symbol(LESSER_THAN, yyline+1, yycolumn+1, yytext());}
    "!!"    {return new Symbol(NULL_, yyline+1, yycolumn+1, yytext());}
    ">" {return new Symbol(GREATER, yyline+1, yycolumn+1, yytext());}
    "<" {return new Symbol(LESSER, yyline+1, yycolumn+1, yytext());}


    /*Operadores de Incremento/Decremento */
    "+=" {return new Symbol(EQUAL_MORE,yyline+1, yycolumn+1, yytext());}
    "++" {return new Symbol(INCREASE, yyline+1,yycolumn+1, yytext());}
    "--" {return new Symbol(DECREASE, yyline+1, yycolumn+1, yytext());}


    /*Operadores aritmeticos*/
    "+" {return new Symbol(ADD, yyline+1, yycolumn+1, yytext());}
    "-" {return new Symbol(MINUS, yyline+1, yycolumn+1, yytext());}
    "*" {return new Symbol(MULTIPLY, yyline+1, yycolumn+1, yytext());}
    "/" {return new Symbol(DIV, yyline+1, yycolumn+1, yytext());}
    "%" {return new Symbol(MODULE, yyline+1, yycolumn+1, yytext());}
    "^" {return new Symbol(POW, yyline+1, yycolumn+1, yytext());}


    /*Signos*/
    "[" {return new Symbol(OPEN_BRACKET, yyline+1, yycolumn+1, yytext());}
    "]" {return new Symbol (CLOSE_BRACKET, yyline+1, yycolumn+1, yytext());}
    "{" {return new Symbol (OPEN_CURLY, yyline+1, yycolumn+1, yytext());}
    "}" {return new Symbol (CLOSE_CURLY, yyline+1, yycolumn+1, yytext());}
    "(" {return new Symbol(OPEN_PARENTHESIS, yyline+1, yycolumn+1, yytext());}
    ")" {return new Symbol(CLOSE_PARENTHESIS, yyline+1, yycolumn+1, yytext());}
    ";" {return new Symbol(COLON, yyline+1, yycolumn+1, yytext());}
    "," {return new Symbol(COMA, yyline+1, yycolumn+1, yytext());}


    /*Operadores logicos*/
    "!&&" {return new Symbol(NAND, yyline+1, yycolumn+1, yytext());}
    "&&" {return new Symbol(AND, yyline+1, yycolumn+1, yytext());}
    "!||" {return new Symbol(NOR, yyline+1, yycolumn+1, yytext());}
    "||" {return new Symbol(OR, yyline+1, yycolumn+1,yytext());}
    "&!" {return new Symbol(XOR, yyline+1, yycolumn+1, yytext());}
    "!" {return new Symbol(NOT, yyline+1, yycolumn+1, yytext());}
    
    /*Especiales*/
    "\t"    {return new Symbol(TAB, yyline+1, yycolumn+1, yytext());}
    "\n"    {return new Symbol(SPACE, yyline+1, yycolumn+1, yytext());}

    {WhiteSpace}    {/*Ignore*/}

    /*Comentarios*/
    {Commentary}  {/*empty*/}
    {BlockCommentary}  {/*empty*/}
}

/*Errores*/
[^ ><#!\\[\\]\\(\\);,"&""|""\"""\'""\t""+-*/"\n\r\t\s]+   {return new Symbol(LEXIC_ERROR, yyline+1, yycolumn+1, yytext());}