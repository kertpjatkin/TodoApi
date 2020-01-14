FROM openjdk:11
COPY . ./
RUN ./gradlew pmd
RUN ./gradlew test
RUN ./gradlew build
EXPOSE 8080
CMD ["java", "-jar", "build/libs/todo-api-0.0.1-SNAPSHOT.jar"]
