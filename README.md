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
To run this project, install it locally using abc:
(Dummy setup code)
```
mvn install effective-coding
mvn run
```
