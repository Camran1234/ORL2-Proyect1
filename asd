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
SALTO
Empezando
Lexicos
