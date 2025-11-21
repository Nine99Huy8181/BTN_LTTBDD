package iuh.fit.fashionshop_be.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {
    private String message;
    private String type; // "products" hoặc "text"
    private List<ProductSuggestion> products;
    // Optional session id returned to client
    private String sessionId;

    // Preserve older constructor usage
    public ChatResponse(String message, String type, List<ProductSuggestion> products) {
        this.message = message;
        this.type = type;
        this.products = products;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductSuggestion {
        private Long id;
        private String name;
        private Float price;
        private Integer inventory;
        private String image;
    }
}
