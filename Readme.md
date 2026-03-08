# Inventory Management System - Backend API

A robust, RESTful API built with Spring Boot to manage product inventory. This service handles data persistence, business logic, validation, and error handling for the Inventory Management System.

## Tech Stack & Dependencies
* **Java 21** (Amazon Corretto)
* **Spring Boot 3.x**
    * Spring Web (REST Controllers)
    * Spring Data JPA (Hibernate/ORM)
    * Spring Boot Validation (DTO constraints)
* **PostgreSQL** (Relational Database)
* **Lombok** (Boilerplate reduction)

## ⚙️ Environment Configuration
Before running the application, you must configure your database connection. Open `src/main/resources/application.properties` and update the following environment variables with your PostgreSQL credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/[YOUR_DB_NAME]
spring.datasource.username=your_postgres_username
spring.datasource.password=your_postgres_password
spring.jpa.hibernate.ddl-auto=update

```
## Setup Instructions

1-Ensure Java 17 and PostgreSQL are installed on your machine.
2-Create a new PostgreSQL database named inventory_db.
3-Clone this repository.
4-Open the project in IntelliJ IDEA (or your preferred IDE).
{IMPORTANT: if using IntelliJ, use the run command : ./mvnw clean spring-boot:run }
5-Allow Maven to download all required dependencies.
6-Run the InventoryApplication.java main class.
7-The server will start on http://localhost:8080.


## Database Schema

```Table: products
id (Primary Key, BigInt, Auto-incremented)
name (Varchar, Not Null) - name of the product.
sku (Varchar, Unique, Not Null) - Stock Keeping Unit barcode identifier.
price (Numeric/Decimal, Not Null) - Cost .
quantity (Integer, Not Null) - Current stock level.
description (Text) - Optional .
created_at (Timestamp) - Automatically generated via @CreationTimestamp.
modified_at (Timestamp) - Automatically updated via @UpdateTimestamp.
```

## Endpoints

1- / ,method -GET, get all products

2- /{id} ,method -GET, get product by id

3- / ,method -POST, create product
    ,Request Body :- {
                    "name": "Mechanical Keyboard",
                    "sku": "KEY-002",
                    "price": 89.50,
                    "quantity": 15,
                    "description": "Clicky keyboard"
                    }

4- /{id} ,method -PUT, update product
,Request Body - same as POST

5- /{id} ,method -DELETE, delete product

## NOTE:

If errors encountered in IntelliJ use the run command below:
./mvnw clean spring-boot:run
