FROM openjdk:8
COPY ./target/demo-0.0.1-SNAPSHOT.jar explore-canada-client.jar
EXPOSE 5000
CMD ["java","-jar","explore-canada-client.jar"]