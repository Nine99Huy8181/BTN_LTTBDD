/*
 * @ (#) f.java     1.0    17-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.service;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:17-Oct-25
 * @version: 1.0
 */
import iuh.fit.fashionshop_be.model.OrderItem;
import iuh.fit.fashionshop_be.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id).orElseThrow(() -> new RuntimeException("OrderItem not found"));
    }

    public List<OrderItem> getOrderItemsByOrderId(Long orderID) {
        return orderItemRepository.findByOrderOrderID(orderID);
    }

    public List<OrderItem> getOrderItemsByVariantId(Long variantID) {
        return orderItemRepository.findByVariantVariantID(variantID);
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
        // Logic: update subtotal = quantity * unitPrice
        return orderItemRepository.save(orderItem);
    }

    public OrderItem updateOrderItem(Long id, OrderItem itemDetails) {
        OrderItem item = getOrderItemById(id);
        item.setOrder(itemDetails.getOrder());
        item.setVariant(itemDetails.getVariant());
        item.setQuantity(itemDetails.getQuantity());
        item.setUnitPrice(itemDetails.getUnitPrice());
        item.setSubTotal(itemDetails.getSubTotal());
        return orderItemRepository.save(item);
    }

    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}