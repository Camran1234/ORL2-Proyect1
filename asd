cd /home/camran1234/NetBeansProjects/ORL2-Proyecto1; JAVA_HOME=/usr/lib/jvm/default /usr/lib/netbeans/java/maven/bin/mvn -Dexec.vmArgs= "-Dexec.args=${exec.vmArgs} -classpath %classpath ${exec.mainClass} ${exec.appArgs}" -Dexec.appArgs= -Dexec.mainClass=valiente.orl2.proyecto1.PhytonFrame -Dexec.executable=/usr/lib/jvm/default/bin/java org.codehaus.mojo:exec-maven-plugin:3.0.0:exec
Running NetBeans Compile On Save execution. Phase execution is skipped and output directories of dependency projects (with Compile on Save turned on) will be used instead of their jar artifacts.
Scanning for projects...

----------------------< valiente:ORL2-Proyecto1 >-----------------------
Building ORL2-Proyecto1 1.0-SNAPSHOT
--------------------------------[ jar ]---------------------------------

--- exec-maven-plugin:3.0.0:exec (default-cli) @ ORL2-Proyecto1 ---
Pista
SIMBOLO RitmoFondo
SALTO
TAB
keep
var
entero
SIMBOLO contador
SALTO
TAB
var
boolean
SIMBOLO bandera
=
verdadero
SALTO
TAB
var
cadena
SIMBOLO texto
=
"hasta aca todo bien"
+
" "
+
"!!"
+
"#n"
SALTO
TAB
keep
var
doble
SIMBOLO uno
,
SIMBOLO dos
,
SIMBOLO tres
,
SIMBOLO cuatro
=
DECIMAL 50.4
*
NUMERO 6
SALTO
TAB
var
entero
arreglo
SIMBOLO arr1
[
(
SIMBOLO a
+
SIMBOLO b
+
(
SIMBOLO c
+
(
SIMBOLO d
+
NUMERO 1
)
)
)
^
NUMERO 3
]
SALTO
TAB
var
cadena
SIMBOLO cad1
,
SIMBOLO cad2
,
SIMBOLO cad3
=
SIMBOLO texto
+
"aca empieza otra linea :D"
SALTO
TAB
SALTO
TAB
SALTO
TAB
entero
SIMBOLO primer_ritmo
(
entero
SIMBOLO veces
)
SALTO
TAB
TAB
SALTO
TAB
TAB
var
entero
SIMBOLO tiempo
SALTO
TAB
TAB
mientras
(
SIMBOLO permitido
==
verdadero
)
SALTO
TAB
TAB
TAB
si
(
SIMBOLO bandera
==
verdadero
)
SALTO
TAB
TAB
TAB
TAB
esperar
(
NUMERO 200
,
NUMERO 1
)
SALTO
TAB
TAB
TAB
TAB
SIMBOLO tiempo
+=
NUMERO 200
SALTO
TAB
TAB
TAB
sino
SALTO
TAB
TAB
TAB
TAB
Reproducir
(
Do
,
NUMERO 5
,
(
NUMERO 20
*
NUMERO 10
)
*
NUMERO 2
,
NUMERO 1
)
SALTO
TAB
TAB
TAB
TAB
SIMBOLO tiempo
+=
(
NUMERO 20
*
NUMERO 10
)
*
NUMERO 2
SALTO
TAB
TAB
TAB
SIMBOLO bandera
=
!
(
SIMBOLO bandera
)
SALTO
TAB
TAB
TAB
SIMBOLO contador
++
SALTO
TAB
TAB
TAB
si
(
SIMBOLO contador
>=
SIMBOLO veces
)
SALTO
TAB
TAB
TAB
TAB
SIMBOLO permitido
=
falso
SALTO
TAB
TAB
TAB
TAB
SIMBOLO contador
--
SALTO
TAB
TAB
retorna
(
SIMBOLO tiempo
+
NUMERO 1
)
SALTO
TAB
TAB
SALTO
TAB
keep
boolean
SIMBOLO asignaciones
(
boolean
SIMBOLO parametro1
)
SALTO
TAB
TAB
var
boolean
SIMBOLO var0
SALTO
TAB
TAB
var
boolean
SIMBOLO var1
=
(
NUMERO 5
/
(
NUMERO 5
+
NUMERO 5
)
)
<
(
NUMERO 8
*
NUMERO 8
)
%
NUMERO 3
SALTO
TAB
TAB
hacer
SALTO
TAB
TAB
TAB
SIMBOLO var0
=
SIMBOLO var1
!=
(
SIMBOLO var1
==
verdadero
)
SALTO
TAB
TAB
TAB
SIMBOLO bandera
=
SIMBOLO var0
||
SIMBOLO var1
SALTO
TAB
TAB
TAB
SIMBOLO bandera
=
!
SIMBOLO bandera
SALTO
TAB
TAB
TAB
si
(
!!
SIMBOLO contador
)
SALTO
TAB
TAB
TAB
TAB
mensaje
(
"la varialbe contador es nula"
)
SALTO
TAB
TAB
TAB
sino
SALTO
TAB
TAB
TAB
TAB
mensaje
(
SIMBOLO texto
)
SALTO
TAB
TAB
mientras
(
!!
SIMBOLO contador
)
SALTO
TAB
TAB
retorna
SIMBOLO bandera
SALTO
TAB
TAB
SALTO
TAB
keep
principal
(
)
SALTO
TAB
TAB
SALTO
TAB
TAB
SIMBOLO contador
=
NUMERO 0
SALTO
TAB
TAB
var
entero
SIMBOLO lleva
=
SIMBOLO primer_ritmo
(
NUMERO 15
)
SALTO
TAB
TAB
SIMBOLO asignaciones
(
true
&&
(
SIMBOLO lleva
>=
NUMERO 200000
)
)
SALTO
TAB
TAB
SIMBOLO matrices
(
NUMERO 3
,
NUMERO 1
,
NUMERO 2
,
NUMERO 3
,
NUMERO 4
)
SALTO
TAB
TAB
SIMBOLO ciclos
(
)
SALTO
TAB
TAB
SALTO
TAB
keep
entero
SIMBOLO matrices
(
entero
SIMBOLO multiplicador
,
entero
SIMBOLO a
,
entero
SIMBOLO b
,
entero
SIMBOLO c
,
entero
SIMBOLO d
)
SALTO
TAB
TAB
Keep
var
entero
arreglo
SIMBOLO arr2
,
SIMBOLO arr3
,
SIMBOLO arr4
[
SIMBOLO d
^
SIMBOLO multiplicador
]
[
SIMBOLO a
*
SIMBOLO b
+
SIMBOLO c
]
SALTO
TAB
TAB
SALTO
TAB
TAB
SIMBOLO arr1
=
{
NUMERO 5
,
NUMERO 10
}
SALTO
TAB
TAB
var
entero
SIMBOLO reg
=
SIMBOLO arr3
[
NUMERO 1
]
[
NUMERO 2
]
+
SIMBOLO arr1
[
NUMERO 1
]
SALTO
TAB
TAB
retorna
SIMBOLO reg
SALTO
TAB
SIMBOLO ciclos
(
)
SALTO
TAB
TAB
para
(
var
entero
SIMBOLO a
=
NUMERO 10
;
SIMBOLO a
>
NUMERO 0
;
SIMBOLO a
--
)
SALTO
TAB
TAB
TAB
mensaje
(
"el valor de a es#n"
)
SALTO
TAB
TAB
TAB
mensaje
(
SIMBOLO a
)
SALTO
TAB
TAB
TAB
si
(
SIMBOLO a
==
NUMERO 5
)
SALTO
TAB
TAB
TAB
TAB
continuar
SALTO
TAB
TAB
TAB
mientras
(
SIMBOLO primer_ritmo
(
SIMBOLO a
)
<
NUMERO 1000
)
SALTO
TAB
TAB
TAB
TAB
SIMBOLO a
++
SALTO
TAB
TAB
TAB
TAB
Reproducir
(
Re
,
NUMERO 3
,
NUMERO 1000
,
NUMERO 9
)
SALTO
TAB
TAB
TAB
TAB
esperar
(
NUMERO 500
,
NUMERO 9
)
SALTO
TAB
TAB
TAB
TAB
Reproducir
(
Re
,
NUMERO 3
,
NUMERO 1000
,
NUMERO 9
)
SALTO
TAB
TAB
TAB
TAB
esperar
(
NUMERO 500
,
NUMERO 9
)
SALTO
TAB
TAB
TAB
TAB
SALTO
TAB
keep
entero
Ordenar
(
)
SALTO
TAB
TAB
var
entero
SIMBOLO rev
=
NUMERO 0
SALTO
TAB
Error sintactico Se esperaba "var" , tipo: Sintaxis incorrecta: Ordenar, linea: 72, column: 14	
Excpected Tokens: error, ARREGLO, SIMBOLO, 
TAB
Ordenar
(
SIMBOLO arr1
,
descendente
)
SALTO
TAB
TAB
SIMBOLO rev
=
Longitud
(
SIMBOLO arr1
)
SALTO
TAB
TAB
retorna
SIMBOLO rev
SALTO
SALTO
SALTO
SALTO
SALTO
Empezando
Lexicos
