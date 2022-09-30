FROM openjdk:17
MAINTAINER bartbie37@gmail.com

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw ./
COPY pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]