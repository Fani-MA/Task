FROM openjdk:17.0.2-jdk-slim-buster
COPY target/Task-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]