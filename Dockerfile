FROM maven:3.9.6-eclipse-temurin-21-alpine as builder

WORKDIR /app

COPY pom.xml .

COPY src ./src

# maybe you dont use docker compose and you want to pass the env variables
ARG SERVER_SERVLET_CONTEXT_PATH=/api/v1
ARG SPRING_DATA_MONGODB_URI=mongodb://localhost:27017/
ARG SPRING_DATA_MONGODB_DATABASE=cinemabookingsystem
ARG JWT_SECRET_KEY=mysecretkey
ARG JWT_EXPIRATION_TIME=86400000

ENV SERVER_SERVLET_CONTEXT_PATH=${SERVER_SERVLET_CONTEXT_PATH}
ENV SPRING_DATA_MONGODB_URI=${SPRING_DATA_MONGODB_URI}
ENV SPRING_DATA_MONGODB_DATABASE=${SPRING_DATA_MONGODB_DATABASE}
ENV JWT_SECRET_KEY=${JWT_SECRET_KEY}
ENV JWT_EXPIRATION_TIME=${JWT_EXPIRATION_TIME}

RUN mvn clean package -DskipTests

# Path: Dockerfile
FROM openjdk:23-jdk-oracle

WORKDIR /app

COPY --from=builder app/target/cinemabookingsystem.jar .

EXPOSE 8080

CMD ["java", "-jar", "/app/cinemabookingsystem.jar"]
