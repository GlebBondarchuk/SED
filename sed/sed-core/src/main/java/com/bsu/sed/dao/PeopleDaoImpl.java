package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.model.persistent.People;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author gbondarchuk
 */
@Repository
public class PeopleDaoImpl extends AbstractDao<People> implements PeopleDao {
    @Override
    public People getByUsername(String username) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("from People where user.name like :username");
        query.setParameter("username", username);
        return (People) query.uniqueResult();
    }
}
