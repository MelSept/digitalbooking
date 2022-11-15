FROM openjdk:19-jdk-alpine
ARG JAR_FILE=/target/*.jar
COPY ${JAR_FILE} api-lodgings.jar
ENV DB_URL=digitalhouse.cluster-cnyqegjgetrv.us-east-2.rds.amazonaws.com/0521PTC12N2db_GRUPO6
ENV DB_USER=0521PTC12N2db_GRUPO6
ENV DB_PASSWORD=Ciophoo9
ENTRYPOINT [ "java", "-jar", "/api-lodgings.jar" ]

# Build Image
# docker build -t digitalbooking-api-lodgings:v1 .

# Run image
# docker run --name digitalbooking-api-lodgings -dp 8081:8081 digitalbooking-api-lodgings:v1