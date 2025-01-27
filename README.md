`DBZAPI` es una API Rest de código abierto utilizada para el registro de personajes de Dragon Ball Y manejo de sesiones. El proyecto ha sido desarrollado a través de una aplicación [Springboot 3.3.8-SNAPSHOT](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot/1.5.9.RELEASE). Adicionalmente, `DBZAPI` almacena los personajes en una base de datos PostgreSQL lo cual le permite realizar posteriores consultas.

Cotenidos
=================
- [Software requerido](#software-requerido)
- [Pasos previos](#pasos-previos)
- [Instalación](#instalación)
	- [Despliegue estándar](#despliegue-estándar)
- [Seguridad](#seguridad)
	- [Obtención de tokens JWT](#obtención-de-tokens-jwt)
- [Documentación](#documentación)
- [Firebase - Cloud Storage](#firebase---cloud-storage)
	- [Pasos para configurar Firebase](#pasos-para-configurar-firebase)
- [Autor](#autor)

## Software requerido
- JDK 17
- Apache Maven 3.8.5
- PostgreSQL 12.12

## Pasos previos
- [Instalar y configurar Maven](https://www.mkyong.com/maven/how-to-install-maven-in-windows/)

## Instalación
`DBZAPI` puede ser ejecutado como una aplicación Spring-Boot la cual requiere la instalación previa de PostgreSQL.

### Despliegue estándar

1. Abrir una consola o shell y crear la base de datos.
```bash
$ psql -U postgres
# CREATE DATABASE "dbzapi";
# \q
```
2. Crear la estructura de tablas ejecutando el script **veronica.sql**.
```bash
$ cd dbz_api/src/sql
$ psql -U postgres dbzapi < dbzapi.sql
```

3. Instalar `DBZAPI`.
```bash
$ cd dbz_api
$ mvn clean install
```

## Seguridad
Al instalar la base de datos de `DBZAPI`, automáticamente se crearán dos usuarios con sus respectivas contraseñas y roles.

| Usuario | Contraseña |     Rol    |
|:-------:|:----------:|:----------:|
| laqm_14@hotmail.com   | admin123   | ROLE_ADMIN |
| lmoran@gmail.com   | admin123   | ROLE_USER  |

### Obtención de tokens JWT
Para generar un token para el usuario admin, por ejemplo, podermos ejecutar el siguiente comando curl:
```bash
curl -X POST http://localhost:8084/api/v1/authenticate \
-H "Content-Type: application/json" \
-d '{
    "username": "laqm_14@hotmail.com",
    "password": "admin123"
}'
```

Con el token generado podemos hacer uso de cualquier de los endpoints que ofrece `DBZAPI` a través de su API Rest. Para esto, debemos utilizar el token generado a través de una llamada con autenticación Bearer.

## Documentación
### Swagger
Para acceder, debemos utilizar los usuarios indicados en la sección anterior.

http://localhost:8084/dbzapp/swagger-ui/swagger-ui/index.html

### Postman
`DBZAPI` también pone a disposición de los usuarios una colección de llamadas y ejemplos que se encuentra en la ruta **/src/postman/dbzapi**.

## Firebase - Cloud Storage

Antes de iniciar con esta configuración es necesario que vayamos a este tutorial.

[Image Uploading with Spring Boot & Firebase Cloud Storage](https://medium.com/@poojithairosha/image-uploading-with-spring-boot-firebase-cloud-storage-e5ef2fbf942d)

Una ves revisado este contenido, comanezaremos a explicar la integración con firebase.

En la carpeta `sql`, encontrarás un archivo JSON que contiene las credenciales necesarias para establecer la conexión con el SDK de Firebase. Este archivo incluye la configuración requerida para autenticar y operar con los servicios de Firebase.

#### Pasos para configurar Firebase:

1. Navega a la carpeta `sql` dentro del proyecto.

2. Asegúrate de reemplazar el archivo JSON con tus propias credenciales de Firebase. Estas credenciales son únicas para cada proyecto de Firebase y deben configurarse correctamente.

3. Una vez configurado, mueve el archivo JSON a la carpeta `resources` de tu proyecto para que sea accesible por la aplicación.

4. Verifica que el archivo JSON esté correctamente referenciado en tu código o entorno para que el SDK de Firebase pueda utilizarlo.

## Autor
| [![](https://avatars.githubusercontent.com/u/34175575?v=4)](https://avatars.githubusercontent.com/u/34175575?v=4) |
|-|
| [@Andykingche](https://github.com/AndyKingche) |
