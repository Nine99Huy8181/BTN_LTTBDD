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
import iuh.fit.fashionshop_be.dto.OrderCreateRequest;
import iuh.fit.fashionshop_be.dto.OrderItemRequest;
import iuh.fit.fashionshop_be.model.Customer;
import iuh.fit.fashionshop_be.model.Inventory;
import iuh.fit.fashionshop_be.model.Order;
import iuh.fit.fashionshop_be.model.OrderItem;
import iuh.fit.fashionshop_be.model.ProductVariant;
import iuh.fit.fashionshop_be.repository.CustomerRepository;
import iuh.fit.fashionshop_be.exception.AppException;
import iuh.fit.fashionshop_be.exception.ErrorCode;
import iuh.fit.fashionshop_be.repository.InventoryRepository;
import iuh.fit.fashionshop_be.repository.OrderItemRepository;
import iuh.fit.fashionshop_be.repository.OrderRepository;
import iuh.fit.fashionshop_be.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductVariantRepository productVariantRepository;
    private final InventoryRepository inventoryRepository;
    private final CustomerRepository customerRepository;

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

    @Transactional
    public Order createOrderFromRequest(OrderCreateRequest req, String userEmail) {
        // Resolve account by email -> then find customer by accountID
    // Find customer by matching account email via repository
    Customer customer = customerRepository.findAll().stream()
                .filter(c -> c.getAccount() != null && c.getAccount().getEmail().equals(userEmail))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer not found for account"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus("PENDING");
        order.setPaymentStatus("PENDING");
        order.setPaymentMethod(req.getPaymentMethod());
        order.setNotes(req.getNotes());
        order.setShippingFee(0f);
        if (req.getAddressID() != null) {
            order.setAddressID(req.getAddressID());
        }

        // Save order first to obtain ID
        Order saved = orderRepository.save(order);

        List<OrderItem> createdItems = new ArrayList<>();
        float total = 0f;

        for (OrderItemRequest it : req.getItems()) {
            ProductVariant variant = productVariantRepository.findById(it.getVariantID())
                    .orElseThrow(() -> new RuntimeException("Variant not found: " + it.getVariantID()));

            Inventory inv = inventoryRepository.findByVariantVariantID(variant.getVariantID());
            if (inv == null || inv.getQuantity() == null || inv.getQuantity() < it.getQuantity()) {
                // Throw AppException with INVENTORY_INSUFFICIENT so client receives 400 and message
                throw new AppException(ErrorCode.INVENTORY_INSUFFICIENT,
                        "Insufficient inventory for variant: " + variant.getVariantID());
            }

            float unitPrice = (variant.getProduct().getDiscountPrice() != null && variant.getProduct().getDiscountPrice() > 0)
                    ? variant.getProduct().getDiscountPrice()
                    : variant.getProduct().getBasePrice();

            int qty = it.getQuantity() != null ? it.getQuantity() : 0;
            float sub = unitPrice * qty;

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(saved);
            orderItem.setVariant(variant);
            orderItem.setQuantity(qty);
            orderItem.setUnitPrice(unitPrice);
            orderItem.setSubTotal(sub);

            createdItems.add(orderItem);

            // update inventory: decrement quantity, increment reserved could be used instead
            inv.setQuantity(inv.getQuantity() - qty);
            inventoryRepository.save(inv);

            total += sub;
        }

        // Set total and save
        saved.setTotalAmount(total);
        saved = orderRepository.save(saved);

        for (OrderItem oi : createdItems) {
            oi.setOrder(saved);
            orderItemRepository.save(oi);
        }

        return saved;
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