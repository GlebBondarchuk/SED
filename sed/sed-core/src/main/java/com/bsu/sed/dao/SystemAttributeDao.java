package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.GenericDao;
import com.bsu.sed.model.SystemAttribute;

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
    String get(String key);
}
