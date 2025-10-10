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
2️⃣ Configure the database
Update your credentials in src/main/resources/application.properties:

properties
Copy code
spring.datasource.url=jdbc:postgresql://localhost:5432/springstore
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
3️⃣ Run the application
bash
Copy code
mvn spring-boot:run
Open Swagger UI:

bash
Copy code
http://localhost:8080/swagger-ui.html
🗓️ Project Roadmap (Weekend Plan)
🧩 Weekend 1 – Setup & Authentication
 Create Spring Boot project (Maven + Java 17 + PostgreSQL)

 Add dependencies: Web, JPA, PostgreSQL, Security, Validation, Lombok

 Configure database and application properties

 Create User Entity and Repository

 Implement JWT Authentication (JwtUtil, JwtAuthFilter, SecurityConfig)

 Build AuthController with /register and /login

 Add /api/users/me secured endpoint

 Integrate Swagger for API testing

✅ Outcome: Register/login users, get and use JWT token for protected routes.

🛒 Weekend 2 – Product Management
 Create Product entity (id, name, description, price, category, stock)

 Implement ProductRepository, ProductService, ProductController

 CRUD endpoints:

GET /api/products

GET /api/products/{id}

POST /api/products (admin only)

PUT /api/products/{id}

DELETE /api/products/{id}

 Add Role-based Authorization:

ROLE_USER → read-only

ROLE_ADMIN → full access

✅ Outcome: Products can be listed and managed securely.

🛍️ Weekend 3 – Cart & Orders
 Create Cart, CartItem, Order, OrderItem entities

 Endpoints:

POST /api/cart/add

GET /api/cart

POST /api/orders/place

GET /api/orders

 Handle stock deduction on order placement

✅ Outcome: Users can add products to cart and place orders.

🧑‍💼 Weekend 4 – Admin Dashboard & Reports
 Create admin-only endpoints:

/api/admin/users → list all users

/api/admin/orders → list all orders

/api/admin/stats → total users, total sales, top products

 Secure /api/admin/** endpoints for ROLE_ADMIN

✅ Outcome: Admins can manage users and monitor platform activity.

⚙️ Weekend 5 – Polishing & Deployment
 Add DTOs for clean API responses

 Implement Global Exception Handling (@ControllerAdvice)

 Enable CORS for frontend integration

 Dockerize app (Dockerfile, docker-compose.yml)

 Deploy to Render / Railway / AWS EC2

✅ Outcome: Ready-to-deploy, production-grade backend service.

🚀 Future Enhancements
 Product image upload via AWS S3 or Cloudinary

 Payment gateway integration (Razorpay Sandbox)

 Email notification on order

 React/Angular frontend integration

🧑‍💻 Contributors
Name	Role
Shankarlal Sharma	Backend Developer
[Your Colleague’s Name]	Developer
[Add More Names]	Contributor

🏁 API Testing
JWT Auth Flow:

Register → POST /api/auth/register

Login → POST /api/auth/login

Copy JWT from login response

Add to headers:

makefile
Copy code
Authorization: Bearer <your_token>
Access secured endpoint /api/users/me

📄 License
This project is for learning and portfolio purposes.
Feel free to fork and experiment 🚀

⭐ Don’t forget to star the repo if you like it!