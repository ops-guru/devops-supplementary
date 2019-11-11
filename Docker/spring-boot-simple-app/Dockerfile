FROM maven:3.6.2-jdk-8-slim as builder 
WORKDIR /app
COPY spring-boot-app .

RUN mvn install

CMD ["java", "-jar", "-Dspring.profiles.active=default", "./docker/spring-boot-app-0.0.1-SNAPSHOT.war"]

# # Alpine Linux with OpenJDK JRE
# FROM openjdk:8-jre-alpine
# WORKDIR /app
# # copy WAR into image
# COPY --from=builder /app/docker/spring-boot-app-0.0.1-SNAPSHOT.war /app/app.war 
# # run application with this command line 
# CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=default", "/app/app.war"]
# # http://localhost:8081/springbootapp/employees