/*
 * @ (#) ExpoPushService.java     1.0    13-Nov-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.service;

import iuh.fit.fashionshop_be.dto.ExpoPushMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:13-Nov-25
 * @version: 1.0
 */
@Service
public class ExpoPushService {

    private static final String EXPO_PUSH_URL = "https://exp.host/--/api/v2/push/send";

    @Value("${expo.push.chunk.size:100}")
    private int chunkSize;

    public void sendPushNotification(String expoPushToken, String title, String body, Map<String, Object> data) {
        ExpoPushMessage message = ExpoPushMessage.builder()
                .to(expoPushToken)
                .title(title)
                .body(body)
                .data(data)
                .sound("default")
                .build();

        sendMessages(List.of(message));
    }

    private void sendMessages(List<ExpoPushMessage> messages) {
        List<List<ExpoPushMessage>> chunks = chopped(messages, chunkSize);
        for (List<ExpoPushMessage> chunk : chunks) {
            sendChunk(chunk);
        }
    }

    private void sendChunk(List<ExpoPushMessage> chunk) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            HttpEntity<List<ExpoPushMessage>> entity = new HttpEntity<>(chunk, headers);
            restTemplate.postForEntity(EXPO_PUSH_URL, entity, String.class);
        } catch (Exception e) {
            // Log lỗi, không throw để không làm hỏng flow chính
            System.err.println("Expo push failed: " + e.getMessage());
        }
    }

    private <T> List<List<T>> chopped(List<T> list, final int L) {
        List<List<T>> parts = new ArrayList<>();
        final int N = list.size();
        for (int i = 0; i < N; i += L) {
            parts.add(new ArrayList<>(list.subList(i, Math.min(N, i + L))));
        }
        return parts;
    }
}
