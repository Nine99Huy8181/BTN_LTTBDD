package iuh.fit.fashionshop_be.service;

import iuh.fit.fashionshop_be.dto.response.ChatResponse;
import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatSessionManager {

    @Data
    public static class ChatSessionData {
        private String sessionId;
        private List<ChatMessageData> messages = new ArrayList<>();
        private Instant lastActivityAt;

        public ChatSessionData(String sessionId) {
            this.sessionId = sessionId;
            this.lastActivityAt = Instant.now();
        }
    }

    @Data
    public static class ChatMessageData {
        private String sender; // "user" hoặc "bot"
        private String content;
        private String type; // "text" hoặc "products"
        private List<ChatResponse.ProductSuggestion> products; // nếu type = "products"
        private Instant createdAt;

        public ChatMessageData(String sender, String content, String type, List<ChatResponse.ProductSuggestion> products) {
            this.sender = sender;
            this.content = content;
            this.type = type;
            this.products = products;
            this.createdAt = Instant.now();
        }
    }

    // Lưu session trong bộ nhớ: sessionId -> ChatSessionData
    private final Map<String, ChatSessionData> sessions = new ConcurrentHashMap<>();

    /**
     * Đảm bảo session tồn tại (tạo mới nếu chưa có)
     */
    public String ensureSession(String sessionId) {
        if (sessionId == null || sessionId.isBlank()) {
            sessionId = UUID.randomUUID().toString();
        }

        sessions.putIfAbsent(sessionId, new ChatSessionData(sessionId));
        
        // Cập nhật hoạt động cuối
        ChatSessionData data = sessions.get(sessionId);
        if (data != null) {
            data.setLastActivityAt(Instant.now());
        }

        return sessionId;
    }

    /**
     * Lưu tin nhắn người dùng
     */
    public void addUserMessage(String sessionId, String content) {
        ChatSessionData data = sessions.get(sessionId);
        if (data != null) {
            data.getMessages().add(new ChatMessageData("user", content, "text", null));
            data.setLastActivityAt(Instant.now());
        }
    }

    /**
     * Lưu tin nhắn từ bot
     */
    public void addBotMessage(String sessionId, String content, String type, List<ChatResponse.ProductSuggestion> products) {
        ChatSessionData data = sessions.get(sessionId);
        if (data != null) {
            data.getMessages().add(new ChatMessageData("bot", content, type, products));
            data.setLastActivityAt(Instant.now());
        }
    }

    /**
     * Lấy danh sách tin nhắn của session
     */
    public List<ChatMessageData> getMessages(String sessionId) {
        ChatSessionData data = sessions.get(sessionId);
        return data != null ? new ArrayList<>(data.getMessages()) : Collections.emptyList();
    }

    /**
     * Xóa session khỏi bộ nhớ
     */
    public void removeSession(String sessionId) {
        sessions.remove(sessionId);
    }

    /**
     * Chạy mỗi 1 phút: xóa các session không hoạt động hơn 30 phút
     */
    @Scheduled(fixedDelay = 30_000) // Chạy mỗi 60 giây
    public void cleanupExpiredSessions() {
        Instant cutoff = Instant.now().minus(1, ChronoUnit.MINUTES);

        sessions.entrySet().removeIf(entry -> entry.getValue().getLastActivityAt().isBefore(cutoff));
    }
}
