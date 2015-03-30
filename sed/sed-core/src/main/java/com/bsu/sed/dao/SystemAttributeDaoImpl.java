package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.model.SystemAttributeKey;
import com.bsu.sed.model.persistent.SystemAttribute;
import com.bsu.sed.utils.DateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Date;

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
    private SystemAttribute getValue(SystemAttributeKey key) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("select system from SystemAttribute system where system.key = :key");
        query.setParameter("key", key);
        return (SystemAttribute) query.uniqueResult();
    }

    @Override
    public String get(SystemAttributeKey key) {
        return getValue(key).getValue();
    }

    @Override
    public int getInt(SystemAttributeKey key) {
        String value = getValue(key).getValue();
        return Integer.parseInt(value);
    }

    @Override
    public boolean getBoolean(SystemAttributeKey key) {
        String value = getValue(key).getValue();
        return Boolean.parseBoolean(value);
    }

    @Override
    public Date getDate(SystemAttributeKey key) {
        String value = getValue(key).getValue();
        return DateUtils.parse(value);
    }

    @Override
    public SystemAttribute getAttribute(SystemAttributeKey key) {
        return getValue(key);
    }
}
