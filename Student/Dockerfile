# Use a base image with Java (OpenJDK 17)
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the executable jar file
COPY target/*.jar app.jar


# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
