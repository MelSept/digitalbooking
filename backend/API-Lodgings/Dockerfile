FROM openjdk:19-jdk-alpine
ARG JAR_FILE=/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar" ]

# Build Image
# docker build -t api-lodgings:v1 .

# Run image
# docker run --name api-lodgings -dp 8081:8081 api-lodgings:v1