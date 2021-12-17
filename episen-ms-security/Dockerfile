FROM openjdk:11

COPY target/episen-ms-security-1.0.0-SNAPSHOT.jar jwt.jar
COPY src/main/resources/keys/server.p12 /keys/server.p12

CMD ["java", "-jar", "/jwt.jar"]

EXPOSE 9070