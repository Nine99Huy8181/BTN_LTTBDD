/*
 * @ (#) d.java     1.0    17-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.controller;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:17-Oct-25
 * @version: 1.0
 */
import iuh.fit.fashionshop_be.dto.OrderCreateRequest;
import iuh.fit.fashionshop_be.dto.OrderDTO;
import iuh.fit.fashionshop_be.model.Order;
import iuh.fit.fashionshop_be.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        // Force lazy load of orderItems
        if (order.getOrderItems() != null) {
            order.getOrderItems().size();
        }
        return ResponseEntity.ok(order);
    }

    @GetMapping("/orders/customer/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(customerId));
    }

    @PostMapping("/orders")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Order> createOrder(@RequestBody OrderCreateRequest req, Principal principal) {
        Order created = orderService.createOrderFromRequest(req, principal.getName());
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/orders/{id}/confirm")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER')")
    public ResponseEntity<Order> confirmOrder(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        order.setOrderStatus("CONFIRMED");
        Order updated = orderService.updateOrder(id, order);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/orders/{id}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long id) {
        Order canceled = orderService.cancelOrder(id);
        return ResponseEntity.ok(canceled);
    }

    @PutMapping("/orders/{orderId}/status")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER')")
    public ResponseEntity<OrderDTO> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestBody Map<String, String> body,
            Authentication authentication) {

        String status = body.get("status");
        if (status == null || status.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        OrderDTO updatedOrder = orderService.updateOrderStatus(orderId, status, authentication);
        return ResponseEntity.ok(updatedOrder);
    }

    @GetMapping("/page-orders")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER')")
    public ResponseEntity<Page<OrderDTO>> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String status) { // <-- THÊM CÁI NÀY

        Page<OrderDTO> orderPage = orderService.getOrdersPaginated(page, size, status);
        return ResponseEntity.ok(orderPage);
    }
}