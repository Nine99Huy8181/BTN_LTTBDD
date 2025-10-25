package iuh.fit.fashionshop_be.dto;

import lombok.Data;

@Data
public class OrderItemRequest {
    private Long variantID;
    private Integer quantity;
}
// Khoa 