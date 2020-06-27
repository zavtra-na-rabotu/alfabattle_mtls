FROM openjdk:11-jre-slim

COPY ./build/libs/mtls*.jar /opt/app.jar

ENV SERVER_PORT=8080
EXPOSE 8080
USER 65534:65534

ENTRYPOINT java \
    -Djava.security.egd=file:/dev/./urandom \
    -jar /opt/app.jar
