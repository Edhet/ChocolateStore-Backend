FROM openjdk:20-oracle
COPY target/*.jar store-backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "store-backend.jar"]