# ========================================
# Stage 1: Build Stage
# ========================================
FROM maven:3.9-eclipse-temurin-21-alpine AS builder

# Tạo thư mục làm việc
WORKDIR /app

# Copy pom.xml trước để cache dependencies
COPY pom.xml ./

# Download dependencies (layer này sẽ được cache nếu pom.xml không thay đổi)
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build application (skip tests để build nhanh hơn)
RUN mvn clean package -DskipTests

# ========================================
# Stage 2: Runtime Stage
# ========================================
FROM eclipse-temurin:21-jre-alpine

# Cài đặt các package cần thiết
RUN apk add --no-cache tzdata

# Set timezone (tùy chọn, mặc định là UTC)
ENV TZ=Asia/Ho_Chi_Minh

# Tạo user non-root để chạy ứng dụng (bảo mật hơn)
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Tạo thư mục làm việc
WORKDIR /app

# Copy JAR file từ build stage
COPY --from=builder /app/target/*.jar app.jar

# Expose port (Render sẽ tự động map với PORT environment variable)
EXPOSE 8080

# Health check (optional nhưng recommended)
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Set JVM options tối ưu cho container
ENV JAVA_OPTS="-Xmx512m -Xms256m -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# Run application với production profile
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=prod -jar app.jar"]
