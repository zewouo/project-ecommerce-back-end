
# Project eCommerce Back-end 

 # Project eCommerce Back-end 

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

   **4. From  main directory  create the network: networkIdruide**

       docker network create networkIdruide

   **5. From main directory launch docker-compose: **

      docker-compose up

   **6. please Wait until all microservices and kafka services are completely launch**

     You can now begin test by open postman or chrome browser


 **B. To Test  application follow the steps below:**


  Open your favorite Browser and use these urls for testing with graphiql:
   
  -   http://localhost:10555/graphiql for access to catalogservice
  -   http://localhost:10444/graphiql for access to orderservice
  -   http://localhost:10333/graphiql for access to packingservice
      
      
  Open postman (please use Postman v8.2.3) and use these urls for testing

     
  -  http://localhost:10555/catalog  for catalogservice  (catalog API)
  -  http://localhost:10444/order    for orderservice     (order API)
  -  http://localhost:10333/packing  for packingservice   (packing API)
      


  You can use the test sets found in the directory

        /backend/posmanTests
   
   Please change the imput parameter on API method call.

   To play with application please insert products in the DB by using catalog API and this following method:

   ```
      mutation {
         createProduct(productInput:{name:"BMW motor and belts",codeProduct:"12345678",
         price:2000,description:"new factory",quantity:400}){
         codeProduct
         name
         price
         description
         quantity
      }

      } 
         
   ```


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
If all step above are well done , you will see all services and deployments done.

In order to test your deployment,use the ip (ex:192.168.49.2) you have got in the past in chrome browser.







