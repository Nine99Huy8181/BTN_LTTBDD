/*
 * @ (#) g.java     1.0    13-Nov-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:13-Nov-25
 * @version: 1.0
 */
@Data
@Builder
public class ExpoPushMessage {
    private String to;
    private String title;
    private String body;
    private Map<String, Object> data;
    private String sound;
}