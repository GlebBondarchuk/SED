package com.bsu.sed.service;

import com.bsu.sed.dao.NotificationDao;
import com.bsu.sed.dao.UserDao;
import com.bsu.sed.model.persistent.Notification;
import com.bsu.sed.model.persistent.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author gbondarchuk
 */
@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationDao notificationDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<Notification> getNotifications(Long toId) {
        return notificationDao.getNotifications(toId);
    }

    @Override
    public void sendNotification(Long fromUserId, List<Long> toUserIds, String message) {
        for (Long toUserId : toUserIds) {
            Notification notification = new Notification();
            User from = userDao.load(fromUserId);
            User to = userDao.load(toUserId);
            notification.setFrom(from);
            notification.setTo(to);
            notification.setMessage(message);
            notificationDao.create(notification);
        }
    }

    @Override
    public long getCount(Long toId) {
        return notificationDao.getCount(toId);
    }

    @Override
    public void remove(Long id, Long toId) {
        notificationDao.remove(id, toId);
    }
}
