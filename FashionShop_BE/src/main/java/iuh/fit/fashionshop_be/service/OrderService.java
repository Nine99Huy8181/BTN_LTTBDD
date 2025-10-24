/*
 * @ (#) df.java     1.0    17-Oct-25
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
import iuh.fit.fashionshop_be.model.Order;
import iuh.fit.fashionshop_be.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getOrdersByCustomerId(Long customerID) {
        return orderRepository.findByCustomerCustomerID(customerID);
    }

    public List<Order> getOrdersByStatus(String orderStatus) {
        return orderRepository.findByOrderStatus(orderStatus);
    }

    public List<Order> getOrdersByPaymentStatus(String paymentStatus) {
        return orderRepository.findByPaymentStatus(paymentStatus);
    }

    public Order createOrder(Order order) {
        // Logic: tính totalAmount từ items, apply coupon, kiểm tra inventory
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order orderDetails) {
        Order order = getOrderById(id);
        order.setCustomer(orderDetails.getCustomer());
        order.setOrderDate(orderDetails.getOrderDate());
        order.setTotalAmount(orderDetails.getTotalAmount());
        order.setPaymentMethod(orderDetails.getPaymentMethod());
        order.setPaymentStatus(orderDetails.getPaymentStatus());
        order.setPaymentDate(orderDetails.getPaymentDate());
        order.setOrderStatus(orderDetails.getOrderStatus());
        order.setShippingAddress(orderDetails.getShippingAddress());
        order.setShippingFee(orderDetails.getShippingFee());
        order.setNotes(orderDetails.getNotes());
        return orderRepository.save(order);
    }
    public Order cancelOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));

        if (!order.getOrderStatus().equalsIgnoreCase("PENDING")) {
            throw new RuntimeException("Chỉ có thể hủy đơn hàng khi đang chờ xử lý (PENDING)");
        }

        order.setOrderStatus("CANCELED");
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}