FROM maven:3.9.9-amazoncorretto-11-alpine AS build

WORKDIR /app

COPY pom.xml .
COPY common/pom.xml common/pom.xml
COPY application/pom.xml application/pom.xml

RUN mvn dependency:go-offline

COPY common/src common/src

RUN mvn install -DskipTests

FROM amazoncorretto:11.0.25-alpine AS deploy

WORKDIR /app

COPY --from=build /app/rest-input-adapter/target/*.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar", "-Dspring.profiles.active=docker"]