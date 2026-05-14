# Quiz App (Monolithic)

This is a backend application for a Quiz platform built using **Spring Boot**. It is a monolithic application that allows users to fetch questions, create questions, generate random quizzes by category, and submit quiz answers to calculate their score.

## 🛠️ Technologies & Dependencies Used
The project is built using Java and Spring Boot. The following dependencies are used:
- **Spring Web** (`spring-boot-starter-webmvc`): For building RESTful web services.
- **Spring Data JPA** (`spring-boot-starter-data-jpa`): For database interactions and ORM.
- **PostgreSQL Driver** (`postgresql`): To connect to the PostgreSQL database.
- **Lombok** (`lombok`): To reduce boilerplate code like getters, setters, and constructors.

## 🗄️ Database Setup

The project uses PostgreSQL as its relational database.

1. Ensure you have **PostgreSQL** installed and running on your machine on the default port `5432`.
2. Create a new database named `questiondb`:
   ```sql
   CREATE DATABASE questiondb;
   ```
3. Update the database credentials if necessary in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/questiondb
   spring.datasource.username=postgres
   spring.datasource.password=sam
   ```
*Note: The application is configured to automatically create/update tables (`spring.jpa.hibernate.ddl-auto=update`) and will run a pre-configured `data.sql` script to seed initial data automatically (`spring.sql.init.mode=always`).*

## 🚀 How to Run the Application

1. Open the project in your IDE (IntelliJ IDEA, Eclipse, Android Studio, etc.).
2. Let Maven resolve the dependencies.
3. Run the application from the main class: `QuizAppApplication.java`.
4. The server will start on `http://localhost:8080`.

## 🌐 API Endpoints

### 1. Question API (`/question`)

| HTTP Method | Endpoint | Description | Request Body |
|-------------|----------|-------------|--------------|
| **GET** | `/question/allQuestions` | Fetches all the available questions. | N/A |
| **GET** | `/question/category/{value}` | Fetches questions for a specific category (e.g., `Java`). | N/A |
| **POST** | `/question/addQuestion` | Adds a new question to the database. | JSON representation of a `Question` |

**Example `POST /question/addQuestion` Request Body:**
```json
{
  "category": "Java",
  "difficultyLevel": "Easy",
  "option1": "extends",
  "option2": "implements",
  "option3": "inherits",
  "option4": "instanceof",
  "questionTitle": "Which keyword is used to inherit a class in Java?",
  "rightAnswer": "extends"
}
```

### 2. Quiz API (`/quiz`)

| HTTP Method | Endpoint | Description | Request Params / Body |
|-------------|----------|-------------|-----------------------|
| **POST** | `/quiz/create` | Creates a new quiz with random questions. | **Params:** `category` (String), `numQ` (int), `title` (String) |
| **GET** | `/quiz/get/{id}` | Fetches a specific quiz by its ID (returns `QuestionWrapper` without the correct answers). | N/A |
| **POST** | `/quiz/submit/{id}` | Submits answers for a quiz and returns the total score. | JSON Array of `Response` objects |

**Example `POST /quiz/create` Request:**
```text
POST http://localhost:8080/quiz/create?category=Java&numQ=5&title=Core Java Quiz
```

**Example `POST /quiz/submit/{id}` Request Body:**
```json
[
  {
    "id": 1,
    "reponse": "extends"
  },
  {
    "id": 2,
    "reponse": "main()"
  }
]
```
*(Note: the property name in the JSON model is currently spelled `reponse`, without the 's' after the first 'e')*