🏥 HealthOps - Microservices-Based Healthcare Management System

HealthOps is a cloud-ready healthcare management system built using a microservices architecture with Spring Boot. The project demonstrates modern backend development practices, including RESTful APIs, gRPC communication, event-driven architecture with Kafka, API Gateway, JWT authentication, Docker containerization, integration testing, and Infrastructure as Code (IaC) using AWS CDK and LocalStack.

🚀 Features
Patient Management Service (CRUD operations)
Billing Service using gRPC
Analytics Service using Apache Kafka
Authentication & Authorization using JWT
API Gateway for request routing and security
OpenAPI (Swagger) documentation
Request validation and centralized exception handling
Dockerized microservices
Integration testing
Infrastructure provisioning using AWS CDK & CloudFormation
Local cloud deployment using LocalStack
🏗️ Architecture

The application consists of multiple independently deployable microservices:

Patient Service
Manages patient records
Exposes REST APIs
Stores patient data in PostgreSQL
Publishes patient events to Kafka
Communicates with Billing Service using gRPC
Billing Service
Handles billing-related operations
Exposes gRPC endpoints
Communicates with Patient Service via Protocol Buffers
Analytics Service
Consumes Kafka events
Processes patient-related events asynchronously
Authentication Service
User authentication
JWT token generation and validation
Password encryption using Spring Security
API Gateway
Single entry point for all client requests
Routes requests to downstream services
JWT validation filter
Centralized security and exception handling
🛠️ Technologies Used
Backend
Java
Spring Boot
Spring MVC
Spring Data JPA
Spring Validation
Spring Security
Communication
REST APIs
gRPC
Protocol Buffers
Apache Kafka
Database
PostgreSQL
H2 Database (during initial development)
Documentation
OpenAPI / Swagger
Containerization
Docker
Docker Compose
Testing
JUnit
Spring Boot Integration Testing
Cloud & Infrastructure
AWS CDK (Java)
AWS CloudFormation
LocalStack
AWS CLI
🔄 Project Workflow
Designed the Spring Boot application architecture.
Implemented the Patient Service with CRUD operations.
Added validation, DTOs, custom exceptions, and centralized error handling.
Integrated PostgreSQL and Dockerized the Patient Service.
Built the Billing Service using gRPC and Protocol Buffers.
Connected Patient Service to Billing Service through gRPC.
Integrated Apache Kafka for event-driven communication.
Developed the Analytics Service as a Kafka consumer.
Implemented API Gateway for routing requests.
Developed Authentication Service with Spring Security and JWT.
Secured APIs using JWT validation filters.
Added OpenAPI documentation.
Wrote integration tests for authentication and patient APIs.
Provisioned cloud infrastructure using AWS CDK.
Created VPC, databases, ECS cluster, MSK cluster, and supporting infrastructure.
Containerized all services and deployed the application locally using LocalStack.
📚 Key Concepts Demonstrated
Microservices Architecture
REST API Development
gRPC Communication
Event-Driven Architecture
Apache Kafka Messaging
JWT Authentication & Authorization
API Gateway Pattern
DTO Pattern
Repository Pattern
Service Layer Pattern
Global Exception Handling
Request Validation
Docker & Containerization
Infrastructure as Code (IaC)
AWS CDK
CloudFormation
LocalStack
Integration Testing
📌 Learning Outcomes

This project demonstrates end-to-end backend application development using modern Java technologies, from designing RESTful microservices and secure APIs to implementing asynchronous communication, containerization, automated testing, and cloud infrastructure provisioning with Infrastructure as Code.
