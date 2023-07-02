# Use the official maven/Java 17 image to create a build artifact.
FROM maven:3.8.4-openjdk-17 as builder

# Set the working directory in the image to /app
WORKDIR /app

# Copy the pom.xml file into the image
COPY pom.xml .

# Download all required dependencies into one layer
RUN mvn dependency:go-offline -B

# Copy your other files
COPY src ./src

# Build a release artifact.
RUN mvn package -DskipTests

# Use openjdk for the final image
FROM openjdk:17-jdk-alpine

# Set the working directory in the image to /app
WORKDIR /app

# Copy the jar file into the image
COPY --from=builder /app/target/*.jar /app/auth-service.jar

# Run the application
CMD ["java", "-jar", "/app/auth-service.jar"]
