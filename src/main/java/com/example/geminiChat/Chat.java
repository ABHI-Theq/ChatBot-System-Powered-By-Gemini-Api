package com.example.geminiChat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Chat(){

    }

    @Column(nullable = false)
    private String prompt;

    @Column(nullable = false)
    private String response;

    public String getPrompt(){
        return prompt;
    }

    public String getResponse(){
        return response;
    }

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public LocalDateTime getCreateaAt(){
        return createdAt;
    }

    public Chat(String prompt, String response) {
        this.prompt = prompt;
        this.response = response;
    }
}
