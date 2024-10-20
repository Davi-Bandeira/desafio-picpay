package com.desafio.picpay.consumer;

import com.desafio.picpay.entity.Notification;
import com.desafio.picpay.entity.Transaction;
import com.desafio.picpay.exception.NotificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Service
public class NotificationConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationConsumer.class);
    private final RestClient restClient;

    public NotificationConsumer(RestClient.Builder builder) {
        this.restClient = builder.baseUrl(
                        "https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6")
                .build();
    }

    @KafkaListener(topics = "transaction-notification", groupId = "picpay-desafio-backend")
    public void receiveNotification(Transaction transaction) {
        LOGGER.info("notifying transaction {}...", transaction);

        var response = restClient.get().retrieve().toEntity(Notification.class);
        var message = Objects.requireNonNull(response.getBody()).getMessage();

        if (response.getStatusCode().isError() || Boolean.TRUE.equals(message))
            throw new NotificationException("Error notifying transaction " + transaction);

        LOGGER.info("notification has been sent {}...", response.getBody());
    }
}
