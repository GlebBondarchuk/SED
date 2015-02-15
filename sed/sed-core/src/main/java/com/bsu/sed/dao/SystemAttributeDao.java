package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.GenericDao;
import com.bsu.sed.model.persistent.SystemAttribute;
import com.bsu.sed.model.SystemAttributeKey;

/**
 * @author gbondarchuk
 */
public interface SystemAttributeDao extends GenericDao<SystemAttribute, Long> {

    /**
     * Get system attribute by key.
     *
     * @param key Key value.
     * @return Attribute value.
     */
    String get(SystemAttributeKey key);

    /**
     * Get integer system attribute value.
     *
     * @param key Key value.
     * @return Integer attribute value.
     */
    int getInt(SystemAttributeKey key);

    /**
     * Get boolean system attribute value.
     *
     * @param key Key value.
     * @return boolean attribute value.
     */
    boolean getBoolean(SystemAttributeKey key);
}
