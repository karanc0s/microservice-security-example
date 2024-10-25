FROM openjdk:22-jdk
LABEL authors="Karan"

EXPOSE 8000

ADD target/eureka-server.jar app-eureka-server.jar

ENTRYPOINT ["java", "-jar", "app-eureka-server.jar"]
