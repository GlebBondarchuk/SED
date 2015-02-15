package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.model.ContentKey;
import com.bsu.sed.model.persistent.Primary;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author gbondarchuk
 */
@Repository
public class PrimaryDaoImpl extends AbstractDao<Primary> implements PrimaryDao {
    @Override
    public Primary get(ContentKey key) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("select primary from Primary primary where primary.key = :key");
        query.setParameter("key", key);
        return (Primary) query.uniqueResult();
    }
}
