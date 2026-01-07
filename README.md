📚 Library Management System

A Library Management System built using Java, Spring Boot, and Maven. This project helps manage library operations such as managing books, users, and issue/return transactions. It is designed as a monolithic Spring Boot application and is suitable for learning, academic use, and portfolio showcasing.


🚀 Features
•📖 Manage Books (Add, Update, Delete, View)
•👤 Manage Users / Students
•🔄 Issue & Return Books
•🔐 Role-based access (Admin / Student)
•🧾 Track book availability
•🌐 RESTful APIs
•🗄️ Database integration using JPA


🧰 Tech Stack
•Java (JDK 17+)
•Spring Boot
•Spring Data JPA
•Spring Security
•Maven
•MySQL
•Eclipse


📦 Required Spring Boot Starters

The following Spring Boot starters are used in this project:
•spring-boot-starter-web
•spring-boot-starter-data-jpa
•spring-boot-starter-security
•spring-boot-starter-validation
•spring-boot-starter-test

Database Driver:
•mysql-connector-j


📂 Project Structure
LibraryManagementSystem
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.nt
│   │   │       ├── controller
│   │   │       ├── dto
│   │   │       ├── entity
│   │   │       ├── jwt
│   │   │       ├── repository
│   │   │       ├── security
│   │   │       ├── service
│   │   │       └── LibraryManagementApplication.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── static / templates
│   └── test
│       └── java
│── pom.xml


⚙️ Database Configuration

Create a MySQL database:
CREATE DATABASE library_db;

🔄 Application Workflow
1.Application starts using Spring Boot
2.Spring Security initializes authentication & authorization
3.Controllers handle API requests
4.Services contain business logic
5.Repositories interact with the database using JPA
6.Data is stored/retrieved from MySQL --- ## 👨‍💻 Author

Akash Datal Java | Spring Boot | Full Stack Developer


⭐ If you like this project, don’t forget to star the repository!
