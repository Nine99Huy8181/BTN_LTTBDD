/*
 * @ (#) ProductResponse.java     1.0    24-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
    String brand;
    Float discountPrice;
    Float averageRating;
    String image;
    Integer soldQuantity;
}