# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory
WORKDIR /app

ARG JAR_FILE=target/*.jar # For Maven

COPY ${JAR_FILE} app.jar


# ${} the port Spring Boot runs on
EXPOSE ${SERVER_PORT}

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]
