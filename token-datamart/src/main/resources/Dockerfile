FROM openjdk:21-jdk-slim

WORKDIR /usr/src/app
COPY token-datamart-1.0.jar /usr/src/app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "token-datamart-1.0.jar"]

# docker build -t token-datamart .
# docker run -p 8080:8080 --name token-datamart --env AWS_ACCESS_KEY_ID=test --env AWS_SECRET_ACCESS_KEY=test token-datamart