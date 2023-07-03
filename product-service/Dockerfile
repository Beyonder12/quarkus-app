FROM maven:3.8.4-openjdk-17 AS maven

WORKDIR /usr/src/app
#COPY . /usr/src/app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ src/
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /opt/app/

# COPY the whole quarkus-app directory, not just the JAR file
COPY --from=maven /usr/src/app/target/quarkus-app/ /opt/app/quarkus-app

EXPOSE 2002

# Run the JAR file within the quarkus-app directory
ENTRYPOINT ["java", "-jar", "quarkus-app/quarkus-run.jar"]