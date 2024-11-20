# Módulo de Carga Horaria PSA

## Descripción

Este módulo es parte del **Sistema de Gestión de Proyectos de PSA**, específicamente para gestionar la carga horaria de los empleados en los proyectos.

## Requisitos

Para poder levantar la aplicación, necesitarás tener instalados los siguientes componentes:

- **Java 23** o superior.
- **Maven** (para la gestión de dependencias y construcción del proyecto).
- **Docker** (para ejecutar PostgreSQL con Docker Compose).

## Dependencias principales

Este proyecto incluye las siguientes dependencias relevantes:

- **Spring Boot**:
  - `spring-boot-starter-web`: Para crear una API RESTful.
  - `spring-boot-starter-data-jpa`: Para la integración con la base de datos usando JPA (Java Persistence API).
  - `spring-boot-starter-test`: Para las pruebas unitarias.
  - `spring-boot-docker-compose`: Para facilitar la ejecución de Docker Compose en el entorno de desarrollo (opcional).

- **PostgreSQL**:
  - `postgresql`: Para la conexión con la base de datos PostgreSQL.

- **OpenAPI**:
  - `springdoc-openapi-starter-webmvc-ui`: Para la documentación interactiva de la API con Swagger.

## Instrucciones para levantar el proyecto

1. **Clonar el repositorio**

   Si aún no tienes el proyecto en tu máquina local, clónalo con Git:

   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd carga-horaria
   ```

2. **Construir el proyecto con Maven**

   Primero, compila el proyecto con Maven:

   ```bash
   mvn clean install
   ```

   Esto descargará las dependencias necesarias y compilará el proyecto.

3. **Configurar la base de datos**

   Si deseas ejecutar PostgreSQL usando Docker Compose, asegúrate de tener Docker instalado y corre el siguiente comando en la raíz del proyecto:

   ```bash
   docker compose build
   ```

   Según tu sistema, también podría ser

   ```bash
   docker-compose build
   ```

   Esto va a crear un contenedor de PostgreSQL con la configuración predeterminada.

4. **Levantar la aplicación**

   Una vez que todo esté configurado, levantá la aplicación con:

   ```bash
   docker compose up
   ```

   o

   ```bash
   docker-compose up
   ```

   Por defecto, la aplicación debería arrancar en el puerto `8080`.

5. **Acceder a la API**

   Una vez que la aplicación esté corriendo, puedes acceder a la API en:

   ```text
   http://localhost:8080
   ```

   Para ver la documentación interactiva de la API (Swagger UI), ve a:

   ```text
   http://localhost:8080/swagger-ui.html
   ```

## Pruebas

Para ejecutar las pruebas unitarias:

```bash
mvn test
```

Esto ejecutará las pruebas configuradas en el proyecto usando JUnit y el marco de pruebas de Spring Boot.

## Notas adicionales

- Asegúrate de que Docker esté en ejecución si estás usando Docker Compose para la base de datos.
