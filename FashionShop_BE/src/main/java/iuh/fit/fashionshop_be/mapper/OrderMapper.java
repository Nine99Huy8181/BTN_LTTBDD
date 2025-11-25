/*
 * @ (#) f.java     1.0    13-Nov-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.mapper;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:13-Nov-25
 * @version: 1.0
 */
import iuh.fit.fashionshop_be.dto.OrderDTO;
import iuh.fit.fashionshop_be.model.Order;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "customerID", source = "customer.customerID")
    @Mapping(target = "customerName", source = "customer.fullName")
    @Mapping(target = "shippingAddress", source = "shippingAddress.fullAddress") // ← ĐÚNG
    @Mapping(target = "couponCode", source = "coupon.code")
    @Mapping(target = "orderItems", source = "orderItems")
    OrderDTO toDTO(Order order);


    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "shippingAddress", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    @Mapping(target = "shipping", ignore = true)
    @Mapping(target = "coupon", ignore = true)
    Order toEntity(OrderDTO dto);
}