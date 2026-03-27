# AI-Powered Employee Service

## 🚀 Overview

A production-style Spring Boot backend system integrating AI capabilities with caching, event streaming, and fault tolerance.

This project demonstrates real-world backend architecture using:

* REST APIs
* OpenAI integration
* Redis caching
* Kafka event streaming
* MySQL persistence
* Resilience4j (Retry + Circuit Breaker)

---

## 🧠 Architecture

Client → REST API → Service Layer
          ↓
       Redis (Cache)
        ↓
       OpenAI API
        ↓
       MySQL (Persistence)
        ↓
       Kafka (Event Streaming)

---

## 🛠 Tech Stack

* Java 21
* Spring Boot 3
* Spring Data JPA
* MySQL (Docker)
* Redis (Docker)
* Kafka (Docker)
* OpenAI API
* Resilience4j
* Lombok
* Maven

---

## 📦 Features

### 👨‍💼 Employee APIs

* POST `/employees` → Create employee
* GET `/employees` → Fetch employees

---

### 🤖 AI APIs

* GET `/ai/ask?query=` → Get AI response
* GET `/ai/logs?page=0&size=5` → Paginated logs

---

### ⚡ System Features

* Redis caching (AI response optimization)
* Kafka event streaming (async processing)
* MySQL persistence (AI query logs)
* Global exception handling
* Standard API response wrapper
* Pagination & sorting
* Resilience4j:

    * Retry mechanism
    * Circuit breaker (fault tolerance)

---

## 🔄 Request Flow

1. User calls `/ai/ask`
2. System checks Redis cache
3. If cache miss → calls OpenAI API
4. Response stored in MySQL
5. Event sent to Kafka
6. Response returned to user

---

## 🐳 Docker Setup (Recommended)

### Start all services:

```bash
docker-compose up -d
```

This will start:

* MySQL (port 3307)
* Redis (port 6379)
* Kafka (port 9092)

---

## ⚙️ Application Setup

### Run Application

```bash
mvn clean install
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

---

## 🧪 API Testing

### Example:

```http
GET http://localhost:8080/ai/ask?query=What is Kafka?
```

---

## 🗄 Verify Data

### MySQL

```sql
SELECT * FROM ai_query_log;
```

---

### Redis

```bash
docker exec -it redis redis-cli
GET "your-query"
```

---

### Kafka Logs

Check application logs:

```text
Sent to Kafka: ...
Received from Kafka: ...
```

---

## 🔥 Fault Tolerance (Resilience4j)

* Retry failed OpenAI calls (3 attempts)
* Circuit breaker prevents cascading failures
* Fallback response if AI is unavailable

---

## 🚀 Future Improvements

* Rate limiting (Redis)
* Swagger API documentation
* Dockerized Spring Boot app
* Monitoring (Spring Actuator)
* Multi-consumer Kafka architecture

---

## 🎯 Key Highlights

* End-to-end backend system
* Event-driven architecture
* Scalable & fault-tolerant design
* Production-ready patterns

---

## 👨‍💻 Author

Prayag Raj Sharma
