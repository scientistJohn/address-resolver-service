FROM openjdk:8-alpine

COPY target/address-resolver-service-1.0-SNAPSHOT.jar /app.jar
EXPOSE 9001
CMD ["java", "-jar","app.jar"]