package com.example.geminiChat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.Map;

@Service
public class QnAService {
    //Access to APIKEy and URL
    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private final WebClient webClient;

    public QnAService(WebClient.Builder webClient, ChatRepository chatRepository) {
        this.webClient = webClient.build();
        this.chatRepository = chatRepository;
    }

    private final ChatRepository chatRepository;


    public String getAnswer(String question){
        //Construct the request payload
        Map<String,Object> requestBody=Map.of(
          "contents",new Object[]{
                  Map.of(
                          "parts",new Object[]{
                                  Map.of("text",question)
                          }
                  )
                }
        );
        //Make API Call

        String response=webClient.post()
                .uri(geminiApiUrl+geminiApiKey)
                .header("Content-Type","application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        //Return response
        String answer= extractAnswerFromResponse(response);


        // Save prompt and response to the database
        chatRepository.save(new Chat(question, answer));

        return answer;
    }

    private String extractAnswerFromResponse(String response) {
        try {
            // Create an ObjectMapper instance for JSON parsing
            ObjectMapper mapper = new ObjectMapper();

            // Parse the response string into a JsonNode
            JsonNode rootNode = mapper.readTree(response);
            // Navigate through the JSON to extract the required answer
            JsonNode candidatesNode = rootNode.get("candidates");
            if (candidatesNode != null && candidatesNode.isArray() && candidatesNode.size() > 0) {
                JsonNode firstCandidate = candidatesNode.get(0);
                JsonNode contentNode = firstCandidate.get("content");
                    JsonNode partsNode=contentNode.get("parts");

                    if (partsNode != null && partsNode.isArray() && partsNode.size() > 0) {
                        JsonNode firstPart = partsNode.get(0);
                        JsonNode textNode = firstPart.get("text");
                        if (textNode != null) {
                            return textNode.asText(); // Extract the text
                        }
                    }

            }
            return "Answer not found in the response.";
        } catch (Exception e) {
            return "Error while parsing response: " + e.getMessage();
        }
    }
}
