## Spring Boot and Microservices Project: MicroJobHub

## [Technologies Used]
- Programming: Java
- Frameworks and Tools: Spring Boot, Docker, Kubernetes
- Service Communication: RabbitMQ, Eureka, API Gateway
- Database: PostgreSQL, DTOs
- Monitoring and Testing: Zipkin, Actuator, Postman, JMeter
- Others: Spring Cloud Config, Resilience4J

Services running at(local):

| Service            | PORT    |
| ------------------ | ------- |
| `company-ms`       | `:8081` |
| `job-ms`           | `:8082` |
| `review-ms`        | `:8083` |
| `gateway`          | `:8084` |
| `pgadmin`          | `:5050` |
| `configserver`     | `:8080` |
| `eureka`           | `:8761` |
| `rabbitmq`         | `:15672`|
| `zipkin`           | `:9411` |