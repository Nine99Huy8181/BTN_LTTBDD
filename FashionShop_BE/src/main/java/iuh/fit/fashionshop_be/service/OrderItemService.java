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
import iuh.fit.fashionshop_be.dto.OrderDTO;
import iuh.fit.fashionshop_be.dto.OrderItemDTO;
import iuh.fit.fashionshop_be.mapper.OrderItemMapper;
import iuh.fit.fashionshop_be.mapper.OrderMapper;
import iuh.fit.fashionshop_be.model.Order;
import iuh.fit.fashionshop_be.model.OrderItem;
import iuh.fit.fashionshop_be.repository.OrderItemRepository;
import iuh.fit.fashionshop_be.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

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

    @Transactional(readOnly = true)
    public List<OrderItemDTO> getOrderItemsDTOByOrderId(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrder_OrderID(orderId);

        if (orderItems.isEmpty()) {
            throw new RuntimeException("No items found for order ID: " + orderId);
            // hoặc trả về empty list nếu muốn: return Collections.emptyList();
        }

        return orderItems.stream()
                .map(orderItemMapper::toDTO)
                .toList();
    }

    // Nếu bạn muốn trả về kèm thông tin đơn hàng luôn (thường dùng hơn)
    public OrderDTO getOrderDetailWithItems(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

        return orderMapper.toDTO(order); // đã include List<OrderItemDTO> nếu bạn thêm vào OrderDTO
    }

    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}