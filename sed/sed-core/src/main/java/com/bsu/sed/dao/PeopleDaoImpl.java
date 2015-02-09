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
    public People getByUserId(Long id) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("from People where user.id like :id");
        query.setParameter("id", id);
        return (People) query.uniqueResult();
    }
}
