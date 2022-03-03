# currency-microservices-project
Four simple Spring Boot projects designed using microservices techniques and Spring Cloud frameworks.

## Table of Contents
* [List of contained projects](#list-of-contained-projects)
* [Technologies](#technologies)
* [Setup](#setup)

# List of contained projects
* [Currency Exchange Service](https://github.com/lordrose1951890/currency-microservices-project/tree/master/currency-exchange-service)
  : Allow client to query currency exchange rate.
* [Currency Conversion Service](https://github.com/lordrose1951890/currency-microservices-project/tree/master/currency-conversion-service)
  : Allow client to get currency conversion result using exchange rate from Currency Exchange Service.
* [Naming Server](https://github.com/lordrose1951890/currency-microservices-project/tree/master/naming-server)
* Service Discovery Server that registers Currency Exchange Service and Currency Conversion Service instances.
* [Api Gateway](https://github.com/lordrose1951890/currency-microservices-project/tree/master/api-gateway)
  : Provide effective routing to Currency Exchange Service and Currency Conversion Service instances.

# Technologies
This project is using:
* Java 17
* Spring Boot for auto config and Docker Image creating capability.
* Spring MVC for building web app.
* Spring Cloud Gateway for api routing and filtering or provide cross-cutting concerns
* Spring Cloud Netflix:
  * Eureka Server and Client for service discovery.
  * Resilience4j to provide circuit breaker, rare limit... to Eureka client.
  * (Spring Cloud) LoadBalancer for client side load balancing.
  * (Spring Cloud) OpenFeign reduce template codes when calling other Eureka clients.
* Spring Cloud Sleuth, Zipkin for distributed data tracing.
* RabbitMQ for messages queue between Eureka client with Sleuth and Zipkin.
* Docker and Docker Compose

# Setup
Docker is required to run these projects:
1. For all 4 projects, each, you use need to build the docker image using the maven spring-boot plugin or the 
   command line:
```
mvn spring-boot:build-image
```

or ```mvnw spring-boot:build-image``` if you don't have Maven installed.

2. Using Docker CLI and navigate to the docker-compose.yaml file, type:
```
docker compose up
```

# Run
Open up your browser and navigate to [localhost:8761](http://localhost:8761) and confirm the Eureka server is 
running. If the server is running, you should be able to see all 3 of the other project instances are also running 
with the status 'UP'.

# End points:
[Currency Exchange Service](https://github.com/lordrose1951890/currency-microservices-project/tree/master/currency-exchange-service)
* http://localhost:8000/currency-exchange/usd/to/vnd
* http://localhost:8000/currency-exchange/cad/to/vnd
* http://localhost:8000/currency-exchange/eur/to/vnd

[Currency Conversion Service](https://github.com/lordrose1951890/currency-microservices-project/tree/master/currency-conversion-service)
* http://localhost:8100/currency-conversion/feign/usd/to/vnd/quantity/10

[Api Gateway](https://github.com/lordrose1951890/currency-microservices-project/tree/master/api-gateway)
* http://localhost:8765/currency-exchange/usd/to/vnd
* http://localhost:8765/currency-conversion/feign/usd/to/vnd/quantity/10
* http://localhost:8765/conversion/usd/to/vnd/quantity/10
