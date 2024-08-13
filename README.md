# Book Library Project

## Description

This is a study project developed using Spring MVC. It is a small web application that allows you to register readers and books, assign books to people, and manage their returns.

The initial task was taken from a 2017 training course and used older versions of technologies, such as Java 1.8, Spring 5, Tomcat 9, and `javax` dependencies.

The project was created from scratch based on the course recommendations and thoroughly tested. Subsequently, it was refactored to use the latest versions of technologies, including Java 17, Spring 6, Tomcat 10, and others. Maven is used for project build and management. Spring Boot is not part of this project.

A demonstration video of the project is available here: 
https://youtu.be/q8MYlNFh2_g (eng)
https://youtu.be/UBrctgjSqhg (rus)

## Features

- Register and manage readers
- Register and manage books
- Assign books to readers
- Return books

## Technology Stack

- **Java**: 17
- **Spring MVC**: 6
- **ThymeLeaf**: For server-side templating
- **Tomcat**: 10
- **JDBCTemplate**: For simplifying database interactions
- **PostgreSQL**: For database management
- **Maven**: For project management and build



## Project Structure

The SQL queries for creating tables and populating them with example data can be found in the `src/main/resources/sqlQueries` file.

## Running the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/pu6karev/LibrarySpringMVC.git

2. Create tables and populate them with data using the SQL queries from the `src/main/resources/sqlQueries` file.
3. Configure Tomcat server from 'Run → Edit Configurations' (IntelliJ Idea).
4. Run the project. By default, it will be accessible at:

   'http://localhost:8080/people'
   'http://localhost:8080/books'

