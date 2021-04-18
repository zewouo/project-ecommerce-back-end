# Project eCommerce Back-end 

Technical Exercise Back-end for IDruide

- **To launch the application follow the steps below:**

**1.  prerequisite to install:**

-  Docker
-  Maven
-  java 8

**2. initial directory**

  Move to the "main" directory where the docker-compose.yml file is located

**3. Go to the main directory and create the network**

   `docker network create networkIdruide`

**4. Build project.**
  Creation of application JARs

```
-    mvn clean install -f catalogservice/pom.xml
-    mvn clean install -f orderservice/pom.xml
-    mvn clean install -f packingservice/pom.xml
```



**5. launch docker compose in the main directory:**

   docker-compose up

**6. please Wait until all microservices and kafka services are completely launch**

- **To Test the application follow the steps below:**

**7. open postman and use these urls for testing or
   use browser with graphiql:
   
    (http://localhost:10555/graphiql)
    (http://localhost:10444/graphiql)
    (http://localhost:10333/graphiql)

```
-     (http://localhost:10555/catalog) for catalogservice
-     [text](http://localhost:10444/order )   for orderservice
-     [text](http://localhost:10333/packing ) for packingservice
```


**8. You can use the test sets found in the directory**

     /backend/posmanTests

**9. To lauch kubernetes, and Deploy DB and services please use files .yaml in folder k8s**
   Follow these steps:
   
-  please install minikube and kubectl
-  you can change the replicas set in file *.yml
-  open an terminal and do 
```
      minikube stop
      minikube delete
      minikube start
      minikube ip (this ip will be use after)
      kubectl create -f mysql-service-service.yaml,mysql-service-deployment.yaml
      ,catalog-service-pod.yaml,catalog-service-service.yaml
      ,packing-service-pod.yaml,packing-service-service.yaml
      ,order-service-pod.yaml,order-service-service.yaml
      ,kafka-service.yaml,kafka-deployment.yaml
      ,zookeeper-service.yaml,zookeeper-deployment.yaml
      ,kafka-claim0-persistentvolumeclaim.yaml
      ,networkIdruide-networkpolicy.yaml
```
   After to check do:
```
- kubectl get configmaps
- kubectl get pods
- kubectl get services
- kubectl get deployments
```






   
