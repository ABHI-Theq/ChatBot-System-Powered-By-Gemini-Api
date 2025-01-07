package com.example.geminiChat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ChatDto {
    private String prompt;
    private String response;
    private LocalDateTime createdAt; // Optional, if you want to display when the conversation happened
}
