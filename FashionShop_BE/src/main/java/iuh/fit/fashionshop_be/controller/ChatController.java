package iuh.fit.fashionshop_be.controller;

import iuh.fit.fashionshop_be.dto.response.ChatResponse;
import iuh.fit.fashionshop_be.service.AiService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*") // Cho phép React Native gọi API
public class ChatController {

    private final AiService aiService;

    public ChatController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChatResponse> chat(@RequestBody Map<String, String> body) {
        String userInput = body.get("message");

        if (userInput == null || userInput.isBlank()) {
            return ResponseEntity.badRequest()
                    .body(new ChatResponse("Vui lòng nhập câu hỏi", "text", null));
        }

        try {
            ChatResponse reply = aiService.processUserInput(userInput.trim());
            return ResponseEntity.ok(reply);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(new ChatResponse("Lỗi server: " + e.getMessage(), "text", null));
        }
    }
}