# Project eCommerce Back-end 

Technical Exercise Back-end for IDruide

To launch the application follow the steps below:

0- prerequisite to install:

Docker
Maven
java 8

1-initial directory

  Move to the "main" directory where the docker-compose.yml file is located

2- Go to the main directory and create the network

   docker network create networkIdruide

3- Build project.
Creation of application JARs

mvn clean install -f catalogservice/pom.xml
mvn clean install -f orderservice/pom.xml
mvn clean install -f packingservice/pom.xml

4-launch docker compose in the main directory:

docker-compose up

5-please Wait until all microservices and kafka services are completely launch

6- open postman and use these urls for testing or
use browser with graphiql (http://localhost:10555/graphiql)

http://localhost:10555/catalog for catalogservice
http://localhost:10444/order   for orderservice
http://localhost:10333/packing for packingservice

7-You can use the test sets found in the directory
./backend/posmanTests

8.To lauch kubernetes,please use files .yml in folder k8s
Follow these steps:
   8.0 please install minikube and kubectl
   8.1 you can change the replicas set in file *.yml
    
