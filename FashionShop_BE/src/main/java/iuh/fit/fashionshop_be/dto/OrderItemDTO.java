/*
 * @ (#) f.java     1.0    25-Nov-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:25-Nov-25
 * @version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    private Long orderItemID;

    // Thông tin sản phẩm
    private Long productID;
    private String productName;
    private String productImage;        // ảnh đại diện của sản phẩm (lấy ảnh đầu tiên của variant hoặc của product)

    // Thông tin variant
    private Long variantID;
    private String sku;
    private String size;
    private String color;
    private String[] variantImages;     // giữ nguyên mảng ảnh của variant nếu cần hiển thị nhiều

    // Giá & số lượng
    private Integer quantity;
    private Float unitPrice;            // giá tại thời điểm đặt hàng
    private Float subTotal;             // quantity * unitPrice

    // Optional: thêm giá gốc và giá sau điều chỉnh để hiển thị khuyến mãi (nếu muốn)
    private Float originalPrice;        // basePrice + priceAdjustment (hoặc discountPrice nếu có)
}