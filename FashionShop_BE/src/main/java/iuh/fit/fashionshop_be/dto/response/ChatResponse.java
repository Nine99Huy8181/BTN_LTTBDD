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
