# Gemini Chatbot Project

Welcome to the **Gemini Chatbot** repository! This project is a chatbot application built using the **Java Spring Boot** stack, integrating the **Gemini API** for intelligent Q&A services. The chatbot leverages **MySQL** as its database to store user prompts and responses.

---

## Features
- **AI-Powered Q&A**: Ask questions and get intelligent answers powered by the Gemini API.
- **Chat History**: Retrieve the history of chat interactions.
- **Database Integration**: Store prompts and responses in a MySQL database for quick access.
- **Web Interface**: User-friendly interface using Thymeleaf.

---

## Project Structure

```
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com.example.geminiChat
│   │   │   │   ├── AiController.java       # Handles API routes for the chatbot
│   │   │   │   ├── HomeController.java     # Handles frontend routes for UI pages
│   │   │   │   ├── QnAService.java         # Service for interacting with the Gemini API
│   │   │   │   ├── ChatRepository.java     # Repository for database interactions
│   │   │   │   ├── Chat.java               # Entity class for chat data
│   │   │   │   ├── ChatDto.java            # Data Transfer Object for chat data
│   │   ├── resources
│   │   │   ├── static
│   │   │   │   ├── index.js                # Frontend JavaScript
│   │   │   │   ├── styles.css              # Shared CSS styles
│   │   │   │   ├── history.css             # Styles for the chat history page
│   │   │   ├── templates
│   │   │   │   ├── Home.html               # Home page UI
│   │   │   │   ├── History.html            # Chat history UI
│   │   │   ├── application.properties      # Application configurations
│   │   │   ├── data.sql                    # Initial database seed data
├── pom.xml                                # Maven dependencies and build configurations
```

---

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/ABHI-Theq/ChatBot-System-Powered-By-Gemini-Api.git
   cd geminiChat
   ```

2. Configure the database and API in `application.properties`:
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

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

5. Access the application:
   - API Endpoints: `http://localhost:8080/api`
   - Web Interface: `http://localhost:8080/home`

---

## API Endpoints

### 1. Ask a Question
- **Endpoint**: `/api/ask`
- **Method**: POST
- **Request Body**:
  ```json
  {
    "question": "What is the Gemini API?"
  }
  ```
- **Response**:
  ```json
  "Gemini API is..."
  ```

### 2. Get Chat History
- **Endpoint**: `/api/history`
- **Method**: GET
- **Response**:
  ```json
  [
    {
      "prompt": "What is the Gemini API?",
      "response": "Gemini API is...",
      "createdAt": "2025-01-01T12:00:00Z"
    }
  ]
  ```

---

## Dependencies

### Key Dependencies:
- **Spring Boot**:
  - spring-boot-starter-web
  - spring-boot-starter-webflux
  - spring-boot-starter-data-jpa
  - spring-boot-starter-thymeleaf
- **Database**:
  - MySQL Connector
  - H2 (for testing purposes)
- **Reactive Programming**:
  - Reactor Core
- **Utilities**:
  - Lombok

For the complete list, refer to the [pom.xml](pom.xml).

---

## Contributing

1. Fork the repository.
2. Create a feature branch:
   ```bash
   git checkout -b feature-name
   ```
3. Commit your changes:
   ```bash
   git commit -m "Add a new feature"
   ```
4. Push to the branch:
   ```bash
   git push origin feature-name
   ```
5. Open a pull request.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Author

Developed by [ABHI-Theq](https://github.com/ABHI-Theq).

Feel free to reach out for any queries or suggestions!

