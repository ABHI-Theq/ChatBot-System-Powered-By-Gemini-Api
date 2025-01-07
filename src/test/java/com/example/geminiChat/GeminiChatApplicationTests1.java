package com.example.geminiChat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class GeminiChatApplicationTests1 {

	@Test
	void contextLoads() {
		System.out.println("Running test: contextLoads");
		assertTrue(true, "Context loads successfully");
		System.out.println("Passed: contextLoads");
	}

	@Test
	void applicationStartsSuccessfully() {
		System.out.println("Running test: applicationStartsSuccessfully");
		GeminiChatApplication.main(new String[] {});
		assertTrue(true, "Application starts successfully");
		System.out.println("Passed: applicationStartsSuccessfully");
	}
}
