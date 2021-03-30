FROM java:8
ADD  orderservice/target/orderservice-0.0.1-SNAPSHOT.jar orderservice-0.0.1.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","orderservice-0.0.1.jar"]