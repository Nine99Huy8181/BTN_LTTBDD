/*
 * @ (#) CategoryResponse.java     1.0    25-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.dto.response;

import iuh.fit.fashionshop_be.model.Category;
import iuh.fit.fashionshop_be.model.Product;
import iuh.fit.fashionshop_be.service.ProductService;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Context;
import org.mapstruct.Mapping;

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
public class CategoryResponse {
    Long  categoryID;
    String name;
    String description;
    String image;
    Long parentID;
}