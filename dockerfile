FROM openjdk:23
COPY target/carga-horaria-0.0.1-SNAPSHOT.jar carga-horaria.jar
ENTRYPOINT ["java", "-jar", "carga-horaria.jar"]
