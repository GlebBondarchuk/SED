package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.GenericDao;
import com.bsu.sed.model.ContentKey;
import com.bsu.sed.model.persistent.Primary;

/**
 * @author gbondarchuk
 */
public interface PrimaryDao extends GenericDao<Primary, Long> {
    Primary get(ContentKey key);
}
