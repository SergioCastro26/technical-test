FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu as base

COPY target/technical-test.jar technical-test.jar

EXPOSE 8080

CMD ["java", "-jar", "technical-test.jar"]