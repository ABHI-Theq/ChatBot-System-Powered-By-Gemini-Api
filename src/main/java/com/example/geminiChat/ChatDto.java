package com.example.geminiChat;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ChatDto {
    private String prompt;
    private String response;
    private LocalDateTime createdAt; // Optional, if you want to display when the conversation happened
}
