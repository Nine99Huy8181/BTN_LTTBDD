package iuh.fit.fashionshop_be.controller;

import iuh.fit.fashionshop_be.dto.response.ChatResponse;
import iuh.fit.fashionshop_be.service.AiService;
import iuh.fit.fashionshop_be.service.ChatSessionManager;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*") // Cho phép React Native gọi API
public class ChatController {

    private final AiService aiService;
    private final ChatSessionManager chatSessionManager;

    public ChatController(AiService aiService, ChatSessionManager chatSessionManager) {
        this.aiService = aiService;
        this.chatSessionManager = chatSessionManager;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChatResponse> chat(@RequestBody Map<String, String> body) {
        String userInput = body.get("message");
        String sessionId = body.get("sessionId");

        if (userInput == null || userInput.isBlank()) {
            return ResponseEntity.badRequest()
                    .body(new ChatResponse("Vui lòng nhập câu hỏi", "text", null));
        }

        try {
            // Tạo hoặc lấy session có sẵn (TTL 30 phút)
            String sid = chatSessionManager.ensureSession(sessionId);

            // Lưu tin nhắn người dùng vào session (bộ nhớ)
            chatSessionManager.addUserMessage(sid, userInput.trim());

            // Xử lý input với AI
            ChatResponse reply = aiService.processUserInput(userInput.trim());

            // Lưu phản hồi từ bot vào session (bộ nhớ)
            chatSessionManager.addBotMessage(sid, reply.getMessage(), reply.getType(), reply.getProducts());

            // Trả về sessionId để client lưu vào AsyncStorage
            reply.setSessionId(sid);
            return ResponseEntity.ok(reply);
        } catch (Exception e) {
            e.printStackTrace();
            ChatResponse err = new ChatResponse("Lỗi server: " + e.getMessage(), "text", null);
            try {
                String sid = chatSessionManager.ensureSession(body.get("sessionId"));
                err.setSessionId(sid);
            } catch (Exception ignore) {}
            return ResponseEntity.status(500).body(err);
        }
    }

    /**
     * API lấy lịch sử chat của một session
     * Nếu session hết hạn (>30 phút), trả về danh sách rỗng
     */
    @GetMapping(path = "/{sessionId}")
    public ResponseEntity<List<Map<String, Object>>> getHistory(@PathVariable String sessionId) {
        try {
            List<ChatSessionManager.ChatMessageData> msgs = chatSessionManager.getMessages(sessionId);
            List<Map<String, Object>> items = msgs.stream()
                    .map(m -> {
                        Map<String, Object> item = new java.util.HashMap<>();
                        item.put("sender", m.getSender());
                        item.put("content", m.getContent());
                        item.put("type", m.getType());
                        item.put("products", m.getProducts());
                        item.put("createdAt", m.getCreatedAt());
                        return item;
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}