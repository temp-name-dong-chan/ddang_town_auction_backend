FROM openjdk:21-jdk
ARG JAR_FILE=./build/libs/ddang_town_auction-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
