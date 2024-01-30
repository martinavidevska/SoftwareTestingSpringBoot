

FROM openjdk:21

ARG JAR_FILE=target/*.jar

COPY ./target/CarRental-0.0.1-SNAPSHOT.jar.original app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
