FROM maven:3-openjdk-11-slim AS builder
WORKDIR /app
COPY ./demo/pom.xml ./
COPY ./demo/src src/
RUN mvn -f /app/pom.xml clean package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY /demo/target/*.jar ./
EXPOSE 8080
CMD ["java","-jar","demo-0.0.1-SNAPSHOT.jar"]
