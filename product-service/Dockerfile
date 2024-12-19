# Stage 1: Build the Spring Boot application
FROM gradle:7.6-jdk17 AS build-stage

WORKDIR /app

# Install required tools
RUN apt-get update && apt-get install -y \
    bash curl zip unzip \
    && rm -rf /var/lib/apt/lists/*

COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Ensure gradlew is executable
RUN chmod +x gradlew

# Pre-download dependencies for faster builds
RUN ./gradlew dependencies --no-daemon

COPY src ./src

# Build the Spring Boot application JAR
RUN ./gradlew bootJar --no-daemon

# Stage 2: Run the application using JDK runtime
FROM openjdk:17-jdk AS run-stage

WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build-stage /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
