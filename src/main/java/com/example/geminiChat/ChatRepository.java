package com.example.geminiChat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    // Find a Chat by its prompt
    Optional<Chat> findByPrompt(String prompt);
}
