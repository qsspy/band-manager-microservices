FROM openjdk:21
WORKDIR /app
COPY target/gateway-1.0.0-SNAPSHOT.jar /app/gateway-1.0.0-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "gateway-1.0.0-SNAPSHOT.jar"]