/*
 * @ (#) Product.java     1.0    25-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.mapper;

import iuh.fit.fashionshop_be.dto.response.ProductVariantResponse;
import iuh.fit.fashionshop_be.service.ProductService;
import iuh.fit.fashionshop_be.service.ProductVariantService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:25-Oct-25
 * @version: 1.0
 */
@Mapper(componentModel = "spring")
public interface ProductVariantMapper {
    @Mapping(target = "productID", source = "product.productID")
    @Mapping(target = "validQuantity", expression = "java(productVariantService.getAvailableStockByVariant(productVariant.getVariantID()))")
    @Mapping(target = "reservedQuantity", expression = "java(productVariantService.getReservedQuantityByVariant(productVariant.getVariantID()))")
    ProductVariantResponse toProductVariantResponse(
            iuh.fit.fashionshop_be.model.ProductVariant productVariant,
            @Context ProductVariantService productVariantService
    );

    List<ProductVariantResponse> toProductVariantResponseList(
            List<iuh.fit.fashionshop_be.model.ProductVariant> productVariants,
            @Context ProductVariantService productVariantService
    );
}