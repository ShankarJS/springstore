# Springstore -  Shopping Application

### Note: Its only backend restapi, frontend to be build and is in progress

## Overview
Springstore is a full-featured **shopping backend application** built using **Spring Boot 3**, **PostgreSQL**, and **JWT-based authentication**.  
It supports **user registration/login**, **product management**, **cart & orders**, and **admin features** — all through secure REST APIs.

## Tech Stack

- Layer-Technology   
- Spring boot 3.5.x   
- Java 17   
- Postgresql 15  
- Spring Security + JWT   
- Swagger/Springdoc OpenAPI  
- ORM: Spring Data JPA + Hibernate  
- Maven
- Backend Deployed on render
- DB deployed on neon
- Frontend(react) deployed on vercel: https://springstore-frontend.vercel.app/
---
## Project Setup
### 1. Clone the repository
```bash
git clone https://github.com/ShankarJS/springstore.git
cd springstore
```

### 2. Configure the database
Update your credentials in src/main/resources/application.properties:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/springstore
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Run the application
```bash
mvn spring-boot:run
```

Open Swagger UI:

```bash
http://localhost:8080/swagger-ui.html
```
---

### License  
This project is for learning and portfolio purposes.  
Feel free to fork and experiment

---
# Notes:
- The app is deployed on docker alongwith postgres, so 2 containers
- I have the docker compose file
- docker-compose up will start, -d option at last can also be used to enable in detach mode
- docker-compose down : to stop container
- I can go inside postgres with exec to see/modify tables
  docker exec -it springstore-postgres-1 psql -U springstore -d springstore  
  \dt: to show tables
- http://localhost:8081 for checking adminer postgres console
  System: PostgreSQL
  Server: postgres
  Username: springstore
  Password: ssp
  Database: springstore

## Gaps/To learn:
- Hibernate, OnetoMany, ManyToOne
---
## Message for frontend Developer   
📌 What you need to build Springstore frontend  
Backend base URL: https://springstore-backend.onrender.com  

Swagger:  
https://springstore-backend.onrender.com/swagger-ui.html

You will use these APIs:    
### Auth  
POST /api/auth/login  
POST /api/auth/register 

### Products  
GET /api/products  
GET /api/products/:id  
POST /api/products (admin)  
PUT /api/products/:id (admin)  
DELETE /api/products/:id (admin)  

### Orders  
POST /api/orders/place  
GET /api/orders  
GET /api/orders/:id  

### User  
GET /api/users/me  

### Authentication  
After login, save JWT token in localStorage  
Pass token in Authorization header for logged-in requests  
Admin role must be checked before allowing Add/Edit/Delete product  

### Pages to build
Home (product listing)  
Product details  
Login  
Signup  
Cart  
Checkout  
Profile  
My Orders  
Admin Dashboard     
Add Product  
Edit Product  

### Other Notes
Cart stored in localStorage  
Show errors from backend  
I will provide admin credentials  