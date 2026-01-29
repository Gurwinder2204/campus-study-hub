# 🎓 Campus Study Hub

A modern, full-stack web application for managing and sharing educational resources for Computer Science students. Built with Spring Boot, Thymeleaf, and PostgreSQL.

> 📝 **Note**: Originally developed with MySQL, later migrated to PostgreSQL for cloud deployment on Render.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.2-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)

## ✨ Features

- **📚 Semester-wise Organization** - Browse subjects organized by 8 semesters
- **📄 PDF Notes** - Upload, download, and manage study notes
- **📝 Question Papers** - Access previous year question papers with year filtering
- **🎥 Video Tutorials** - Curated YouTube video links with thumbnails
- **🔍 Search** - Find subjects quickly across all semesters
- **👤 User Authentication** - Secure login with Spring Security
- **🛡️ Admin Panel** - Dedicated admin dashboard for content management
- **📱 Responsive Design** - Beautiful dark-themed UI that works on all devices

## 🛠️ Tech Stack

| Layer | Technology |
|-------|------------|
| Backend | Spring Boot 3.2.2, Spring Security, Spring Data JPA |
| Frontend | Thymeleaf, Bootstrap 5, Bootstrap Icons |
| Database | PostgreSQL (Render) |
| Build Tool | Maven |
| Java Version | Java 17 |
| Deployment | Render |

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

- **Java 17** or higher ([Download](https://adoptium.net/))
- **PostgreSQL 14+** ([Download](https://www.postgresql.org/download/))
- **Maven 3.6+** (optional - wrapper included)
- **Git** (for cloning)

## 🚀 Quick Start

### 1. Clone the Repository

```bash
git clone https://github.com/Gurwinder2204/campus-study-hub.git
cd campus-study-hub
```

### 2. Configure Database

Create a PostgreSQL database:

```sql
CREATE DATABASE campus_study_hub;
```

### 3. Configure Application Properties

Copy the example configuration file:

```bash
cp src/main/resources/application-example.properties src/main/resources/application.properties
```

Edit `application.properties` with your credentials:

```properties
# Database Configuration (PostgreSQL)
spring.datasource.url=jdbc:postgresql://localhost:5432/campus_study_hub
spring.datasource.username=YOUR_POSTGRES_USERNAME
spring.datasource.password=YOUR_POSTGRES_PASSWORD

# Admin User Configuration (CHANGE BEFORE FIRST RUN!)
app.admin.email=admin@campus.com
app.admin.password=YOUR_SECURE_PASSWORD
app.admin.name=Campus Admin
```

> ⚠️ **Security Warning**: Never commit `application.properties` with real credentials. It's already in `.gitignore`.

### 4. Run the Application

**Windows:**

```bash
.\mvnw.cmd spring-boot:run
```

**Linux/Mac:**

```bash
./mvnw spring-boot:run
```

### 5. Access the Application

Open your browser and navigate to:

| URL | Description |
|-----|-------------|
| <http://localhost:8080> | Login Page |
| <http://localhost:8080/dashboard> | User Dashboard |
| <http://localhost:8080/admin> | Admin Panel |

## 🔐 Default Admin Credentials

On first startup, an admin account is created automatically using the credentials in `application.properties`:

| Property | Default Value |
|----------|---------------|
| Email | `admin@campus.com` |
| Password | (set in application.properties) |

> **Important**: Change the password in `application.properties` before running the application!

## 📁 Project Structure

```
campus-study-hub/
├── src/main/java/com/campusstudyhub/
│   ├── config/          # Security & web configuration
│   ├── controller/      # MVC controllers
│   ├── dto/             # Data Transfer Objects
│   ├── entity/          # JPA entities
│   ├── repository/      # Spring Data repositories
│   ├── service/         # Business logic services
│   └── DataLoader.java  # Initial data seeder
├── src/main/resources/
│   ├── templates/       # Thymeleaf HTML templates
│   ├── application.properties        # (not committed)
│   └── application-example.properties # Template config
├── uploads/             # Uploaded files directory
├── Dockerfile           # Production Docker config
└── pom.xml              # Maven dependencies
```

## ⚙️ Configuration Options

| Property | Description | Default |
|----------|-------------|---------|
| `server.port` | Server port | `8080` |
| `app.upload.dir` | Upload directory | `uploads` |
| `app.upload.max-file-size` | Max file size (bytes) | `10485760` (10MB) |
| `app.dataloader.enabled` | Enable data seeding | `true` |
| `app.admin.email` | Admin email | `admin@campus.com` |
| `app.admin.password` | Admin password | (must be set) |
| `app.admin.name` | Admin display name | `Campus Admin` |

## 🔒 Security Notes

1. **Never commit credentials** - `application.properties` is in `.gitignore`
2. **Change default passwords** - Update admin password before first run
3. **Use environment variables** - For production/Render deployment:

   ```properties
   spring.datasource.url=${DB_URL}
   spring.datasource.username=${DB_USERNAME}
   spring.datasource.password=${DB_PASSWORD}
   app.admin.password=${APP_ADMIN_PASSWORD}
   ```

4. **Passwords are hashed** - All passwords are stored using BCrypt

## 🚀 Render Deployment

This project is configured for deployment on Render with PostgreSQL:

1. Create a new **Web Service** on Render
2. Connect your GitHub repository
3. Set build command: `./mvnw clean package -DskipTests`
4. Set start command: `java -jar target/campus-study-hub-1.0.0.jar`
5. Add a **PostgreSQL** database on Render
6. Set environment variables:
   - `DB_URL` - PostgreSQL connection URL from Render
   - `DB_USERNAME` - Database username
   - `DB_PASSWORD` - Database password
   - `APP_ADMIN_EMAIL` - Admin email
   - `APP_ADMIN_PASSWORD` - Admin password
   - `APP_ADMIN_NAME` - Admin display name

## 🧪 Running Tests

```bash
# Windows
.\mvnw.cmd test

# Linux/Mac
./mvnw test
```

## 🏗️ Building for Production

```bash
# Create executable JAR
.\mvnw.cmd clean package -DskipTests

# Run the JAR
java -jar target/campus-study-hub-1.0.0.jar
```

## 📸 Screenshots

*Coming soon*

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 👨‍💻 Author

Created as a learning project showcasing Spring Boot, Thymeleaf, and PostgreSQL integration.

---

⭐ **Star this repo if you found it helpful!**
