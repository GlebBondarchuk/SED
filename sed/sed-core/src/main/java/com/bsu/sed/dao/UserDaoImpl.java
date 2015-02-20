package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.model.SortOrder;
import com.bsu.sed.model.persistent.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author gbondarchuk
 */
@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    @Override
    public User getByLogin(String login) {
        return getByField("login", login);
    }

    @Override
    public User getByEmail(String email) {
        return getByField("email", email);
    }

    @Override
    public User getByUsername(String username) {
        return getByField("name", username);
    }

    private User getByField(String fieldName, String fieldValue) {
        Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.like(fieldName, fieldValue));
        return (User) criteria.uniqueResult();
    }

    private boolean existByField(String fieldName, String fieldValue) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("select count(*) from User where " + fieldName + "=:" + fieldName);
        query.setParameter(fieldName, fieldValue);
        long result = (long) query.uniqueResult();
        return result != 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> find(SortOrder order, int limit, int offset) {
        Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(User.class);
        criteria.addOrder(Order.asc("id"));
        criteria.setFirstResult(offset);
        criteria.setMaxResults(limit);
        return (List<User>) criteria.list();
    }

    @Override
    public boolean existByName(String name) {
        return existByField("name", name);
    }

    @Override
    public boolean existByLogin(String login) {
        return existByField("login", login);
    }
}
