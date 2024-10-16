# Library Book Management System

A comprehensive library book management system built with Spring Boot, featuring full CRUD operations and an H2 in-memory database.

## Table of Contents

- [About the Project](#about-the-project)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
  - [Get All Books](#get-all-books)
  - [Get a Book by ID](#get-a-book-by-id)
  - [Create a New Book](#create-a-new-book)
  - [Update an Existing Book](#update-an-existing-book)
  - [Delete a Book](#delete-a-book)
- [Database Configuration](#database-configuration)
- [H2 Console](#h2-console)
- [Project Structure](#project-structure)
- [License](#license)

## About the Project

The **Library Book Management System** is a Spring Boot application designed to manage library book data. It allows users to create, read, update, and delete book records through a REST API. The application uses H2 as an in-memory database and provides endpoints for managing book information.

## Technologies Used

- Java 17
- Spring Boot 2.7.2
- H2 Database (In-Memory)
- Maven
- RESTful API
- JSON

## Prerequisites

Ensure you have the following installed on your local development machine:

- **Java** (JDK 17 or later)
- **Maven** (3.8.1 or later)
- **Git** (for cloning the repository)

## Getting Started

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/Scavenger233/REST-API-Project.git
   cd REST-API-Project
   ```

2. Build the project using Maven:

   ```bash
   mvn clean install
   ```

### Running the Application

You can run the application using one of the following methods:

- **Using Maven**:
  
  ```bash
  mvn spring-boot:run
  ```

- **Using an IDE (e.g., IntelliJ IDEA)**:
  - Open the project in the IDE.
  - Run the `SprintBootCrudApplication.java` file as a Spring Boot Application.

## API Documentation

### Get All Books

- **Endpoint**: `GET /api/library/{bookName}/books`
- **Description**: Retrieves a list of all books with the given book name.

### Get a Book by ID

- **Endpoint**: `GET /api/library/{bookName}/books/{id}`
- **Description**: Retrieves a specific book by its ID.

### Create a New Book

- **Endpoint**: `POST /api/library/{bookName}/books`
- **Request Body**:

  ```json
  {
    "description": "A detailed description of the book"
  }
  ```

- **Description**: Creates a new book with the provided description.

### Update an Existing Book

- **Endpoint**: `PUT /api/library/{bookName}/books/{id}`
- **Request Body**:

  ```json
  {
    "description": "Updated description of the book"
  }
  ```

- **Description**: Updates the description of the specified book.

### Delete a Book

- **Endpoint**: `DELETE /api/library/{bookName}/books/{id}`
- **Description**: Deletes the specified book by its ID.

## Database Configuration

The application uses an H2 in-memory database. Configuration settings are located in the `application.properties` file.

- **H2 Console**: Enabled by default
- **Database URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: *(leave empty)*

## H2 Console

You can access the H2 database console at the following URL:

```
http://localhost:8080/h2-ui
```

To log in:
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: *(leave empty)*

## Project Structure

```
src
├── main
│   ├── java
│   │   └── com
│   │       └── library
│   │           ├── controller
│   │           ├── model
│   │           ├── repository
│   │           └── service
│   └── resources
│       ├── application.properties
│       └── static
└── test
```

- **Controller**: Handles the REST API endpoints.
- **Model**: Defines the `Book` entity.
- **Repository**: Interface for database operations.
- **Service**: Business logic related to book management.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
