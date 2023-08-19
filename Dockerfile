FROM openjdk:20-oracle

RUN mvn clean package
COPY target/*.jar store-backend.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "store-backend.jar"]