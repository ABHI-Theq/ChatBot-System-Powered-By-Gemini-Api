package com.example.geminiChat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String showIndexPage() {
        // This will render index.html in the /templates folder
        return "Home";
    }

    @GetMapping("/history")
    public String showHistoryPage() {
        // This will render history.html in the /templates folder
        return "History";
    }
}
