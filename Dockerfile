FROM gradle:7.4.2-jdk17 AS build

WORKDIR /app

COPY gradle /app/gradle
COPY gradlew /app/gradlew
COPY build.gradle /app/build.gradle
COPY settings.gradle /app/settings.gradle

COPY src /app/src
RUN ./gradlew clean build -x test



FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/app.jar

ENV PORT=8080
EXPOSE 8080

 ENTRYPOINT ["java", "-jar", "/app/app.jar"]
