# Movie Booking System - Help Guide

## Overview

Backend project built using Spring Boot and PostgreSQL.

## Tech Stack

* Java 17 or 21
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven
* Lombok
* Swagger / OpenAPI

## Prerequisites

Install the following:

* JDK 17 or JDK 21
* IntelliJ IDEA
* PostgreSQL
* Maven (optional if using IntelliJ bundled Maven)

## Database Setup

Open PostgreSQL and run:

```sql
CREATE DATABASE test_db;
```

Default credentials used in project:

```properties
username=postgres
password=postgres
```

Update `src/main/resources/application.properties` if your local credentials are different.

## application.properties

Use the following configuration:

```properties
server.port=8080

spring.datasource.url=jdbc:postgresql://localhost:5432/booking_db
spring.datasource.username=postgres
spring.datasource.password=****

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.defer-datasource-initialization=true
spring.sql.init.mode=always
```

## Load Sample Data

Place `data.sql` inside:

```text
src/main/resources/data.sql
```

On application startup, sample data will be inserted.

## How to Run in IntelliJ

1. Open project in IntelliJ IDEA.
2. Wait for Maven dependencies to download.
3. Ensure PostgreSQL service is running.
4. Open `MovieBookingSystemApplication.java`.
5. Click Run.

## Run Using Terminal

From project root:

```bash
mvn spring-boot:run
```

## Verify Startup

Application should start on:

```text
http://localhost:8080
```

## Swagger API Documentation

Open:

```text
http://localhost:8080/swagger-ui/index.html
```

## Main APIs

### Movies

* `POST /api/movies`
* `GET /api/movies`
* `GET /api/movies/{id}`

### Theatres

* `POST /api/theatres`
* `GET /api/theatres`
* `GET /api/theatres/city/{city}`

### Shows

* `POST /api/shows`
* `PUT /api/shows/{id}`
* `DELETE /api/shows/{id}`
* `GET /api/shows/search?movieId=1&city=Bangalore&date=2026-04-22`
* `GET /api/shows/theatre`
* `GET /api/shows/movie`

### Bookings

* `POST /api/bookings`

## Sample Booking Request

```json
{
  "userName": "Santosh",
  "showId": 1,
  "seats": ["A3", "A4", "A5"]
}
```

## Offers Implemented

* 50% discount on third ticket
* 20% discount for afternoon shows (12 PM to 4 PM)

## Common Issues

### Port 8080 Already Used

Change port in `application.properties`.

### Database Connection Failed

Check PostgreSQL is running and credentials are correct.

### Tables Not Created

Verify `spring.jpa.hibernate.ddl-auto=update`.

### data.sql Not Running

Verify:

```properties
spring.jpa.defer-datasource-initialization=true
spring.sql.init.mode=always
```

## Build Jar

```bash
mvn clean package
```

Generated jar will be in:

```text
target/
```

## Future Enhancements

* JWT Security
* Redis seat locking
* Bulk booking
* Cancellation APIs
* Payment gateway integration

## Contact

For local setup issues, check logs in IntelliJ Run Console.
