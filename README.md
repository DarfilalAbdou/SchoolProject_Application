# School Management System

A Spring Boot application for managing school data, including students, courses, directors, and grades. It features both a REST API and a user-friendly web interface for students and teachers (directors).

## Features

### 1. Student Personal Space
- **Secure Login**: Students can log in using their ID and password.
- **Grade Overview**: Students can view their grades across different courses.
- **Visual Feedback**: Grades are color-coded (e.g., Green for 'A', Red for 'F') for quick assessment.

### 2. Teacher (Director) Interface
- **Course Management**: Teachers can view their assigned courses and see all enrolled students.
- **Grade Management (CRUD)**:
    - **Add**: Assign new grades to students in specific courses.
    - **Update**: Modify existing grades.
    - **Delete**: Remove grades if necessary.
- **Course Control**: Teachers can add new courses or delete existing ones they manage.

### 3. REST API
- Fully functional REST endpoints for all entities:
    - `GET /api/students`, `POST /api/students`, etc.
    - `GET /api/courses`, `POST /api/courses`, etc.
    - `GET /api/grades`, `POST /api/grades`, etc.
    - `GET /api/directors`, `POST /api/directors`, etc.

### 4. Database Persistence
- Uses **H2 Database** with file-based persistence (`./entity/school`).
- Data remains available across application restarts.
- **H2 Console** accessible for direct database inspection.

## Getting Started

### Prerequisites
- Java 17 or later.
- Gradle (included via Gradle Wrapper).

### Launching the Application
Run the following command in the project root:
```powershell
./gradlew.bat bootRun
```
The application will start on `http://localhost:8080`.

### Accessing the Interfaces
- **Main Login Page**: `http://localhost:8080/`
- **H2 Console**: `http://localhost:8080/h2-console`
    - **JDBC URL**: `jdbc:h2:file:./entity/school`
    - **User**: `abdou`
    - **Password**: `paf`

## Showcase Data (Demo Credentials)

| Role | ID | Password | Description |
| :--- | :--- | :--- | :--- |
| **Student** | `1` | `pass123` | Alice Brown |
| **Student** | `2` | `pass123` | Bob Davis |
| **Teacher** | `1` | `admin123` | Director Smith |
| **Teacher** | `2` | `admin123` | Director Johnson |

## Technical Stack
- **Backend**: Spring Boot 3, Spring Data JPA, Spring Web.
- **Frontend**: Thymeleaf, Bootstrap 5.
- **Database**: H2 (Persistent File Mode).
- **Build Tool**: Gradle.
