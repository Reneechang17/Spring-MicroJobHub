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

  - Below diagram shows how trace and span IDs are used in Zipkin to track request.
  ![Zipkin tracing progress](https://github.com/Reneechang17/Spring-MicroJobHub/blob/main/static/progress2/Tracing%20with%20Trace%20and%20Span%20IDs.png)

- Integrated Config Server allows centralized management of configurations across microservices which fetches configurations from a Git repository.
![Config Server](https://github.com/Reneechang17/Spring-MicroJobHub/blob/main/static/progress2/Spring%20Cloud%20Config%20Server.png)

- Services running at(local):

| Service            | PORT    |
| ------------------ | ------- |
| `eureka`           | `:8761` |
| `zipkin`           | `:9411` |
| `configserver`     | `:8080` |

### Progress 3: API Gateway & Resilience4J & RabbitMQ
- Spring Cloud Gateway routes external requests to appropriate microservices, providing a single entry point for the system.
![API Gateway](https://github.com/Reneechang17/Spring-MicroJobHub/blob/main/static/progress3/API%20Gateway.jpg)

- Resilience4J uses a circuit breaker to monitor service health and manage traffic with a rate limiter, preventing overload and maintaining service availability.
![Resilience4J](https://github.com/Reneechang17/Spring-MicroJobHub/blob/main/static/progress3/%20Resilience4J%20.jpg)

- RabbitMQ use for decoupled communication between services. 
  - The Review service acts as a producer, sending rating information to the Company service.
  - And Company service acts as a consumer. 
![RabbitMQ](https://github.com/Reneechang17/Spring-MicroJobHub/blob/main/static/progress3/rabbitmq.jpg)

- Services running at(local):

| Service            | PORT    |
| ------------------ | ------- |
| `gateway`          | `:8084` |
| `rabbitmq`         | `:15672`|

### Progress 4: Docker & Kubernetes for Project
- Use Docker to containerize services for easy deployment and scalability across different systems. 
![Docker for project](https://github.com/Reneechang17/Spring-MicroJobHub/blob/main/static/progress4/Docker%20for%20Project.jpg)

- Docker images
  - company-service: renee6177/companyms
  - job-service: renee6177/jobms
  - review-service: renee6177/reviewms
  - config-service: renee6177/configserver
  - gateway: renee6177/gateway
  - server-registry: renee6177/servicereg
  ![Docker images](https://github.com/Reneechang17/Spring-MicroJobHub/blob/main/static/progress4/docker%20images.jpg)

- Kubernetes for companyms, jobms and reviewms: Orchestrates container deployment, scaling, and management for efficient and consistent service operation across a cluster, providing automatic load balancing, service discovery, and self-healing capabilities to ensure optimal performance and reliability.

- Kubernetes for Postgres, Zipkin and RabbitMQ
  - Postgres: Stores application configuration in a centralized manner, allowing services to retrieve and apply configurations dynamically without service restarts.
  - Zipkin & RabbitMQ: Deploys Zipkin for distributed tracing capabilities, RabbitMQ for messaging system to handle communication between services.

- If use k8s, our project do not need API Gateway and Eureka server.








