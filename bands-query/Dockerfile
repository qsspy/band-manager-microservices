FROM openjdk:21
WORKDIR /app
COPY target/bands-query-1.0.0-SNAPSHOT.jar /app/bands-query-1.0.0-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "bands-query-1.0.0-SNAPSHOT.jar"]