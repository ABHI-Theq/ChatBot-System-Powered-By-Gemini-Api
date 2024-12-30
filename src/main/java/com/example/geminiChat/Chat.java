package com.example.geminiChat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
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

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Chat(String prompt, String response) {
        this.prompt = prompt;
        this.response = response;
    }
}
