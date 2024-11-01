# Etapa de build
FROM maven:3.9.9-amazoncorretto-11-alpine AS build

WORKDIR /app

# Copia el pom.xml principal y los de los módulos
COPY pom.xml .
COPY common/pom.xml common/pom.xml
COPY domain/pom.xml domain/pom.xml
COPY application/pom.xml application/pom.xml
COPY maria-output-adapter/pom.xml maria-output-adapter/pom.xml
COPY mongo-output-adapter/pom.xml mongo-output-adapter/pom.xml
COPY rest-input-adapter/pom.xml rest-input-adapter/pom.xml
COPY cli-input-adapter/pom.xml cli-input-adapter/pom.xml

# Descargar dependencias necesarias
RUN mvn dependency:go-offline

# Copia el código fuente de todos los módulos
COPY common/src common/src
COPY domain/src domain/src
COPY application/src application/src
COPY maria-output-adapter/src maria-output-adapter/src
COPY mongo-output-adapter/src mongo-output-adapter/src
COPY rest-input-adapter/src rest-input-adapter/src
COPY cli-input-adapter/src cli-input-adapter/src

# Construcción de los módulos
RUN mvn install -DskipTests

# Etapa de despliegue
FROM amazoncorretto:11.0.25-alpine AS deploy

WORKDIR /app

# Copia el JAR compilado del adaptador REST en la imagen final
COPY --from=build /app/rest-input-adapter/target/*.jar /app/app.jar

# Define el punto de entrada
ENTRYPOINT ["java", "-jar", "/app/app.jar", "-Dspring.profiles.active=docker"]
