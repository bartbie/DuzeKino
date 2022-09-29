FROM maven:3.8.6-openjdk-18 AS build
MAINTAINER bartbie37@gmail.com

WORKDIR usr/src/app

COPY ./src/main/ ./src
COPY ./pom.xml .
RUN mvn -f /usr/src/app/pom.xml clean package


FROM gcr.io/distroless/java

ARG JAR

COPY --from=build ./target/"${JAR}" ./app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
