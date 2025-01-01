package com.example.geminiChat;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class AiController {

    private final QnAService qnAService;
    private final ChatRepository chatRepository;

    @PostMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestBody Map<String, String> payload) {
        String question = payload.get("question");

        // Check if the question already exists in the database
        Optional<Chat> existingChat = chatRepository.findByPrompt(question);

//        Flux<String> answerStream;
        String answer;
        if (existingChat.isPresent()) {
            // If the question exists, fetch the response from the database
             answer = existingChat.get().getResponse();
        } else {
            // Stream the answer as it's generated by the QnAService
            answer = qnAService.getAnswer(question);

        }

        return ResponseEntity.ok(answer);
    }


    @GetMapping("/history")
    public ResponseEntity<List<ChatDto>> getChatHistory() {
        // Convert chat history to a list of ChatDTOs
        List<ChatDto> chatHistory = chatRepository.findAll().stream()
                .map(chat -> new ChatDto(chat.getPrompt(), chat.getResponse(), chat.getCreatedAt())) // Assuming Chat has getTimestamp() method
                .collect(Collectors.toList());

        return ResponseEntity.ok(chatHistory);
    }
}
