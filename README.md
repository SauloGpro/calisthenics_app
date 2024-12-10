## 📜 Notarization Blockchain

Notarization Blockchain es una aplicación que permite gestionar la notarización de documentos utilizando una blockchain privada. La solución está desarrollada en Java con Spring Boot y utiliza una base de datos relacional para almacenar los datos. Este proyecto está diseñado para garantizar la trazabilidad, integridad y autenticidad de los documentos a través de una cadena de bloques.
## 🚀 Características

* Registro de usuarios: Los usuarios pueden registrarse y autenticarse en el sistema.
* Añadir documentos a la blockchain: Los documentos son añadidos como bloques dentro de la blockchain, garantizando su integridad.
* Validación de la blockchain: Comprueba que la cadena de bloques no ha sido alterada.
*  Consulta de documentos:
    * Búsqueda por hash del bloque.
    * Consulta de bloques asociados a un propietario.
    * Resumen de la blockchain: Consulta un resumen general de la cadena (número de bloques, integridad, etc.).
## 🛠 Tecnologías utilizadas 
- Backend
- Lenguaje: Java (versión 21)
- Framework principal: Spring Boot (v3.3.6)
 -Dependencias clave: 
    * Spring Boot Web 
    * Spring Data JPA 
    * Spring Security 
    * MySQL Connector 
    * JWT (Json Web Tokens) con jjwt 
    * Lombok 
    * MapStruct
- Base de datos MySQL:
    * Nombre: blockchain_db 
    * Configuración en application.properties. Copiar código: 
        
        spring.application.name=NotarizationBlockchain
        spring.datasource.url=jdbc:mysql://localhost:3308/blockchain_db?createDatabaseIfNotExists=true
        spring.datasource.username=root
        spring.datasource.password=root
        pring.jpa.hibernate.ddl-auto=update
        spring.jpa.show-sql=true
        spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
        spring.jpa.defer-datasource-initialization=true
## 📂 Estructura del proyecto 
El proyecto sigue una arquitectura típica de Spring Boot. A continuación, un desglose de las capas principales:
 
- src/main/java/com/example/NotarizationBlockchain/
├── config/                # Configuraciones de seguridad y beans
├── controller/            # Controladores RESTful y dtos
├── service/               # Lógica de negocio
│   ├── impl/              # Implementaciones de servicios
├── repository/            # Repositorios para acceso a base de datos
│   ├── entities/          # Entidades JPA
├── model/                 # Modelos de dominio
├── mapper/                # MapStruct mappers para transformar entre DTOs y entidades
 
* Carpetas clave: 
    * config/: Configuración de seguridad (JWT y Spring Security). 
    * controller/: Implementa los endpoints de la API REST. 
    * service/: Contiene la lógica de negocio (implementación de la blockchain). 
    * repository/: Maneja la persistencia utilizando JPA. 
    * model/: Contiene los modelos de dominio como Block y User. 
    * mapper/: MapStruct para convertir entre modelos de dominio, entidades y DTOs. 

## 📋 Endpoints de la API 
## 🔒 Autenticación y Usuarios 
- Método Endpoint Descripción Cuerpo 
    * POST /auth/register Registrar un nuevo usuario 
    { "username": "user1", "password": "1234", "email": "user@example.com" } 
    * POST /auth/login Iniciar sesión y obtener un JWT 
    { "username": "user1", "password": "1234" } 
## 📜 Blockchain 
* Método Endpoint Descripción Cuerpo 
    * POST /blockchain/add Añadir un bloque a la blockchain { "data": "Documento importante", "documentType": "Contrato", "owner": "user1" } 
    * GET /blockchain/validate Validar la integridad de la blockchain  
    * GET /blockchain/chain Obtener todos los bloques  
    * GET /blockchain/search/{hash} Buscar un bloque por su hash  
    * GET /blockchain/summary Obtener un resumen de la blockchain  
    * GET /blockchain/owner/{owner} Obtener bloques por propietario  
## 📦  Requisitos previos Java 
* Versión 21 o superior. 
* Maven: Para gestionar las dependencias y construir el proyecto. 
* MySQL: Una base de datos MySQL debe estar disponible y configurada. 
## ⚙️Instalación y ejecución Clonar el repositorio
* Copiar código: 
    * git clone https://github.com/tu-usuario/NotarizationBlockchain.git 
    * cd NotarizationBlockchain 
* Configurar la base de datos: 
   * Crear la base de datos en MySQL: 
* sql Copiar código: CREATE DATABASE blockchain_db; 
Asegurarse de que las credenciales de acceso estén correctamente configuradas src/main/resources/application.properties: spring.datasource.url=jdbc:mysql://localhost:3308/blockchain_db?createDatabaseIfNotExists=true spring.datasource.username=root
spring.datasource.password=root 
* Compilar y ejecutar: Compilar el proyecto: bash Copiar código mvn clean install  
* Ejecutar la aplicación: bash Copiar código mvn spring-boot:run 
## 🧪 Ejemplos de uso 

* Registro de usuario: 
    * Request: bash Copiar código POST http://localhost:8080/auth/register Content-Type: application/json
    { "username": "user1", "password": "securePassword", "email": "user1@example.com" } 
    * Response: json Copiar código { "username": "user1", "email": "user1@example.com" } 
    
* Añadir un documento a la blockchain: 
    * Request: bash Copiar código POST http://localhost:8080/blockchain/add Authorization: Bearer {JWT_TOKEN} Content-Type: application/json { "data": "Contrato de trabajo", "documentType": "Contrato", "owner": "user1" } 
    * Response: json Copiar código { "index": 1, "timestamp": 1702152978, "previousHash": "0", "hash": "1f3870be274f6c49b3e31a0c6728957f", "data": "Contrato de trabajo", "documentType": "Contrato", "owner": "user1" }
## 🛡 Seguridad Autenticación 
La API utiliza JWT para autenticar las solicitudes. Los usuarios deben autenticarse a través del endpoint /auth/login para obtener un token que luego deben incluir en el encabezado Authorization de las solicitudes protegidas. Spring Security: Protege los endpoints sensibles y maneja la autorización. 
## 🤝 Contribuciones 
Realiza un fork del repositorio. Crea una nueva rama para tu funcionalidad: bash Copiar código git checkout -b feature/nueva-funcionalidad Realiza los cambios necesarios y haz un commit: bash Copiar código git commit -m "Añadida nueva funcionalidad" Envía un pull request explicando los cambios realizados. 
## 📝  Licencia 
Este proyecto está bajo la licencia MIT. Puedes consultar el archivo LICENSE para más información.
