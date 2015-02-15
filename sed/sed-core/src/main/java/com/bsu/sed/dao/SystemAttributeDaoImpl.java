package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.model.SystemAttributeKey;
import com.bsu.sed.model.persistent.SystemAttribute;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author gbondarchuk
 */
@Repository
public class SystemAttributeDaoImpl extends AbstractDao<SystemAttribute> implements SystemAttributeDao {

    /**
     * Get attribute value.
     *
     * @param key Attribute identifier.
     * @return Attribute value as string.
     */
    private String getValue(SystemAttributeKey key) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("select system.value from SystemAttribute system where system.key = :key");
        query.setParameter("key", key);
        return (String) query.uniqueResult();
    }

    @Override
    public String get(SystemAttributeKey key) {
        return getValue(key);
    }

    @Override
    public int getInt(SystemAttributeKey key) {
        String value = getValue(key);
        return Integer.parseInt(value);
    }

    @Override
    public boolean getBoolean(SystemAttributeKey key) {
        String value = getValue(key);
        return Boolean.parseBoolean(value);
    }
}
