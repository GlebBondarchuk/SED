package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author gbondarchuk
 */
@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    @Override
    public User getByLogin(String login) {
        Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.like("login", login));
        return (User) criteria.uniqueResult();
    }
}
