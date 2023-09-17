FROM openjdk:11
ARG JAR_FILE=./build/libs/Server-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
COPY ./src/main/resources/serviceAccountKey.json /src/main/resources/serviceAccountKey.json
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]

