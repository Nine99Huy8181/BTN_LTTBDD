/*
 * @ (#) ProductResponse.java     1.0    24-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:24-Oct-25
 * @version: 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    Long productID;
    String name;
    String description;
    String brand;
    Float basePrice;
    String material;
    LocalDateTime createdDate;
    String status;
    Float averageRating;
    Float discountPrice;
    Boolean isFeatured;
    Integer reviewCount;
    String image;
    Integer soldQuantity;
}