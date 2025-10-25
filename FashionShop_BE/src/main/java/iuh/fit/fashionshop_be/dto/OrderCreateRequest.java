package iuh.fit.fashionshop_be.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderCreateRequest {
    private List<OrderItemRequest> items;
    private Long addressID; // optional
    private String paymentMethod;
    private String notes;
}
// Khoa 