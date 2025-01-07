package com.example.geminiChat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GeminiChatApplicationTests2 {

    @Mock
    private QnAService qnAService;

    @Mock
    private ChatRepository chatRepository;

    @InjectMocks
    private AiController aiController;


    @Test
    void testGetChatHistory_Success() {
        System.out.println("Running test: testGetChatHistory_Success");

        // Mocking the ChatDto objects directly
        Chat chat1 = new Chat("explain about ai", "AI stands for Artificial Intelligence.");
        ChatDto chatDto1 = new ChatDto("explain about ai", "AI stands for Artificial Intelligence.", chat1.getCreatedAt());

        when(chatRepository.findAll()).thenReturn(Arrays.asList(chat1));

        ResponseEntity<List<ChatDto>> response = aiController.getChatHistory();

        assertEquals(200, response.getStatusCodeValue(), "Expected status code 200");
        assertNotNull(response.getBody(), "Expected non-null response body");
        assertEquals(1, response.getBody().size(), "Expected 1 chat record");
        verify(chatRepository, times(1)).findAll();
        System.out.println(response.getBody());

        System.out.println("Passed: testGetChatHistory_Success");
    }

    @Test
    void testGetChatHistory_NoRecords() {
        System.out.println("Running test: testGetChatHistory_NoRecords");

        // Mocking an empty database
        when(chatRepository.findAll()).thenReturn(Arrays.asList());

        ResponseEntity<List<ChatDto>> response = aiController.getChatHistory();

        assertEquals(200, response.getStatusCodeValue(), "Expected status code 200");
        assertNotNull(response.getBody(), "Expected non-null response body");
        assertTrue(response.getBody().isEmpty(), "Expected empty response body");

        System.out.println("Passed: testGetChatHistory_NoRecords");
    }

    @Test
    void testGetChatHistory_ExceptionHandling() {
        System.out.println("Running test: testGetChatHistory_ExceptionHandling");

        // Simulating a database error
        when(chatRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        ResponseEntity<List<ChatDto>> response = aiController.getChatHistory();

        assertEquals(500, response.getStatusCodeValue(), "Expected status code 500 for internal server error");
        assertNull(response.getBody(), "Expected null response body due to exception");

        System.out.println("Passed: testGetChatHistory_ExceptionHandling");
    }

    @Test
    void testAskQuestion_ExistingPrompt() {
        System.out.println("Running test: testAskQuestion_ExistingPrompt");

        String question = "explain about ai";
        String answer = "Artificial intelligence (AI) is a broad field encompassing the development of computer systems capable of performing tasks that typically require human intelligence. These tasks include things like: Learning, Reasoning, Problem-solving, etc.";

        Chat chat = new Chat(question, answer);

        // Mocking the repository to return the existing chat
        when(chatRepository.findByPrompt(question)).thenReturn(Optional.of(chat));

        ResponseEntity<String> response = aiController.askQuestion(Map.of("question", question));

        assertEquals(200, response.getStatusCodeValue(), "Expected status code 200");
        assertEquals(answer, response.getBody(), "Expected correct answer from the database");

        System.out.println("Passed: testAskQuestion_ExistingPrompt");
    }

    @Test
    void testAskQuestion_NewPrompt() {
        System.out.println("Running test: testAskQuestion_NewPrompt");

        String question = "What is the capital of France?";
        String answer = "The capital of France is Paris.";

        // Simulating no prompt in the database and calling the external Gemini API
        when(chatRepository.findByPrompt(question)).thenReturn(Optional.empty());
        when(qnAService.getAnswer(question)).thenReturn(answer);

        ResponseEntity<String> response = aiController.askQuestion(Map.of("question", question));

        assertEquals(200, response.getStatusCodeValue(), "Expected status code 200");
        assertEquals(answer, response.getBody(), "Expected correct answer from QnA service");

        System.out.println("Passed: testAskQuestion_NewPrompt");
    }

    @Test
    void testAskQuestion_ExceptionHandling() {
        System.out.println("Running test: testAskQuestion_ExceptionHandling");

        String question = "Error case?";

        // Simulating an error while querying the database
        when(chatRepository.findByPrompt(question)).thenThrow(new RuntimeException("Database error"));

        ResponseEntity<String> response = aiController.askQuestion(Map.of("question", question));

        assertEquals("An error occurred while processing your request. Please try again later.", response.getBody(), "Expected error message");

        System.out.println("Passed: testAskQuestion_ExceptionHandling");
    }

    @Test
    void testAskQuestion_WaitForAnswer() {
        System.out.println("Running test: testAskQuestion_WaitForAnswer");

        String question = "Explain Quantum Computing?";
        String simulatedAnswer = "Quantum Computing uses quantum-mechanical phenomena like superposition and entanglement.";

        // Simulating that no answer exists and the system waits for the Gemini API response
        when(chatRepository.findByPrompt(question)).thenReturn(Optional.empty());
        when(qnAService.getAnswer(question)).thenAnswer(invocation -> {
            // Simulate a delay (e.g., waiting for an API response)
            try {
                Thread.sleep(1000); // Simulate wait time of 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return simulatedAnswer;
        });

        ResponseEntity<String> response = aiController.askQuestion(Map.of("question", question));

        assertEquals(200, response.getStatusCodeValue(), "Expected status code 200 after waiting for answer");
        assertEquals(simulatedAnswer, response.getBody(), "Expected answer after waiting for the Gemini API");

        System.out.println("Passed: testAskQuestion_WaitForAnswer");
    }
}
