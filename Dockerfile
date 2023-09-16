#FROM openjdk:11
#ARG JAR_FILE=./build/libs/Server-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE} app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/app.jar"]

# 기본 이미지 설정
FROM openjdk:11

# 작업 디렉토리 설정
WORKDIR /app

# 호스트 머신의 프로젝트 파일을 이미지 내부로 복사
COPY ./src /app/src
COPY ./build.gradle /app/build.gradle

# Java 애플리케이션 빌드 및 실행
RUN javac -cp /app src/main/java/HDmedi/Server/ServerApplication.java
CMD ["java", "-cp", "/app", "src/main/java/HDmedi/Server/ServerApplication.java"]
