# Project eCommerce Back-end 

Technical Exercise Back-end for IDruide

 **A. To launch the application follow the steps below:**

   **1.  prerequisite to install:**

         -  Docker
         -  Maven
         -  java 8

   **2. initial directory**

      Move to the "main" directory where the docker-compose.yml file is located

  **3. Build project.**
     Creation of application JARs

      
      -    mvn clean install -f catalogservice/pom.xml
      -    mvn clean install -f orderservice/pom.xml
      -    mvn clean install -f packingservice/pom.xml   

   **4. Go to the main directory and create the network: networkIdruide**

       docker network create networkIdruide

   **5. launch docker compose in  main directory:**

      docker-compose up

   **6. please Wait until all microservices and kafka services are completely launch**

    You can now begin test by open postman or chrome browser


 **B. To Test  application follow the steps below:**


  Open your favorite Browser and use these urls for testing with graphiql:
   
  -   http://localhost:10555/graphiql for access to catalogservice
  -   http://localhost:10444/graphiql for access to orderservice
  -   http://localhost:10333/graphiql for access to packingservice
      
      
  Open postman and use these urls for testing

     
  -  http://localhost:10555/catalog for catalogservice
  -  http://localhost:10444/order    for orderservice
  -  http://localhost:10333/packing  for packingservice
      


  You can use the test sets found in the directory

        /backend/posmanTests

      

**C. To lauch kubernetes, and Deploy DB or services please use files .yaml in folder k8s**

 Follow these steps:

  Prerequis
   
-  Please install minikube and kubectl
-  You can change the replicas set in file *.yml
-  Open an terminal and do 

```
      minikube stop
      minikube delete
      minikube start
      minikube ip ( ex:192.168.49.2 this ip will be use after)
      kubectl create -f mysql-service-service.yaml,mysql-service-deployment.yaml
      ,catalog-service-pod.yaml,catalog-service-service.yaml
      ,packing-service-pod.yaml,packing-service-service.yaml
      ,order-service-pod.yaml,order-service-service.yaml
      ,kafka-service.yaml,kafka-deployment.yaml
      ,zookeeper-service.yaml,zookeeper-deployment.yaml
      ,kafka-claim0-persistentvolumeclaim.yaml
      ,networkIdruide-networkpolicy.yaml
```
   In order to check, please  do:
```
      kubectl get configmaps
      kubectl get pods
      kubectl get services
      kubectl get deployments
```
you will see all services and deployments done.

In order to test your deployment,use the ip (ex:192.168.49.2) in chome browser.






   
