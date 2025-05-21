FROM openjdk:21-bookworm
WORKDIR /app
COPY target/ds-lab-2024-0.0.1-SNAPSHOT.jar ./application.jar
CMD ["java", "-jar", "application.jar"]