package com.bsu.sed.service;

import com.bsu.sed.model.persistent.Notification;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface NotificationService {
    List<Notification> getNotifications(Long toId);

    void sendNotification(Long fromUserId, List<Long> toUserIds, String message);

    long getCount(Long toId);

    void remove(Long id, Long toId);
}
