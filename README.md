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

Tecnologias usadas:
Spring Boot - 2.0.4.RELEASE
JDK - 1.8 or later
Spring Framework - 5.0.8 RELEASE
Hibernate - 5.2.17.Final
Apache maven -3.6.1
Spring Data JPA - 2.0.10 RELEASE
IDE - Eclipse or Spring Tool Suite 4 (STS)
MYSQL - 5.1.47
Jersey - 2.26

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


Ver metodos y structuras de request en este link:
http://localhost:8080/sysone/swagger.json


ENDPOINTS

VENTAS

Crear una venta nueva:
metodo POST:
http://localhost:8080/sysone/api/v1/sales



{
    "car": {
            "id": 1
        },
    "options": ["AA","AB","LL"]
}


Respuesta:


{
    "id": 50,
    "car": {
        "id": 1
    },
    "options": [
        "AA",
        "AB",
        "LL"
    ],
    "totalPrice": 309000.0
}


Listar Ventas:
metodo GET
http://localhost:8080/sysone/api/v1/sales/
Respuesta:



[
    {
        "id": 42,
        "car": {
            "id": 2,
            "name": "Familiar",
            "baseCost": 245000.0
        },
        "options": [
            "AA",
            "AB",
            "CC"
        ],
        "totalPrice": 250000.0
    },
    {
        "id": 43,
        "car": {
            "id": 1,
            "name": "Coupé",
            "baseCost": 270000.0
        },
        "options": [
            "CC",
            "CR",
            "ERE"
        ],
        "totalPrice": 310000.0
    }
]


Buscar por ID:
metodo GET
http://localhost:8080/sysone/api/v1/sales/42


Respuestas:


{
    "headers": {},
    "body": {
        "id": 42,
        "car": {
            "id": 2,
            "name": "Familiar",
            "baseCost": 245000.0
        },
        "options": [
            "AA",
            "AB",
            "CC"
        ],
        "totalPrice": 250000.0
    },
    "statusCode": "OK",
    "statusCodeValue": 200
}


EDITAR:
metodo PUT
http://localhost:8080/sysone/api/v1/sales/49


{
    "id": 42,
    "car": {
            "id": 2
        },
    "options": ["AA","AB","LL"]
}


Respuestas:


{
    "headers": {},
    "body": {
        "id": 49,
        "car": {
            "id": 2,
            "name": "Familiar",
            "baseCost": 245000.0
        },
        "options": [
            "AA",
            "AB",
            "LL"
        ],
        "totalPrice": 284000.0
    },
    "statusCode": "OK",
    "statusCodeValue": 200
}


Eliminar:
Metodo DELETE
http://localhost:8080/sysone/api/v1/sales/42


Respuesta:
{
    "deleted": true
}

///////////////////////////////////////////////////////////////////////////////////////

AUTOMOVILES

Crear:
Metodo POST
http://localhost:8080/sysone/api/v1/cars


{
    "name": "Sedán",
    "baseCost": 230000.0
}


Respuesta:


{
    "id": 3,
    "name": "Sedán",
    "baseCost": 230000.0
}



Listar todos:
Metodo GET
http://localhost:8080/sysone/api/v1/cars


[
    {
        "id": 1,
        "name": "Coupe",
        "baseCost": 270000.0
    },
    {
        "id": 2,
        "name": "Familiar",
        "baseCost": 245000.0
    },
    {
        "id": 3,
        "name": "Sedán",
        "baseCost": 230000.0
    }
]


Buscar por ID:
Metodo GET
http://localhost:8080/sysone/api/v1/cars/1


Respuesta:
{
    "headers": {},
    "body": {
        "id": 1,
        "name": "baseCost",
        "baseCost": 0.0
    },
    "statusCode": "OK",
    "statusCodeValue": 200
}


Eliminar:
Metodo DELETE
http://localhost:8080/sysone/api/v1/sales/42


Respuesta:
{
    "deleted": true
}

///////////////////////////////////////////////////////////////////////////////////////

Opciones/Piezas Adicionales

Crear
Metodo POST
http://localhost:8080/sysone/api/v1/options


{
	"description": "Sistema de Frenos ABS",
	"priceOption": 14000.0,
	"shortCut": "ABS"
}


Respuesta:


{
    "id": 11,
    "description": "Sistema de Frenos ABS",
    "priceOption": 14000.0,
    "shortCut": "ABS"
}


Listar todos:
Metodo GET
http://localhost:8080/sysone/api/v1/options/
Respuesta:


[
    {
        "id": 9,
        "description": "Techo Corredizo",
        "priceOption": 12000.0,
        "shortCut": "TC"
    },
    {
        "id": 10,
        "description": "Aire Acondicionado",
        "priceOption": 20000.0,
        "shortCut": "AA"
    },
    {
        "id": 11,
        "description": "Sistema de Frenos ABS",
        "priceOption": 14000.0,
        "shortCut": "ABS"
    },
    {
        "id": 12,
        "description": "AirBag",
        "priceOption": 7000.0,
        "shortCut": "AB"
    },
    {
        "id": 13,
        "description": "Llantas de aleación",
        "priceOption": 12000.0,
        "shortCut": "LL"
    }
]



Buscar por ID
Metodo GET
http://localhost:8080/sysone/api/v1/options/9
Respuesta:


{
    "headers": {},
    "body": {
        "id": 9,
        "description": "Techo Corredizo",
        "priceOption": 12000.0,
        "shortCut": "TC"
    },
    "statusCode": "OK",
    "statusCodeValue": 200
}




Modificar
Metodo PUT
http://localhost:8080/sysone/api/v1/options/11


{
	"description": "Sistema de Frenos ABS",
    "priceOption": 14000.0,
    "shortCut": "ABS"
}


Respuesta:


{
    "headers": {},
    "body": {
        "id": 11,
        "description": "Sistema de Frenos ABS",
        "priceOption": 14000.0,
        "shortCut": "ABS"
    },
    "statusCode": "OK",
    "statusCodeValue": 200
}


Eliminar
Metodo DELETE
http://localhost:8080/sysone/api/v1/sales/13
Respuesta:


{
    "deleted": true
}
