FROM openjdk:11 AS builder
WORKDIR /app
COPY ./demo ./
RUN ./mvnw package

FROM openjdk:17-alpine
WORKDIR /usr/local/target
COPY /demo/target/*.jar ./
EXPOSE 8080
CMD ["java","-jar","demo-0.0.1-SNAPSHOT.jar"]
