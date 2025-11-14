# ---------- build stage ----------
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /workspace

# copy only what we need to cache dependencies
COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw -B -q dependency:go-offline

# copy source & build
COPY src ./src
RUN ./mvnw -B -DskipTests package

# ---------- runtime stage ----------
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# create a non-root user (good practice)
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# copy jar from builder stage (update artifact name if different)
COPY --from=build /workspace/target/*.jar app.jar

# healthcheck (optional)
HEALTHCHECK --interval=30s --timeout=5s --start-period=10s \
  CMD wget -qO- --spider http://localhost:8080/actuator/health || exit 1

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
