package iuh.fit.fashionshop_be.service;

import iuh.fit.fashionshop_be.dto.response.ChatResponse;
import iuh.fit.fashionshop_be.model.Product;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Service
public class AiService {

    private final ProductService productService;
    private final ChatClient chatClient;

    public AiService(ProductService productService, ChatClient.Builder chatClientBuilder) {
        this.productService = productService;
        this.chatClient = chatClientBuilder.build();
    }

    public ChatResponse processUserInput(String input) {
        if (input == null || input.isBlank()) {
            return new ChatResponse("Vui lòng nhập câu hỏi của bạn.", "text", null);
        }

        // Extract keywords từ câu hỏi
        String keyword = extractKeyword(input);
        
        // Tìm sản phẩm với keyword đã extract
        List<Product> hits = productService.searchByKeyword(keyword);

        if (hits != null && !hits.isEmpty()) {
            List<ChatResponse.ProductSuggestion> products = hits.stream()
                    .limit(5)
                    .map(p -> new ChatResponse.ProductSuggestion(
                            p.getProductID(),
                            p.getName(),
                            p.getBasePrice(),
                            p.getVariants() != null && !p.getVariants().isEmpty()
                                    ? p.getVariants().getFirst().getInventory().getQuantity()
                                    : 0,
                            p.getImage()
                    ))
                    .collect(Collectors.toList());

            return new ChatResponse(
                    "🛍️ Tôi tìm thấy " + products.size() + " sản phẩm phù hợp:",
                    "products",
                    products
            );
        }

        // Fallback sang AI để tìm sản phẩm tương tự
        try {
            List<Product> allProducts = productService.findAll();
            
            // Sử dụng AI để extract keyword từ câu hỏi
            String extractedKeyword = extractKeywordWithAI(input, allProducts);
            
            // Tìm lại với keyword do AI extract
            if (extractedKeyword != null && !extractedKeyword.isEmpty()) {
                List<Product> relevantProducts = productService.searchByKeyword(extractedKeyword);
                
                if (!relevantProducts.isEmpty()) {
                    List<ChatResponse.ProductSuggestion> products = relevantProducts.stream()
                            .limit(5)
                            .map(p -> new ChatResponse.ProductSuggestion(
                                    p.getProductID(),
                                    p.getName(),
                                    p.getBasePrice(),
                                    p.getVariants() != null && !p.getVariants().isEmpty()
                                            ? p.getVariants().getFirst().getInventory().getQuantity()
                                            : 0,
                                    p.getImage()
                            ))
                            .collect(Collectors.toList());

                    return new ChatResponse(
                            "Đây là một số sản phẩm phù hợp với yêu cầu của bạn:",
                            "products",
                            products
                    );
                }
            }

            // Nếu vẫn không tìm thấy, trả về câu trả lời AI
            String productContext = allProducts.stream()
                    .limit(50) // Giới hạn context để tránh quá dài
                    .map(p -> String.format("- %s: %,.0f VNĐ, Danh mục: %s",
                            p.getName(),
                            p.getBasePrice(),
                            p.getCategory() != null ? p.getCategory().getName() : "N/A"))
                    .collect(Collectors.joining("\n"));

            String systemPrompt = "Bạn là trợ lý mua sắm thông minh. " +
                    "Hãy tư vấn sản phẩm dựa trên danh sách sau:\n\n" +
                    productContext + "\n\n" +
                    "Trả lời ngắn gọn, thân thiện bằng tiếng Việt. " +
                    "Không dùng HTML, chỉ văn bản thuần.";

            String response = chatClient.prompt()
                    .system(systemPrompt)
                    .user(input)
                    .call()
                    .content();

            return new ChatResponse(
                    response != null ? response : "Xin lỗi, tôi không thể xử lý yêu cầu này.",
                    "text",
                    null
            );

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ChatResponse(
                    "Lỗi khi xử lý với AI: " + ex.getMessage(),
                    "text",
                    null
            );
        }
    }

    /**
     * Extract keyword từ câu hỏi bằng regex
     * Ví dụ: "tôi muốn tìm áo polo" -> "áo polo"
     */
    private String extractKeyword(String input) {
        String lowerInput = input.toLowerCase().trim();
        
        // Các từ khóa trigger
        String[] triggers = {
            "tìm", "mua", "xem", "cần", "muốn", "có", "bán", "tìm kiếm"
        };
        
        // Loại bỏ các từ trigger và lấy phần còn lại
        for (String trigger : triggers) {
            Pattern pattern = Pattern.compile("(.*?)" + trigger + "\\s+(.+)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(lowerInput);
            if (matcher.find()) {
                String extracted = matcher.group(2).trim();
                // Loại bỏ các từ không cần thiết ở cuối
                extracted = extracted.replaceAll("(không|nào|gì|thế|vậy|đi|nhé|ạ)$", "").trim();
                return extracted;
            }
        }
        
        // Nếu không match pattern, trả về input gốc
        return input;
    }

    /**
     * Sử dụng AI để extract keyword chính xác hơn
     */
    private String extractKeywordWithAI(String input, List<Product> allProducts) {
        try {
            // Tạo danh sách các tên sản phẩm và category
            String productNames = allProducts.stream()
                    .map(Product::getName)
                    .distinct()
                    .limit(30)
                    .collect(Collectors.joining(", "));

            String categories = allProducts.stream()
                    .map(p -> p.getCategory() != null ? p.getCategory().getName() : "")
                    .filter(c -> !c.isEmpty())
                    .distinct()
                    .collect(Collectors.joining(", "));

            String extractPrompt = String.format(
                    "Từ câu hỏi: '%s'\n\n" +
                    "Danh sách sản phẩm có sẵn: %s\n" +
                    "Danh mục: %s\n\n" +
                    "Hãy trích xuất TỪ KHÓA chính để tìm kiếm sản phẩm (chỉ trả về 1-3 từ, không giải thích).\n" +
                    "Ví dụ: 'tôi muốn mua áo polo' -> trả về 'polo' hoặc 'áo polo'",
                    input, productNames, categories
            );

            String keyword = chatClient.prompt()
                    .user(extractPrompt)
                    .call()
                    .content();

            return keyword != null ? keyword.trim().toLowerCase() : input;
        } catch (Exception e) {
            return input;
        }
    }
}