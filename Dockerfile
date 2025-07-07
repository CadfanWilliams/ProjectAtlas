FROM --platform=linux/arm64 azul/zulu-openjdk-alpine
MAINTAINER cadfan williams
COPY target/ProjectAtlas-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]