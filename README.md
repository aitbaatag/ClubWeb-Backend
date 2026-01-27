# ClubWeb Backend

A Spring Boot REST API for managing running clubs where users can create clubs, organize events, and discover communities.

## 🚧 Work in Progress

This backend application is currently under active development. More features will be added soon.

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot 4.0.1**
- **Spring Data JPA** - Database access layer
- **Hibernate** - ORM framework
- **PostgreSQL** - Primary database
- **Spring Security** - Authentication & Authorization
- **JWT (JSON Web Tokens)** - Stateless authentication
- **Lombok** - Reduce boilerplate code
- **Bean Validation** - Request validation
- **Maven** - Dependency management

## 📋 Features

### Authentication & Security
- User registration and login
- JWT-based authentication
- Spring Security integration
- Password encryption
- Role-based access control

### Club Management
- Create, read, update, and delete clubs
- Club ownership tracking
- Search clubs by query
- Automatic timestamp tracking (created/updated dates)
- Club information management (title, content, photo URL)

### Event Management
- Create, read, update, and delete events
- Associate events with clubs
- View all events for a specific club
- Event details (name, type, start/end time, photo)

### User Management
- User profiles
- User authentication

## 🚀 Getting Started

### Prerequisites

- **Java 21** or higher
- **Maven 3.6+**
- **PostgreSQL** database

### Database Setup

1. Create a PostgreSQL database:
```sql
CREATE DATABASE rungroop;
CREATE USER springuser WITH PASSWORD 'springpass';
GRANT ALL PRIVILEGES ON DATABASE rungroop TO springuser;
```

2. Update database credentials in `src/main/resources/application.properties` if needed:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/rungroop
spring.datasource.username=springuser
spring.datasource.password=springpass
```

### Running the Application

```bash
# Using Maven wrapper
./mvnw spring-boot:run

# Or build and run the JAR
./mvnw clean package
java -jar target/web-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

## 📡 API Endpoints

### Authentication (`/api/auth`)
- `POST /api/auth/signup` - Register a new user
- `POST /api/auth/login` - Authenticate user

### Clubs (`/api/clubs`)
- `GET /api/clubs` - Get all clubs
- `GET /api/clubs/{id}` - Get club by ID
- `POST /api/clubs/new` - Create a new club (requires authentication)
- `PUT /api/clubs/{id}` - Update club
- `DELETE /api/clubs/{id}/delete` - Delete club
- `GET /api/clubs/search?query={query}` - Search clubs

### Events (`/api/events`)
- `GET /api/events` - Get all events
- `GET /api/events/{id}` - Get event by ID
- `POST /api/events/new` - Create a new event
- `PUT /api/events/{id}` - Update event
- `DELETE /api/events/{id}/delete` - Delete event
- `GET /api/events/club/{clubId}` - Get events for a specific club

### Users (`/api/users`)
- User management endpoints

## 📁 Project Structure

```
src/main/java/com/rungroop/web/
├── controller/     # REST API endpoints
│   ├── AuthController.java
│   ├── ClubController.java
│   ├── EventController.java
│   └── UserController.java
├── dto/           # Data Transfer Objects
│   ├── AuthResponseDto.java
│   ├── ClubDto.java
│   ├── EventDto.java
│   ├── LoginDto.java
│   ├── RegistrationDto.java
│   ├── RoleDto.java
│   └── UserDto.java
├── models/        # JPA entities
│   ├── Club.java
│   ├── Event.java
│   ├── Role.java
│   └── User.java
├── repository/    # Database repositories
│   ├── ClubRepository.java
│   ├── EventRepository.java
│   ├── RoleRepository.java
│   └── UserRepository.java
├── security/      # Security configuration
│   ├── config/
│   └── userdetails/
├── services/      # Business logic layer
│   ├── AuthService.java
│   ├── ClubService.java
│   ├── EventService.java
│   ├── RoleService.java
│   └── impl/
└── WebApplication.java  # Main application class
```

## 🔧 Configuration

### Application Properties

Key configurations in `application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/rungroop
spring.datasource.username=springuser
spring.datasource.password=springpass

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false

# JWT Configuration
jwt.secret=YOUR_SECRET_KEY
jwt.expiration=3600000  # 1 hour in milliseconds
```

### CORS Configuration

The API allows cross-origin requests from `http://localhost:5173` for frontend development.

## 🧪 Testing

The project includes test files for API testing:
- `src/test/http/club.http` - Club API tests
- `src/test/http/event.http` - Event API tests
- `src/test/http/user.http` - User API tests
- `src/test/http/security-test.http` - Security tests

## 🔐 Security

- Passwords are encrypted using BCrypt
- JWT tokens for stateless authentication
- Role-based access control (ROLE_USER, ROLE_ADMIN)
- Authenticated endpoints require valid JWT token
- Club ownership validation (users can only modify their own clubs)

## 🔜 Upcoming Features

- JWT integration in authentication flow
- Club membership management
- Join/leave club functionality
- Enhanced search and filtering
- User profile management
- File upload for club/event photos
- Email notifications
- And more...

## 📝 License

This project is currently under development.

## 👨‍💻 Development

To contribute or run in development mode:

1. Clone the repository
2. Set up PostgreSQL database
3. Configure `application.properties`
4. Run `./mvnw spring-boot:run`
5. Access the API at `http://localhost:8080`

## 🐛 Known Issues

- JWT authentication flow is under implementation
- File upload for photos not yet implemented (currently using URLs)

---

**Note:** This is a learning/portfolio project showcasing Spring Boot, REST API, Spring Security, and JWT authentication.

