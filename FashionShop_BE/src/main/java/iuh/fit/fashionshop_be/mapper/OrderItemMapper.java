/*
 * @ (#) f.java     1.0    25-Nov-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.mapper;

import iuh.fit.fashionshop_be.dto.OrderItemDTO;
import iuh.fit.fashionshop_be.model.OrderItem;
import iuh.fit.fashionshop_be.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:25-Nov-25
 * @version: 1.0
 */
@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    @Mapping(target = "productID", source = "variant.product.productID")
    @Mapping(target = "productName", source = "variant.product.name")
    @Mapping(target = "productImage", source = "variant.product.image") // hoặc lấy variant.images[0] nếu muốn
    @Mapping(target = "variantID", source = "variant.variantID")
    @Mapping(target = "sku", source = "variant.sku")
    @Mapping(target = "size", source = "variant.size")
    @Mapping(target = "color", source = "variant.color")
    @Mapping(target = "variantImages", source = "variant.images")
    @Mapping(target = "originalPrice", expression = "java(calculateOriginalPrice(orderItem))")
    OrderItemDTO toDTO(OrderItem orderItem);

    // Nếu bạn không muốn hiển thị giá gốc thì bỏ dòng originalPrice đi
    default Float calculateOriginalPrice(OrderItem orderItem) {
        Product product = orderItem.getVariant().getProduct();
        Float base = product.getDiscountPrice() != null ? product.getDiscountPrice() : product.getBasePrice();
        Float adjustment = orderItem.getVariant().getPriceAdjustment() != null ? orderItem.getVariant().getPriceAdjustment() : 0f;
        return base + adjustment;
    }

    // Dùng khi tạo OrderItem từ DTO (thường chỉ cần variantID + quantity)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "variant", ignore = true)
    @Mapping(target = "unitPrice", ignore = true)
    @Mapping(target = "subTotal", ignore = true)
    OrderItem toEntity(OrderItemDTO dto);
}