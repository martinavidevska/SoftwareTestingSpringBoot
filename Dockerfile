FROM eclipse-temurin:17-jre-alpine
COPY --from=builder target/*.jar app.jar
EXPOSE 9090
CMD ["java","-jar","app.jar"]


FROM openjdk:21

ARG JAR_FILE=target/*.jar

COPY ./target/CarRental-0.0.1-SNAPSHOT.jar.original app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
