# Sparta Global Academy – API

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

## Containerising the Application

The application can be containerised and run locally using Docker.

### Prerequisites

* Docker Desktop installed and running
* A Docker account

### Steps

1. Download Docker Desktop, create an account, and complete the installation steps.

2. Clone the repository.

3. Open your command-line interface (CLI) and navigate to the `SpartaAcademyAPI` directory.

   Note: The `Dockerfile` is located in this directory. Review it to understand the actions performed during the build process.

4. Build the Docker image:

   ```text
   docker build -t spa_api .
   ```

   This command reads instructions from the Dockerfile and builds an image for the SpartaAcademyAPI application.

5. Run the Docker container:

   ```text
   docker run -d -p 8080:8080 -p 8081:8081 spa_api
   ```

   This runs the container in detached mode, mapping local ports 8080 and 8081 to the container.

6. Open your browser and navigate to:

   ```text
   http://localhost:8080/
   ```

### Troubleshooting

If you encounter issues during setup:

1. Open Docker Desktop.
2. Navigate to the **Builds** section.
3. Open the build for `SpartaAcademyAPI`.
4. Review any error logs displayed.
5. Report errors to your Academy Trainer for assistance.

### Stopping the Container

1. List running containers:

   ```text
   docker ps
   ```

2. Stop the container:

   ```text
   docker stop <container_id_or_name>
   ```

   Example:

   ```text
   docker stop abcdef123456
   docker stop my_container
   ```

3. Verify the container has stopped by running `docker ps` again.

Optional cleanup:

```text
docker rm <container_id_or_name>
```

---

## Deployment to Render

The application can be deployed using Render.

### Steps

1. Create an account on Render.
2. From the Render dashboard, create a new **Web Service**.
3. Select **GitHub** as the deployment source.
4. Connect the repository containing this application.
5. Configure the service settings as required.
6. Deploy the service.

Once deployed, Render will automatically build and run the application from the repository.

---

## CI/CD Pipelines

### Run JUnit & API Tests Pipeline

This pipeline runs on pull requests targeting the `master` branch and performs the following:

* Runs unit tests
* Runs API tests using RestAssured
* Automatically merges the pull request if all checks pass

The pipeline triggers automatically when a pull request is opened or updated against `master`.

### Deploy to Render Instance Pipeline

This pipeline runs on pushes to the `master` branch and performs the following:

* Runs unit tests
* Runs API tests
* Triggers a Render deployment using a deploy hook

To run this pipeline:

1. Merge a pull request into the `master` branch.
2. GitHub Actions will automatically execute the workflow.
3. If all tests pass, the Render deploy hook is triggered and the application is deployed.

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

 Code is reviewed and merged into the main branch

---

