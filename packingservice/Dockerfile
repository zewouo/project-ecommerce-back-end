FROM java:8
ADD  packingservice/target/packingservice-0.0.1-SNAPSHOT.jar packingservice-0.0.1.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","packingservice-0.0.1.jar"]