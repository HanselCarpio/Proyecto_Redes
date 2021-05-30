# Proyecto_Redes
Proyecto #1 de Redes y Comunicación de Datos. Servidor y cliente TFTP, (Bryan Keihl &amp; Hansel Carpio)


Requerimentos para ejecutar correctamente este proyecto:

Java Development Kit (Java SE 16): https://www.oracle.com/java/technologies/javase-jdk16-downloads.html

Se puede utilizar cualquier ide para desarrollar en Java y en este caso, se desarrolló y ejecutó dicho lenguaje en

Netbeans 12.4: https://www.apache.org/dyn/closer.cgi/netbeans/netbeans/12.4/Apache-NetBeans-12.4-bin-windows-x64.exe

De igual forma, tambien se puede utilizar 

Visual Studio Code v1.56: https://code.visualstudio.com/Download


Teniendo esto previemente instalado en la pc, procederemos a descargar la version más reciente del proyecto, para ello es necesario dar click en la fecha que esta ubicada en el boton de color verde "CODE" y luego de que se desplegue el menú, darle click a al opción "DOWNLOAD ZIP"

![Captura de pantalla (74)](https://user-images.githubusercontent.com/38017780/120114545-dc1cb700-c13c-11eb-9758-920782f3e380.png)

Luego de descargar correctamente el proyecto, lo colocamos en la ubicacion de prefecenecia y se precede a descomprimir el .zip y se obtendran los siguientes archivos

![Captura de pantalla (77)](https://user-images.githubusercontent.com/38017780/120114737-cc51a280-c13d-11eb-8a57-b440a34da834.png)

Donde en la carpeta llamada "Proyecto Redes (B63727_B40425)" se encontrarán los archivos correspondientes al proyecto java

![Captura de pantalla (78)](https://user-images.githubusercontent.com/38017780/120114807-16d31f00-c13e-11eb-875c-1776d4e3029e.png)

Luego de esto, abriremos Apache Netbeans y daremos en la siguiente opcion para poder cargar los proyectos

![Captura de pantalla (91)](https://user-images.githubusercontent.com/38017780/120115725-47b55300-c142-11eb-9bfe-9535477f6f57.png)

Luego, buscaremos la ubicacion donde tenemos nuestro proyecto correspondiente

![Captura de pantalla (80)](https://user-images.githubusercontent.com/38017780/120115831-daee8880-c142-11eb-9b6b-ed1cbcd62c5b.png)

El siguiente paso a seguir es el de seleccionar uno de esos dos proyectos, en este caso empezaremos con Client_TFTP, donde al abrirlo se desplegará una ventana emergente con una advertencia sobre librerias faltantes

![Captura de pantalla (82)](https://user-images.githubusercontent.com/38017780/120116001-3fa9e300-c143-11eb-8151-a6895929fcd8.png)

En este punto debemos seleccionar la opción de solucionar problemas (Resolve Problems), donde nos aparecera el sguiente cuadro

![Captura de pantalla (83)](https://user-images.githubusercontent.com/38017780/120116027-5f410b80-c143-11eb-9b27-60e08957f331.png)

Una vez ahí seleccionamos el primer problema y le daremos a la casilla de resolver (Resolve) y se nos desplegará otra venta donde vamos a buscar  la carpeta "MySQL Jar" que esta dentro de la carpeta que descomprimimos al principio

![Captura de pantalla (85)](https://user-images.githubusercontent.com/38017780/120116177-163d8700-c144-11eb-88e0-73a4152a59fa.png)

Dentro de esa carpeta habrán 2 archivos de extención.jar que son las librerias faltantes, elegimos la opción correcta del problema a resolver y le damos a la opción de "Open"

![Captura de pantalla (86)](https://user-images.githubusercontent.com/38017780/120116222-60266d00-c144-11eb-82ef-b13ab6adca7b.png)

Una vez realizado esto, repetimos los pasos para resolver el otro problema de faltante de libreria

![Captura de pantalla (87)](https://user-images.githubusercontent.com/38017780/120116347-fce90a80-c144-11eb-88e9-b72387408eaf.png)

Donde elegimos el otro archivo .jar que nos detalla en el margen superior, la misma ventana de busqueda

![Captura de pantalla (88)](https://user-images.githubusercontent.com/38017780/120116394-2bff7c00-c145-11eb-8fda-1f67b1f03a4a.png)

Luego de esto, se verá reflejado en la ventana, con un check, que los dos problemas ya están solucionados

![Captura de pantalla (89)](https://user-images.githubusercontent.com/38017780/120116424-52bdb280-c145-11eb-8735-4b416552f64f.png)

Lueg de terminado esto, procedemos a buscar el otro proyecto, que sería el de Server_TFTP

![Captura de pantalla (80)](https://user-images.githubusercontent.com/38017780/120116474-8ef11300-c145-11eb-90ee-bbfe84334322.png)

Cuando abrimos este, se nos desplegara la misma ventena que nos indica los miemos errores de faltante de librerias

![Captura de pantalla (82)](https://user-images.githubusercontent.com/38017780/120116499-b2b45900-c145-11eb-8557-446bce50f5dd.png)

Para resolver estos, tenemos que realizar los mismos pasos que con el Client_TFTP...

Una vez realizado, procedemos a verificar que todo este corecto, donde en la parte derecha de Apache Netbeans estará el menu de los dos proyectos y su respectivo despliegue de archivos. Aqui nos iremos a la opción de "Libraries" y tiene que aparecer de la siguiente manera

![Captura de pantalla (90)](https://user-images.githubusercontent.com/38017780/120116619-340beb80-c146-11eb-8fd6-e6e7a3f0d90a.png)


Como parte del proyecto, estos archivos con extencion .jar son utilizados para la conexion desde java se necesita un Java Database Connectivity, más conocida por sus siglas JDBC, que es una API que permite la ejecución de operaciones sobre bases de datos desde el lenguaje de programación Java.


Concluido todo esto, se tiene lo necesario para ejecutar el proyecto, para saber sobre su utilizacion dirigirse a la wiki respectiva

