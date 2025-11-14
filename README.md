Project status:
Step 4: done
Step 5: 5.5 in progress
    5) Deploy to Render / Railway / AWS EC2: I will be using render for now and later EC2

Notes:
-> The app is deployed on docker alongwith postgres, so 2 containers
  - I have the docker compose file
  - docker-compose up will start, -d option at last can also be used to enable in detach mode
  - docker-compose down : to stop container
  - I can go inside postgres with exec to see/modify tables
    docker exec -it springstore-postgres-1 psql -U springstore -d springstore
    # \dt: to show tables
  - http://localhost:8081 for checking adminer postgres console
    System: PostgreSQL
    Server: postgres
    Username: springstore
    Password: ssp
    Database: springstore
-> Next step is to deploy the whole app on render...

Gaps/To learn:
- Hibernate, OnetoMany, ManyToOne

---
# springstore
E-commerce web app using Java, Spring Boot, PostgreSQL

# 🛍️ Springstore — Shopping Application (Java + Spring Boot)

## 💡 Overview
Springstore is a full-featured **shopping backend application** built using **Spring Boot 3**, **PostgreSQL**, and **JWT-based authentication**.  
It supports **user registration/login**, **product management**, **cart & orders**, and **admin features** — all through secure REST APIs.

---

## 🧱 Tech Stack

| Layer | Technology |
|--------|-------------|
| Backend Framework | Spring Boot 3.5.x |
| Language | Java 17 |
| Database | PostgreSQL |
| Security | Spring Security + JWT |
| API Docs | Swagger / Springdoc OpenAPI |
| ORM | Spring Data JPA + Hibernate |
| Build Tool | Maven |

---

## 🗂️ Project Setup

### 1️⃣ Clone the repository
```bash
git clone https://github.com/ShankarJS/springstore.git
cd springstore
```

2️⃣ Configure the database
Update your credentials in src/main/resources/application.properties:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/springstore
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

3️⃣ Run the application
```bash
mvn spring-boot:run
```

Open Swagger UI:

```bash
http://localhost:8080/swagger-ui.html
```

---
🗓️ Project Roadmap (Weekend Plan) 

🧩 Weekend 1 – Setup & Authentication  

- Create Spring Boot project (Maven + Java 17 + PostgreSQL)
- Add dependencies: Web, JPA, PostgreSQL, Security, Validation, Lombok
- Configure database and application properties
- Create User Entity and Repository
- Implement JWT Authentication (JwtUtil, JwtAuthFilter, SecurityConfig)
- Build AuthController with /register and /login
- Add /api/users/me secured endpoint
- Integrate Swagger for API testing

🧩 BASE URL
If running locally:
http://localhost:8080

🧠 Weekend 1 APIs — Authentication Module
1️⃣ Register a New User
Endpoint:
POST /api/auth/register
Headers:
Content-Type: application/json
Body (raw → JSON):
```json
{
"name": "Alice",
"email": "alice@example.com",
"password": "password123"
}
```
Response (201 Created):
{
"message": "User registered successfully",
"user": {
"id": 1,
"name": "Alice",
"email": "alice@example.com"
}
}

After register, you will get a jwt token, which can be used to access secured endpoints

2️⃣ Login to Get JWT Token
Endpoint:
POST /api/auth/login
Headers:
Content-Type: application/json
Body (raw → JSON):
{
"email": "alice@example.com",
"password": "password123"
}
Response (200 OK):
{
"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6..."
}

After login, you will get a jwt token, either this token or the one you got while registering can be used to access secured endpoints but prefer using the recent one
📎 Copy this token — you’ll need it for all protected APIs.

eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYWh1bEBleGFtcGxlLmNvbSIsInJvbGUiOiJST0xFX1VTRVIiLCJpYXQiOjE3NjA5NTc0NDMsImV4cCI6MTc2MTA0Mzg0M30.AgVdGrQ7T43dBh1ZoG2qLd1_4e0A84JdnwfW9749f4k
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYWNoaW5AZXhhbXBsZS5jb20iLCJuYW1lIjoiU2FjaGluIiwicm9sZSI6IlJPTEVfVVNFUiIsImlhdCI6MTc2MTAzNTY5MSwiZXhwIjoxNzYxMTIyMDkxfQ.ycVR07opbbhHQrGEpWvAYXjCEX3RKjaviquDmvYsskA

3️⃣ Get Logged-In User (Protected)
Endpoint:
GET /api/users/me
Headers:
Authorization: Bearer <PASTE_YOUR_TOKEN_HERE>
Example:
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6...
Response (200 OK):
{
"id": 1,
"name": "Alice",
"email": "alice@example.com"
}
✅ Outcome: Register/login users, get and use JWT token for protected routes.

---
🛒 Weekend 2 – Product Management
Create Product entity (id, name, description, price, category, stock)
Implement ProductRepository, ProductService, ProductController

CRUD endpoints:
GET /api/products
GET /api/products/{id}
POST /api/products (admin only)   to be tested
PUT /api/products/{id}            to be tested
DELETE /api/products/{id}         to be tested

Add Role-based Authorization:
ROLE_USER → read-only
ROLE_ADMIN → full access

✅ Outcome: Products can be listed and managed securely.
---

🛍️ Weekend 3 – Cart & Orders  
Goal: Enable users to add to cart and place orders.

Tasks:  
Create Cart and CartItem entities (User ↔ Products)  
Create Order and OrderItem entities (User ↔ Products)

