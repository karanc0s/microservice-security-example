FROM openjdk:22-jdk
LABEL authors="Karan"

EXPOSE 8530

ADD target/accounts-service.jar app-accounts-service.jar

ENTRYPOINT ["java", "-jar", "app-accounts-service.jar"]
