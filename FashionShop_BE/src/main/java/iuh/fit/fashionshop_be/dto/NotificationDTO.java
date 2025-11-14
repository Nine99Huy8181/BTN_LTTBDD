/*
 * @ (#) NotificationDTO.java     1.0    13-Nov-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.dto;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:13-Nov-25
 * @version: 1.0
 */
// src/main/java/iuh/fit/fashionshop_be/dto/NotificationDTO.java
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private Long notificationID;
    private Long customerID;
    private String title;
    private String message;
    private String type; // ORDER, PROMOTION, SYSTEM...
    private Boolean isRead;
    private LocalDateTime createdDate;
    private LocalDateTime readDate;
    private String deepLink; // app://order/123
    private String imageUrl;
}