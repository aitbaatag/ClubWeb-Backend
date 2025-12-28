# ClubWeb Backend

A Spring Boot REST API for managing clubs where users can post their clubs, join existing clubs, and discover communities.

## 🚧 Work in Progress

This backend application is currently under active development. More features will be added soon.

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot 4.0.1**
- **Spring Data JPA**
- **Hibernate**
- **Lombok**
- **Maven**

## 📋 Current Features

- Create, read, update, and delete clubs
- Club information management (title, content, photo)
- Automatic timestamp tracking (created/updated dates)

## 🚀 Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6+
- Database (configured in `application.properties`)

### Running the Application

```bash
# Using Maven wrapper
./mvnw spring-boot:run

# Or build and run the JAR
./mvnw clean package
java -jar target/web-0.0.1-SNAPSHOT.jar
```

## 📁 Project Structure

```
src/main/java/com/rungroop/web/
├── controller/     # REST API endpoints
├── dto/           # Data Transfer Objects
├── models/        # JPA entities
├── repository/    # Database repositories
└── services/      # Business logic layer
```

## 🔜 Upcoming Features

- User authentication and authorization
- Club membership management
- Join/leave club functionality
- Search and filter clubs
- User profiles
- And more...

## 📝 License

This project is currently under development.

