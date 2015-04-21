package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.model.persistent.Notification;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author gbondarchuk
 */
@Repository
public class NotificationDaoImpl extends AbstractDao<Notification> implements NotificationDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<Notification> getNotifications(Long toId) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("SELECT nottification FROM Notification nottification WHERE nottification.to.id = :toId ORDER BY sendDate DESC")
                .setParameter("toId", toId);
        return query.list();
    }

    @Override
    public long getCount(Long toId) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("SELECT count(nottification.id) FROM Notification nottification WHERE nottification.to.id = :toId")
                .setParameter("toId", toId);
        return (long) query.uniqueResult();
    }

    @Override
    public void remove(Long id, Long toId) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("DELETE FROM Notification nottification " +
                "WHERE nottification.id=:id and nottification.to.id = :toId")
                .setParameter("toId", toId)
                .setParameter("id", id);
        query.executeUpdate();
    }
}
