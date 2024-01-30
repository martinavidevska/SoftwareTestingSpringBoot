FROM eclipse-temurin:17-jre-alpine
COPY --from=builder target/*.jar app.jar
EXPOSE 9090
CMD ["java","-jar","app.jar"]