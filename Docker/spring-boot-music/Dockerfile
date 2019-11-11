FROM levep79/jdk-alpine:latest

COPY ./artifacts/spring-music.jar /spring-music.jar

CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active", "/spring-music.jar"]
