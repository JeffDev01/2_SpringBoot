FROM adoptopenjdk/openjdk11:alpine
EXPOSE 8040
ADD target/forum-api.jar forum-api.jar
ENTRYPOINT ["java", "-jar", "/forum-api.jar"]