/*
 * @ (#) f.java     1.0    13-Nov-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.controller;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:13-Nov-25
 * @version: 1.0
 */
import iuh.fit.fashionshop_be.config.security.CustomerDetails;
import iuh.fit.fashionshop_be.dto.NotificationDTO;
import iuh.fit.fashionshop_be.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    // Lấy danh sách thông báo của customer
    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasRole('CUSTOMER') and #customerId == authentication.principal.customerID")
    public ResponseEntity<List<NotificationDTO>> getCustomerNotifications(
            @PathVariable Long customerId) {
        List<NotificationDTO> notifications = notificationService.getNotificationsByCustomer(customerId);
        return ResponseEntity.ok(notifications);
    }

    // Đánh dấu đã đọc
    @PutMapping("/{id}/read")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Void> markAsRead(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomerDetails userDetails) {
        notificationService.markAsRead(id);
        return ResponseEntity.ok().build();
    }

    // Xóa thông báo
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Void> deleteNotification(
            @PathVariable Long id,
            Authentication authentication) {
        CustomerDetails userDetails = (CustomerDetails) authentication.getPrincipal();
        notificationService.deleteNotification(id, userDetails.getCustomerID());
        return ResponseEntity.ok().build();
    }

    // Admin: Lấy danh sách thông báo hệ thống/admin
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER')")
    public ResponseEntity<List<NotificationDTO>> getAdminNotifications() {
        return ResponseEntity.ok(notificationService.getAdminNotifications());
    }

    @PutMapping("/admin/{id}/read")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER')")
    public ResponseEntity<Void> markAdminAsRead(@PathVariable Long id) {
        notificationService.markAdminAsRead(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER')")
    public ResponseEntity<Void> deleteAdminNotification(@PathVariable Long id) {
        notificationService.deleteAdminNotification(id);
        return ResponseEntity.ok().build();
    }
}