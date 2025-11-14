package iuh.fit.fashionshop_be.service;/*
 * @ (#) f.java     1.0    13-Nov-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */


/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:13-Nov-25
 * @version: 1.0
 */
import iuh.fit.fashionshop_be.dto.NotificationDTO;
import iuh.fit.fashionshop_be.mapper.NotificationMapper;
import iuh.fit.fashionshop_be.model.Customer;
import iuh.fit.fashionshop_be.model.Notification;
import iuh.fit.fashionshop_be.repository.CustomerRepository;
import iuh.fit.fashionshop_be.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.messaging.simp.user.SimpSubscription;
import org.springframework.messaging.simp.user.SimpSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final CustomerRepository customerRepository;
    private final NotificationMapper notificationMapper;
    private final SimpMessagingTemplate messagingTemplate;
        private final SimpUserRegistry simpUserRegistry;
    private final ExpoPushService expoPushService;
        private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    // Gửi thông báo cá nhân
    @Transactional
    public void sendPersonalNotification(Long customerId, String title, String message,
                                         String type, String deepLink, String imageUrl) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Notification notification = Notification.builder()
                .customer(customer)
                .title(title)
                .message(message)
                .type(type)
                .status("SENT")
                .isRead(false)
                .createdDate(LocalDateTime.now())
                .deepLink(deepLink)
                .imageUrl(imageUrl)
                .build();

        notification = notificationRepository.save(notification);
        NotificationDTO dto = notificationMapper.toDTO(notification);

        // 1. Gửi WebSocket
        messagingTemplate.convertAndSendToUser(
                customerId.toString(),
                "/queue/notifications",
                dto
        );

        // 2. Gửi Push nếu có token
        if (customer.getExpoPushToken() != null && !customer.getExpoPushToken().isBlank()) {
            expoPushService.sendPushNotification(
                    customer.getExpoPushToken(),
                    title,
                    message,
                    Map.of(
                            "notificationId", notification.getNotificationID(),
                            "deepLink", deepLink,
                            "type", type
                    )
            );
        }
    }

    // Gửi cho tất cả admin (đơn hàng mới, khuyến mãi...)
    public void sendToAllAdmins(String title, String message, String deepLink) {
                // Persist a system/admin notification (customer = null) so admins can fetch unread later
                Notification notification = Notification.builder()
                                .customer(null)
                                .title(title)
                                .message(message)
                                .type("ADMIN")
                                .status("SENT")
                                .isRead(false)
                                .createdDate(LocalDateTime.now())
                                .deepLink(deepLink)
                                .build();

                notification = notificationRepository.save(notification);
                NotificationDTO dto = notificationMapper.toDTO(notification);

                // Count how many connected sessions have subscribed to the admin topic (for debugging)
                int adminSubs = 0;
                try {
                        for (SimpUser user : simpUserRegistry.getUsers()) {
                                for (SimpSession session : user.getSessions()) {
                                        for (SimpSubscription sub : session.getSubscriptions()) {
                                                if ("/topic/admin/notifications".equals(sub.getDestination())) {
                                                        adminSubs++;
                                                }
                                        }
                                }
                        }
                } catch (Exception ex) {
                        // non-fatal; just log
                        logger.warn("Failed to inspect SimpUserRegistry for admin subscriptions", ex);
                }

                logger.info("Broadcasting admin notification (subscribers={}): {}", adminSubs, dto);

                messagingTemplate.convertAndSend("/topic/admin/notifications", dto);

                // Nếu cần push cho admin → thêm logic lấy token admin
    }

        // Get persisted admin/system notifications
        public List<NotificationDTO> getAdminNotifications() {
                return notificationMapper.toDTOList(notificationRepository.findByCustomerIsNullOrderByCreatedDateDesc());
        }

        @Transactional
        public void markAdminAsRead(Long notificationId) {
                Notification notification = notificationRepository.findById(notificationId)
                                .orElseThrow(() -> new RuntimeException("Notification not found"));

                if (notification.getIsRead() != null && notification.getIsRead()) return;

                notification.setIsRead(true);
                notification.setReadDate(LocalDateTime.now());
                notificationRepository.save(notification);

                // notify admins about the read update
                messagingTemplate.convertAndSend("/topic/admin/notifications/read", notificationMapper.toDTO(notification));
        }

        @Transactional
        public void deleteAdminNotification(Long notificationId) {
                Notification notification = notificationRepository.findById(notificationId)
                                .orElseThrow(() -> new RuntimeException("Notification not found"));
                if (notification.getCustomer() != null) {
                        throw new RuntimeException("Not an admin/system notification");
                }

                notificationRepository.delete(notification);
                messagingTemplate.convertAndSend("/topic/admin/notifications/delete", notificationId);
        }

    // Lấy danh sách thông báo
    public List<NotificationDTO> getNotificationsByCustomer(Long customerId) {
        return notificationMapper.toDTOList(
                notificationRepository.findByCustomerCustomerIDOrderByCreatedDateDesc(customerId)
        );
    }

    @Transactional
    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        if (notification.getIsRead() != null && notification.getIsRead()) {
            return; // đã đọc rồi
        }

        notification.setIsRead(true);
        notification.setReadDate(LocalDateTime.now());
        notificationRepository.save(notification);

        // Gửi update realtime
        messagingTemplate.convertAndSendToUser(
                notification.getCustomer().getCustomerID().toString(),
                "/queue/notifications/read",
                notificationMapper.toDTO(notification)
        );
    }

    // Xóa + kiểm tra quyền
    @Transactional
    public void deleteNotification(Long notificationId, Long customerId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        if (!notification.getCustomer().getCustomerID().equals(customerId)) {
            throw new RuntimeException("You can only delete your own notifications");
        }

        notificationRepository.delete(notification);

        // Gửi xóa realtime
        messagingTemplate.convertAndSendToUser(
                customerId.toString(),
                "/queue/notifications/delete",
                notificationId
        );
    }
}