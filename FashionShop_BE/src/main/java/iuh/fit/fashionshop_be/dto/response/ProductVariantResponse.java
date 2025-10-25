/*
 * @ (#) ProductVariantResponse.java     1.0    25-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.dto.response;

import iuh.fit.fashionshop_be.model.ProductVariant;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Mapping;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:25-Oct-25
 * @version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariantResponse {
    Long variantID;
    String sku;
    String size;
    String color;
    Float priceAdjustment;
    String[] images;
    String status;
    Long productID;
    Integer validQuantity;
}