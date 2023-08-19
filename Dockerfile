FROM maven:openjdk:20-oracle as builder

COPY ./src src/
COPY ./pom.xml pom.xml

RUN mvn clean package -DskipTests

FROM openjdk:20-oracle
COPY --from=builder target/*.jar store-backend.jar
EXPOSE 8080
CMD ["java", "-jar", "store-backend.jar"]