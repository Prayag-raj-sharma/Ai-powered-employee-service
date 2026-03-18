# AI Employee Service

## 🚀 Overview
Spring Boot backend service with:
- Employee CRUD APIs
- AI query endpoint
- AI query logging in MySQL
- Pagination & sorting
- Standard API response wrapper

## 🛠 Tech Stack
- Java 21
- Spring Boot
- Spring Data JPA
- MySQL (Docker)
- Lombok

## 📦 Features

### Employee APIs
- POST /employees
- GET /employees

### AI APIs
- GET /ai/ask?query=
- GET /ai/logs

## 🐳 Docker Setup

Run MySQL:

```bash
docker run -d --name mysql-aipoc -p 3307:3306 \
-e MYSQL_ROOT_PASSWORD=root \
-e MYSQL_DATABASE=aipoc \
-e MYSQL_USER=appuser \
-e MYSQL_PASSWORD=appuser \
mysql:8
