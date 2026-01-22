# Sparta Global Academy – API
dsbsd
## Project Overview

This project is a production ready API built using Spring Boot for managing the Sparta Global Academy domain. The API allows administrators, trainers, and trainees to manage courses, trainers, and trainees through our endpoints.

The application was developed as a one sprint group project using Agile/Scrum practices, GitHub collaboration, DTO based service layers, unit testing, and OpenAPI documentation.

---

## Domain Model

The API is built around three core resources:

* **Courses** – Represent academy courses
* **Trainers** – Represent trainers assigned to courses
* **Students** – Represent trainees enrolled on courses

Relationships between these entities allow:

* Trainers to be assigned to courses
* Trainees to enrol on and be removed from courses

---

## Tech Stack

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* H2 / MySQL (configurable)
* JUnit 5 & Mockito
* springdoc-openapi (Swagger)
* Maven
* GitHub Actions (CI)

---

## Architecture

The application follows a layered architecture:

* **Controller Layer** – Handles HTTP requests and responses
* **Service Layer** – Contains business logic and validation
* **Repository Layer** – Handles database interaction using JPA
* **DTOs** – Used to transfer data safely between layers

This separation of concerns improves maintainability, testability, and scalability.

---

## Features

* Full CRUD operations for:

    * Courses
    * Trainers
    * Students
* Course enrolment and removal
* Trainer course assignment
* Custom repository methods (e.g. filtering by name)
* DTO based request and response handling
* Validation and meaningful HTTP response codes
* Unit tests for service layer logic

---

## API Documentation (Swagger)

The API is fully documented using Swagger.

Once the application is running, access:

* **Swagger UI:** `http://localhost:8091/swagger-ui.html`
* **OpenAPI Docs:** `http://localhost:8091/h2-console`

Swagger provides:

* Endpoint descriptions
* Request/response examples
* HTTP response codes

---

## Testing

* Unit tests are written using JUnit 5 and Mockito
* Service layer logic is tested in isolation by mocking repositories
* Tests follow a Given–When–Then structure
* Edge cases such as invalid IDs and empty results are covered

---

## CI Pipeline

A GitHub Actions pipeline is configured to:

* Run tests automatically on push and pull requests
* Prevent broken code from being merged

This ensures code quality and consistency across the team.

---

## Team Collaboration

* Version control with GitHub
* Feature-branch workflow
* Pull requests with reviews
* Trello board for task tracking

### Trello Board Columns

* Project Backlog
* Sprint Backlog
* In Progress
* In Review
* Completed
* Notes (Definition of Done)

---

## Running the Application

### Prerequisites

* Java 17+
* Maven

### Steps

1. Clone the repository
2. Open the project in your IDE
3. Run the application:

   ```bash
   mvn spring-boot:run
   ```
4. Access Swagger UI to explore endpoints

---

## Definition of Done

A user story is considered complete when:

* The endpoint is implemented and functional
* DTOs are used correctly
* Validation and error handling are in place
* Unit tests are written and passing
* Swagger documentation is complete
* Code is reviewed and merged into the main branch

---

## Lessons Learned

* Importance of clear user stories and acceptance criteria
* Benefits of DTOs in API design
* Writing tests early improves confidence
* Communication is critical in short sprints

---