Endpoints:  
/api/cart/add (POST)    http://localhost:8080/api/cart/add?productId=1&quantity=2   working
/api/cart (GET)        JWT token needed   working
/api/orders/place (POST)    JWT Token needed  working
/api/orders (GET)  working

Handle transactional updates — stock decreases after order
Add validations (e.g., product must exist, sufficient stock)
✅ Outcome: Users can add products to cart and place orders.

---
🧑‍💼 Weekend 4 – Admin Dashboard & Reports
 Create admin-only endpoints:

/api/admin/users → list all users
/api/admin/orders → list all orders
/api/admin/stats → total users, total sales, top products
 Secure /api/admin/** endpoints for ROLE_ADMIN

✅ Outcome: Admins can manage users and monitor platform activity.

Status: Weekend 4 done, In dashboard some data like top products was not showing, need to revisit it later

Admin Dashboard Total Orders: 7 Total Users: 8 Total Revenue: 33591.98 Monthly Sales 10: Top Products - - - User Stats Sachin - Orders: 2 Rahul - Orders: 2 Above is the output shown in frontend angular admin dashboard Monthly sales is not showing properly , and below is OrderRepository class, Please check if any change is required, also I remember when you gave me OrderRepository class, you gave this note, what does this mean? Note: MONTH() and YEAR() JPQL functions are supported by many JPA providers but if your provider doesn’t support them, you can use FUNCTION('month', o.orderDate) style or a native query. package com.shankar.springstore.repository; import com.shankar.springstore.dto.MonthlySalesDto; import com.shankar.springstore.dto.TopProductDto; import com.shankar.springstore.dto.UserStatsDto; import com.shankar.springstore.model.Order; import com.shankar.springstore.model.User; import org.springframework.data.jpa.repository.JpaRepository; import org.springframework.data.jpa.repository.Query; import org.springframework.data.repository.query.Param; import java.time.LocalDateTime; import java.util.List; public interface OrderRepository extends JpaRepository<Order, Long> { List<Order> findByUser(User user); @Query("SELECT COUNT(o) FROM Order o") long countAllOrders(); @Query("SELECT COALESCE(SUM(o.totalAmount),0) FROM Order o") double sumAllRevenue(); @Query("SELECT new com.shankar.springstore.dto.MonthlySalesDto(YEAR(o.orderDate), MONTH(o.orderDate), COALESCE(SUM(o.totalAmount),0), COUNT(o)) " + "FROM Order o WHERE o.orderDate >= :from AND o.orderDate <= :to GROUP BY YEAR(o.orderDate), MONTH(o.orderDate) ORDER BY YEAR(o.orderDate), MONTH(o.orderDate)") List<MonthlySalesDto> findMonthlySales(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to); // Top products by quantity sold and revenue @Query("SELECT new com.shankar.springstore.dto.TopProductDto(oi.product.id, oi.product.name, COALESCE(SUM(oi.quantity),0), COALESCE(SUM(oi.price),0)) " + "FROM OrderItem oi WHERE oi.order.orderDate >= :from AND oi.order.orderDate <= :to GROUP BY oi.product.id, oi.product.name ORDER BY SUM(oi.quantity) DESC") List<TopProductDto> findTopProducts(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to); // user stats @Query("SELECT new com.shankar.springstore.dto.UserStatsDto(u.id, u.email, u.name, COUNT(o), COALESCE(SUM(o.totalAmount),0)) " + "FROM Order o JOIN o.user u GROUP BY u.id, u.email, u.name ORDER BY SUM(o.totalAmount) DESC") List<UserStatsDto> findUserStats(); } //Note: MONTH() and YEAR() JPQL functions are supported by many JPA providers but if your provider doesn’t support them, you can use FUNCTION('month', o.orderDate) style or a native query.

---
⚙️ Step 5 – Polishing & Deployment
 1) Add DTOs for clean API responses
 2) Implement Global Exception Handling (@ControllerAdvice)
 3) Enable CORS for frontend integration
 4) Dockerize app (Dockerfile, docker-compose.yml)
 5) Deploy to Render / Railway / AWS EC2

    4) Dockerize app (Dockerfile, docker-compose.yml):
    - docker pull openjdk:26-ea-trixie
    - docker run -it --name rest-demo-setup openjdk:26-ea-trixie bash
      - ls -a
    - docker cp target/rest-demo.jar rest-demo-setup:/tmp/rest-demo.jar
    - docker commit rest-demo-setup rest-demo-image
    - docker run -d -p 8080:8080 --name rest-demo-container rest-demo-image java -jar /tmp/rest-demo.jar
    - docker ps
    - http://localhost:8080
    
    Cleanup optional:  //If you ever want to stop and remove it:
    docker stop rest-demo-container
    docker rm rest-demo-container
    docker rmi rest-demo-image

    To automate image creation, we can use Dockerfile
    refer rest-demo telusko projec for dockerfile
    after creating dockerfile:
    > docker build -t telusko/rest-demo:v3 .
    

✅ Outcome: Ready-to-deploy, production-grade backend service.

---
🚀 Future Enhancements
- Product image upload via AWS S3 or Cloudinary
- Payment gateway integration (Razorpay Sandbox)
- Email notification on order
- React/Angular frontend integration

---
🧑‍💻 Contributors

| Name | Role|
|------|-----|
|Shankarlal Sharma|	Backend Developer|
|[Your Colleague’s Name]|	Developer|
|[Add More Names]|	Contributor|

---

📄 License  
This project is for learning and portfolio purposes.  
Feel free to fork and experiment 🚀

⭐ Don’t forget to star the repo if you like it!