package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.GenericDao;
import com.bsu.sed.model.persistent.Notification;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface NotificationDao extends GenericDao<Notification, Long> {
    List<Notification> getNotifications(Long toId);

    long getCount(Long toId);

    void remove(Long id, Long toId);
}
