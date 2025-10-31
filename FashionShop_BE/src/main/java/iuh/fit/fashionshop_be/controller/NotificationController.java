/*
 * @ (#) NotificationController.java     1.0    29-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.controller;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:29-Oct-25
 * @version: 1.0
 */
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    @MessageMapping("/notify") // Client gửi message đến đây (nếu cần, ví dụ test)
    @SendTo("/topic/public") // Publish đến public topic (tùy chọn)
    public String sendNotification(String message) {
        return message;
    }
}