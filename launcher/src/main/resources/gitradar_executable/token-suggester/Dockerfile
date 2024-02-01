FROM openjdk:21-jdk-slim

WORKDIR /usr/src/app
COPY token-suggester-1.0.jar /usr/src/app

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "token-suggester-1.0.jar"]