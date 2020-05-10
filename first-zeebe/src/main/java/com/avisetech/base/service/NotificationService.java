package com.avisetech.base.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {
    public void pushNotification(String message) {
        log.info("Notification >> {}", message);
    }
}
