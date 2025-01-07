# Gemini Chatbot Project 🤖

Welcome to the **Gemini Chatbot** repository! This project is a chatbot application built using the **Java Spring Boot** stack, integrating the **Gemini API** for intelligent Q&A services. The chatbot leverages **MySQL** as its database to store user prompts and responses.

---

## ✨ Features
- 🧠 **AI-Powered Q&A**: Ask questions and get intelligent answers powered by the Gemini API.
- 📜 **Chat History**: Retrieve the history of chat interactions.
- 💾 **Database Integration**: Store prompts and responses in a MySQL database for quick access.
- 🎯 **Web Interface**: User-friendly interface using Thymeleaf.

---

## 📁 Project Structure

```
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com.example.geminiChat
│   │   │   │   ├── AiController.java         # Handles API routes for the chatbot
│   │   │   │   ├── Chat.java                 # Entity class for chat data
│   │   │   │   ├── ChatDto.java              # Data Transfer Object for chat data
│   │   │   │   ├── ChatRepository.java       # Repository for database interactions
│   │   │   │   ├── GeminiChatApplication.java # Main application class
│   │   │   │   ├── HomeController.java       # Handles frontend routes for UI pages
│   │   │   │   ├── QnAService.java           # Service for interacting with the Gemini API
│   │   ├── resources
│   │   │   ├── static
│   │   │   │   ├── index.js                  # Frontend JavaScript
│   │   │   │   ├── styles.css                # Shared CSS styles
│   │   │   │   ├── history.css               # Styles for the chat history page
│   │   │   ├── templates
│   │   │   │   ├── Home.html                 # Home page UI
│   │   │   │   ├── History.html              # Chat history UI
│   │   │   ├── application.properties        # Application configurations
│   │   │   ├── data.sql                      # Initial database seed data
├── test
│   ├── java
│   │   ├── com.example.geminiChat
│   │   │   ├── GeminiChatApplicationTests1.java              # Unit tests for chat functionality
│   │   │   ├── GeminiChatApplicationTests2.java # Application tests
├── .gitattributes                           # Git attributes configuration
├── .gitignore                               # Git ignore rules
├── HELP.md                                  # Help documentation
├── pom.xml                                  # Maven dependencies and build configurations
```

---

## 🚀 Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/ABHI-Theq/ChatBot-System-Powered-By-Gemini-Api.git
   cd geminiChat
   ```

2. Create a MySQL database named `chatbot`:
   ```sql
   CREATE DATABASE chatbot;
   ```

3. Configure the database and API in `application.properties`:
   ```properties
   spring.application.name=geminiChat
   gemini.api.key=YOUR_API_KEY
   gemini.api.url=https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=
   spring.datasource.url=jdbc:mysql://localhost:3306/chatbot
   spring.datasource.username=YOUR_DATABASE_USERNAME
   spring.datasource.password=YOUR_DATABASE_PASSWORD
   spring.jpa.show-sql=true
   spring.datasource.initialization-mode=always
   spring.sql.init.mode=always
   ```

4. Build and run the project:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

5. Access the application:
   - 🌐 Web Interface: `http://localhost:8080/home`
   - 🔌 API Endpoints: `http://localhost:8080/api`

---

## 🧪 Unit Testing

The project includes comprehensive unit tests located in the `src/test/java/com.example.geminiChat` directory:

### GeminiChatApplicationTests1.java
Basic application tests that verify:
- Application context loading
- Application startup functionality
- Core Spring Boot configuration

### GeminiChatApplicationTests2.java
Comprehensive testing suite for chat functionality including:
- Chat history retrieval
  - Successful retrieval
  - Empty history handling
  - Exception handling
- Question handling
  - Existing prompt retrieval
  - New prompt processing
  - Error handling
  - Async response handling
- Integration with QnAService
- Database operations through ChatRepository

Run tests using:
```bash
mvn GeminiChatApplicationTests1
```

```bash
mvn GeminiChatApplicationTests2
```

Test output will show detailed progress for each test case with console logging.

## 🛠️ API Endpoints

### 1. Ask a Question
- **Endpoint**: `/api/ask`
- **Method**: POST
- **Request Body**:
  ```json
  {
    "question": "What is the Gemini API?"
  }
  ```

### 2. Get Chat History
- **Endpoint**: `/api/history`
- **Method**: GET

---

## 📦 Dependencies

### Key Dependencies:
- 🔧 **Spring Boot**:
  - spring-boot-starter-web
  - spring-boot-starter-webflux
  - spring-boot-starter-data-jpa
  - spring-boot-starter-thymeleaf
- 🗄️ **Database**: MySQL Connector
- ⚡ **Testing**: JUnit, Spring Boot Test
- 🛠️ **Utilities**: Lombok

For the complete list, refer to the [pom.xml](pom.xml).

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature-name`)
3. Commit your changes (`git commit -m "Add a new feature"`)
4. Push to the branch (`git push origin feature-name`)
5. Open a pull request

---

## 📄 License

This project is not licensed yet.

---

## 👨‍💻 Author

Developed by [ABHI-Theq](https://github.com/ABHI-Theq).

Feel free to reach out for any queries or suggestions! 📧
