FROM openjdk:17-alpine

EXPOSE 8000

COPY ./target/currency-exchange-service-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]