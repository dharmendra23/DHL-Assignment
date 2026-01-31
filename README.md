User Management Service
Overview

A User Management application built using Kotlin + Spring Boot providing REST APIs to create, read, update, and delete users.
A React-based UI is included to demonstrate end-to-end functionality.


Technology Stack

Backend
Kotlin, Spring Boot
Spring Web, Spring Data JPA
H2 Database
Swagger (OpenAPI)
JUnit, Mockito

Frontend
React (JavaScript framework)
Vite, Fetch API

Architecture
React UI → REST APIs → Spring Boot Service → Database


REST API Endpoints

| Method | Endpoint          |
| ------ | ----------------- |
| POST   | `/api/users`      |
| GET    | `/api/users`      |
| GET    | `/api/users/{id}` |
| PUT    | `/api/users/{id}` |
| DELETE | `/api/users/{id}` |


Validation
Name: required, 2–50 characters
Email: required, valid format
Implemented using Bean Validation


Database
H2 in-memory database (default)
Auto schema generation
Easily switchable to MySQL/PostgreSQL

Swagger API Docs
Swagger UI is enabled for API testing:
http://localhost:8080/swagger-ui/index.html

Frontend
A React UI demonstrates:
Create user
View users
Update user
Delete user
Frontend runs at: http://localhost:5173


Kafka / Event-Based Impact Analysis
If integrated with Kafka:
Enables asynchronous processing
Improves scalability and decoupling
Supports audit, notification, analytics use cases
Introduces eventual consistency and retry handling

Testing
Unit tests written using JUnit and Mockito
Focus on service-layer logic


How to Run
Backend
./gradlew bootRun


Frontend
npm install
npm run dev




Author
Dharmendra Bharti – Software Engineer
