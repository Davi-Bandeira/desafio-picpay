package com.desafio.picpay.service;

import com.desafio.picpay.entity.Transaction;
import com.desafio.picpay.producer.NotificationProducer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);
    private final NotificationProducer notificationProducer;

    public void notify(Transaction transaction) {
        LOGGER.info("notifying transaction {}...", transaction);

        notificationProducer.sendNotification(transaction);
    }
}
