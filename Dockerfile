From openjdk:8
copy ./target/microservice-0.0.1-SNAPSHOT.jar microservice-0.0.1-SNAPSHOT.jar
EXPOSE 5000
CMD ["java","-jar","microservice-0.0.1-SNAPSHOT.jar"]
