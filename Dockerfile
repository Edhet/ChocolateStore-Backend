FROM maven:3.9.3-eclipse-temurin-20 as builder

COPY ./src src/
COPY ./pom.xml pom.xml

RUN mvn clean package -DskipTests

FROM eclipse-temurin:20-jre-alpine
COPY --from=builder target/*.jar store-backend.jar
EXPOSE 8080
CMD ["java", "-jar", "store-backend.jar"]