FROM openjdk:22-jdk
LABEL authors="Karan"

EXPOSE 8520

ADD target/loans-service.jar app-loans-service.jar

ENTRYPOINT ["java", "-jar", "app-loans-service.jar"]
