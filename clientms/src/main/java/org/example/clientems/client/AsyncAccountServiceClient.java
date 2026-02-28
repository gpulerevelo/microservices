package org.example.clientems.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AsyncAccountServiceClient {

    private static final Logger log = LoggerFactory.getLogger(AsyncAccountServiceClient.class);

    private final RestTemplate restTemplate;
    private final String accountServiceUrl;

    public AsyncAccountServiceClient(RestTemplate restTemplate,
                                     @Value("${account-service.url}") String accountServiceUrl) {
        this.restTemplate = restTemplate;
        this.accountServiceUrl = accountServiceUrl;
    }

    @Async("asyncExecutor")
    public void notifyClientCreated(Long clientId) {
        try {
            String url = accountServiceUrl + "/api/accounts/client-notifications/created";
            Map<String, Object> payload = new HashMap<>();
            payload.put("clientId", clientId);
            restTemplate.postForObject(url, payload, Void.class);
            log.info("Successfully notified account-service about new client: {}", clientId);
        } catch (Exception e) {
            log.error("Failed to notify account-service about client {}: {}", clientId, e.getMessage());
        }
    }
}


