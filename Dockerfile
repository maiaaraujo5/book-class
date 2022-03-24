FROM maven:3.8.4-openjdk-11 AS BUILDER
WORKDIR /build
COPY . /build

RUN mvn clean package

FROM adoptopenjdk/openjdk11:alpine
COPY --from=BUILDER /build/target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar", "-Ddebug", "-Xmx128m"]
