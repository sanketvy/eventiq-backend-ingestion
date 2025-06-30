FROM openjdk:21-slim

WORKDIR /app

COPY /target/eventiq-ingestion-0.0.1-SNAPSHOT.jar /app

EXPOSE 9000

CMD ["java","-jar","eventiq-ingestion-0.0.1-SNAPSHOT.jar"]
