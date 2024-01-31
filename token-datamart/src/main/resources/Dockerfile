FROM openjdk:21-jdk-slim

WORKDIR /usr/src/app
COPY token-datamart-1.0.jar /usr/src/app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "token-datamart-1.0.jar", "http://host.docker.internal"]

# docker build -t token-datamart .
termi