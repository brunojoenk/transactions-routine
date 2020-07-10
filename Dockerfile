FROM openjdk:11-jdk-slim-buster
VOLUME /tmp
COPY target/transactionsroutine-*.jar app.jar

ARG DB_PASSWORD
ENV DATABASE_PASSWORD=$DB_PASSWORD

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]