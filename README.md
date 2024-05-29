# Spring Boot and Microservices Project: MicroJobHub

### Project Introduction
- **MicroJobHub** is a system that leverages a **Spring Boot microservices** architecture to streamline job application processes. It utilizes **Docker** and **Kubernetes** for scalable deployment and management. Each service have its own database, with **Eureka** for service discovery and **RabbitMQ** for reliable messaging. **Spring Cloud Gateway** directs external requests, **Zipkin** provides distributed tracing for monitoring, and **Spring Cloud Config** manages external configurations centrally. **Resilience4J** introduces resilience patterns such as circuit breakers. MicroJobHub efficiently enhances job application system.

- Docker Hub Repository: https://hub.docker.com/repositories/renee6177

### Technologies Used
- Programming: Java
- Frameworks and Tools: Spring Boot, Docker, Kubernetes
- Service Communication: RabbitMQ, Eureka, API Gateway
- Database: PostgreSQL, DTOs
- Monitoring and Testing: Zipkin, Actuator, Postman, JMeter
- Others: Spring Cloud Config, Resilience4J

### Progress 1: Monolithic To Microservices
- The shift from a monolithic to a microservices architecture breaks down a unified application into independent services, enhancing scalability and deployment flexibility. 
![Microservices Architecture](https://github.com/Reneechang17/Spring-MicroJobHub/blob/main/static/progress1/Monolithic%20To%20Microservices.jpg)
- DTOs facilitate data transfer between these services, optimizing network communication.
![DTOs](https://github.com/Reneechang17/Spring-MicroJobHub/blob/main/static/progress1/DTOs.jpg)

- Services running at(local):

| Service            | PORT    |
| ------------------ | ------- |
| `company-ms`       | `:8081` |
| `job-ms`           | `:8082` |
| `review-ms`        | `:8083` |

### Progress 2: Add Server Registry(Eureka) & Zipkin & Spring Cloud Config Server
- Used a service registry(Eureka) which enabled services like Job and Review to dynamically discover each other for inter-service communication.
![Eureka](https://github.com/Reneechang17/Spring-MicroJobHub/blob/main/static/progress2/Service%20Registry%20Flow.png)

- Zipkin is used for distributed tracing, helping to pinpoint failures or bottlenecks across microservices by tracing requests from end to end.
![Zipkin](https://github.com/Reneechang17/Spring-MicroJobHub/blob/main/static/progress2/Distributed%20Tracing%20with%20Zipkin.png)

  - Below diagram shows how trace and span IDs are used in Zipkin to track requests.
  ![Zipkin tracing progress](https://github.com/Reneechang17/Spring-MicroJobHub/blob/main/static/progress2/Tracing%20with%20Trace%20and%20Span%20IDs.png)

- Integrated Config Server allows centralized management of configurations across microservices which fetches configurations from a Git repository
![Config Server](https://github.com/Reneechang17/Spring-MicroJobHub/blob/main/static/progress2/Spring%20Cloud%20Config%20Server.png)

- Services running at(local):

| Service            | PORT    |
| ------------------ | ------- |
| `eureka`           | `:8761` |
| `zipkin`           | `:9411` |
| `configserver`     | `:8080` |

### Progress 3: API Gateway & Resilience4J & RabbitMQ

### Progress 4: Docker & Kubernetes for Project




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