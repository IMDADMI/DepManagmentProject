FROM openjdk:19-jdk-alpine

WORKDIR     /springApp

COPY       target/MiniProject.jar ./

ENV port=8080

EXPOSE ${port}
#RUN         ./mvnw install

# WORKDIR     /springApp/target

ENTRYPOINT  ["java", "-jar","MiniProject.jar"]
