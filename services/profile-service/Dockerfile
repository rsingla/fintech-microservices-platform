FROM eclipse-temurin:21-jre-alpine
ARG JAR_FILE=target/profile-service-0.1.0-SNAPSHOT.jar
WORKDIR /app
COPY ${JAR_FILE} app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75", "-jar", "/app/app.jar"]
