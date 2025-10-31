/*
 * @ (#) N.java     1.0    29-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.model;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:29-Oct-25
 * @version: 1.0
 */
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NotificationID")
    private Long notificationID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerID")
    private Customer customer; // nullable: vì có thể là thông báo chung hệ thống

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Message", columnDefinition = "TEXT")
    private String message;

    @Column(name = "Type", length = 50)
    private String type;
    // ORDER, SYSTEM, SHIPPING, PROMOTION...

    @Column(name = "Status", length = 50)
    private String status;
    // SENT, DELIVERED, FAILED (for push logs if needed)

    @Column(name = "IsRead")
    private Boolean isRead;

    @Column(name = "CreatedDate")
    private LocalDateTime createdDate;

    @Column(name = "ReadDate")
    private LocalDateTime readDate; // nullable

    @Column(name = "DeepLink", length = 500)
    private String deepLink; // app://order/123

    @Column(name = "ImageUrl", length = 500)
    private String imageUrl;
}
