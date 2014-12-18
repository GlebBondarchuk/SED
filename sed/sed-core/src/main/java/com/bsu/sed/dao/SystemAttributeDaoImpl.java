package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.model.SystemAttribute;
import com.bsu.sed.model.SystemAttributeKey;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author gbondarchuk
 */
@Repository
public class SystemAttributeDaoImpl extends AbstractDao<SystemAttribute> implements SystemAttributeDao {

    @Override
    public String get(SystemAttributeKey key) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("select system.value from System system where system.key = :key");
        query.setParameter("key", key.getName());
        return (String) query.uniqueResult();
    }
}
