# Use a base image with Java 21
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file
# Assuming the project is built and the JAR is in the target directory
# You can also use a multi-stage build to build the JAR inside Docker
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
