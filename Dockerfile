# Step 1: Use a lightweight Java image
FROM eclipse-temurin:17-jdk-alpine

# Step 2: Set working directory
WORKDIR /app

# Step 3: Copy Maven build files first for dependency caching
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Step 4: Download dependencies
RUN ./mvnw dependency:go-offline

# Step 5: Copy actual source code
COPY src ./src

# Step 6: Package the app
RUN ./mvnw clean package -DskipTests

# Step 7: Run the jar
CMD ["java", "-jar", "target/springstore-0.0.1-SNAPSHOT.jar"]

# Step 8: Expose port
EXPOSE 8080
