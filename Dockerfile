FROM amazoncorretto:23-alpine-jdk

EXPOSE 8080 8081

RUN mkdir app

COPY target/conference-app-1.0-SNAPSHOT.jar app
COPY config.yml app

WORKDIR app

CMD ["java","-jar","conference-app-1.0-SNAPSHOT.jar","server","config.yml"]