FROM java:8
ADD  catalogservice/target/catalogservice-0.0.1-SNAPSHOT.jar catalogservice-0.0.1.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","catalogservice-0.0.1.jar"]
