FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY entrypoint.sh /entrypoint.sh
RUN sed -i 's/\r$//' /entrypoint.sh && chmod +x /entrypoint.sh
RUN mkdir /logs
RUN touch /logs/app.log
ENTRYPOINT java -jar /app.jar > /logs/app.log 2>&1
