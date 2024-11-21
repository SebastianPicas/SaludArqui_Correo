FROM openjdk:24-ea-17-jdk-oraclelinux8

WORKDIR /app

COPY /build/libs/correos-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081


CMD ["java", "-jar", "app.jar"]