# sysone-admision
Proyecto desarrollado como parte de recruitmen journey de la empresas Sysone.

Enunciado


Una fábrica de automóviles produce uno de sus modelos en tres variantes, llamadas sedán, coupé y familiar. Cada una tiene un precio de venta básico sin opcionales. A su vez, a cada variante se le pueden agregar opciones como techo corredizo, aire acondicionado, sistema de frenos ABS, airbag y llantas de aleación. Cada uno de estos opcionales tiene un precio que suma al básico. En este caso, cada auto vendrá caracterizado por su variante y podrá tener ninguno, uno o más opcionales.

Asumiendo los siguientes costos:
    Básico sedán          230.000
    Básico familiar        245.000
    Básico coupé          270.000
    Techo corredizo (TC)        12.000
    Aire acondicionado (AA)  20.000
    Sistemas de frenos ABS (ABS)    14.000
    Airbag (AB)   7.000
    Llantas de aleación (LL)   12.000

 
1)Diseñar una solución que permita calcular el costo final de un automóvil.
2)Desarrollar una API Rest CRUD que me permita crear, modificar, listar y eliminar objetos del tipo “Automóvil”.

Ej:

{
  “id”: 1,
  “nombre”: “Sedán”,
  “opcionales”: [“TC”, “AA”, “LL”]
}
Las operaciones de alta/consulta deberán almacenar/mostrar el costo del mismo.

 
Se evaluará tecnologías para desarrollar dicha API Rest (Spring MVC, CXF, Jersey, etc.), su persistencia (JDBC, Spring JDBC, Hibernate, etc.) y BD de datos (MariaDB, MySQL, Oracle, MongoDB, etc.) seleccionada para la resolución del ejercicio, por otro lado se evaluarán metodologías y herramientas de deployd y  formas de exposición  ( es un plus publicar la api en la nube).




TECNOLOGIAS USADAS

Patrones de diseño.

spring boot starter security  
USAR BASIC AUTH
USUARIO: admin
CONTRASENIA: admin

Spring Boot - 2.0.4.RELEASE

JDK - 1.8 or later

Spring Framework - 5.0.8 RELEASE

Hibernate - 5.2.17.Final

Apache maven -3.6.1

Spring Data JPA - 2.0.10 RELEASE

IDE - Eclipse or Spring Tool Suite 4 (STS)

MYSQL - 5.1.47

Jersey - 2.26

Javadoc 

Para el testing del API se uso Postaman Client.




SOLUCION DESARROLLADA

API Rest que permite:


Crear, modificar, listar y eliminar objetos del tipo “Automóvil”.

  1.	Un método get para listar todos los automóviles registrados.
  2.	Un método get para buscar un único automóvil.
  3.	Un método post para registrar nuevos autos.
  4.	Un método para eliminar existentes. 


Crear, modificar, listar y eliminar objetos del tipo “Opciones”.

  1.	Un método get para listar todos los opciones (partes adicionales) registrados.
  2.	Un método get para buscar una única opción.
  3.	Un método post para registrar nuevas opciones.
  4.	Un método para eliminar existentes.


Crear, modificar, listar y eliminar objetos del tipo “Ventas”.

A través de estos endpoint podemos crear, modificar, listar y eliminar ventas, es en esta instancia donde se calcula el monto planteado en el enunciado, recibe como parámetro uno de los autos registrados y las piezas disponibles que desee adicionar; este endPoint retornara un registro de venta sumando el costo base del auto y todas sus piezas, para la solución se aplicaron distintas tecnologías y el patrón estructural Decorator.

  1.	Un método get para listar todos los opciones (partes adicionales) registrados.
  2.	Un método get para buscar una única opción.
  3.	Un método post para registrar nuevas opciones.
  4.	Un método para eliminar existentes. 


ANTES DE EMPEZAR

USAR BASIC AUTH (Se configuro con spring security)

USUARIO: admin	

CONTRASENIA: admin


BASE DE DATOS


Crear base de datos 

sysone_database

en MySql

Las property de MySql tienen los siguientes valores, hacer los cambios correspondientes.
								    
spring.datasource.username = root

spring.datasource.password = 					  


Ver metodos y structuras de request en este link:
http://localhost:8080/swagger-ui/index.html

ENDPOINTS

(Primero se deben cargar los datos de autos y opciones antes de invcoar el endpoint de ventas, usar los endpoinds correspondientes, o correr el scrip de MySql)

Script SQL:

BASE.sql

Pasos para importar:

crear base de datos: sysone_database

Por consola ejecutar:

mysql -u username -p sysone_database < BASE.sql


Si esto falla, en MySql crea la base de datos: sysone_database

A continuacion carga los datos de CARS y OPTIONS usando los endpoinds correspondientes, luego podras cargar las ventas.

