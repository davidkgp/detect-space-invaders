FROM openjdk:8-jdk-alpine

ENV INPUT_ROOT="input/"
ENV FAULT_TOLERANCE_PERCENTAGE=10

WORKDIR /project

COPY target/detect-space-invaders-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar
CMD ["java","-jar","/project/app.jar"]