package iuh.fit.fashionshop_be.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVariantRequest {
    private Long productID;
    private String sku;
    private String size;
    private String color;
    private Float priceAdjustment;
    private String[] images;
    private String status;
    // validQuantity là "available" mà client gửi (quantity - reservedQuantity)
    private Integer validQuantity;
    private Integer reservedQuantity;
}