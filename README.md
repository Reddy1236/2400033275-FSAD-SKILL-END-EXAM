# Spring Boot Hibernate Supplier Project

Package: `com.klef.fsad.exam`

Required classes:

- `Supplier`
- `ClientDemo`

Database name: `fsadendexam`

## MySQL Commands

Run this in MySQL Workbench if the table is not already created:

```sql
CREATE DATABASE fsadendexam;

USE fsadendexam;

CREATE TABLE supplier (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    date DATE,
    status VARCHAR(50),
    PRIMARY KEY (id)
);

SELECT * FROM supplier;
```

## Run Spring Boot

```bash
cd /Users/avinashreddypadala/Desktop/FSAD-END-LAB
mvn spring-boot:run
```

The app runs on:

```text
http://localhost:8081
```

## Postman

Insert supplier:

```http
POST http://localhost:8081/suppliers
Content-Type: application/json
```

Body:

```json
{
  "name": "Avinash Traders",
  "description": "Electrical goods supplier",
  "date": "2026-05-02",
  "status": "Active"
}
```

Update supplier name/status by ID:

```http
PUT http://localhost:8081/suppliers/update?id=1
Content-Type: application/json
```

Body:

```json
{
  "name": "Avinash Suppliers",
  "status": "Inactive"
}
```

View all suppliers:

```http
GET http://localhost:8081/suppliers
```
