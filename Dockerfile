FROM openjdk:8-jdk-alpine
USER root
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} game.jar
ENTRYPOINT ["java","-jar","/game.jar"]