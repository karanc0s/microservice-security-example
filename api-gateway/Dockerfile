FROM openjdk:22-jdk
LABEL authors="Karan"

EXPOSE 8001

ADD target/api-gateway.jar app-api-gateway.jar

ENTRYPOINT ["java", "-jar", "app-api-gateway.jar"]
