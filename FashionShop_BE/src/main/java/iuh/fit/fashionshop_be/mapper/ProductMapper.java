/*
 * @ (#) f.java     1.0    24-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.mapper;

import iuh.fit.fashionshop_be.dto.response.ProductResponse;
import iuh.fit.fashionshop_be.model.Product;
import iuh.fit.fashionshop_be.service.ProductService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:24-Oct-25
 * @version: 1.0
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "soldQuantity", expression = "java(productService.getSoldQuantity(product.getProductID()))")
    ProductResponse toProductResponse(Product product, @Context ProductService productService);
}
