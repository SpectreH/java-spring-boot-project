FROM gradle:8.8-jdk21 AS builder
WORKDIR /home/gradle/src
COPY . .
RUN gradle clean bootJar

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /home/gradle/src/app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]