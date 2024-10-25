FROM openjdk:22-jdk
LABEL authors="Karan"

EXPOSE 8510

ADD target/cards-service.jar app-cards-service.jar

ENTRYPOINT ["java", "-jar", "app-cards-service.jar"]
