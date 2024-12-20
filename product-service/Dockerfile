dockerfile
Copy code
# Stage 1: Build the Spring Boot application
FROM gradle:7.6-jdk17 AS build-stage

# Set the working directory inside the container
WORKDIR /app

# Copy Gradle configuration files
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Pre-download dependencies for faster builds
RUN ./gradlew dependencies --no-daemon

# Copy the rest of the application source code
COPY src ./src

# Build the Spring Boot application JAR
RUN ./gradlew bootJar --no-daemon

# Stage 2: Run the application using JDK runtime
FROM openjdk:17-jdk-alpine AS run-stage

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build-stage /app/build/libs/*.jar app.jar

# Expose the application's default port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

