# FROM openjdk:23
# COPY target/carga-horaria-0.0.1-SNAPSHOT.jar carga-horaria.jar
# ENTRYPOINT ["java", "-jar", "carga-horaria.jar"]


# Etapa de construcción: Usar una imagen base con Maven preinstalado
# FROM maven:3.9-openjdk-17 AS builder
# FROM maven:3.9-eclipse-temurin-17 AS builder
FROM maven:3.9-eclipse-temurin-23 AS builder

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml y las fuentes del proyecto
COPY pom.xml .
COPY src ./src

# Ejecutar Maven para compilar el JAR
RUN mvn clean package -DskipTests

# Etapa de ejecución: Usar una imagen con OpenJDK para ejecutar la aplicación
FROM openjdk:23

# Establecer el directorio de trabajo para la ejecución
WORKDIR /app

# Copiar el JAR generado desde la etapa anterior
COPY --from=builder /app/target/carga-horaria-0.0.1-SNAPSHOT.jar carga-horaria.jar

# Configurar el comando para ejecutar el JAR
ENTRYPOINT ["java", "-jar", "carga-horaria.jar"]
