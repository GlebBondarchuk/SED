package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.GenericDao;
import com.bsu.sed.dao.generic.browsable.BrowsableDao;
import com.bsu.sed.model.TextKey;
import com.bsu.sed.model.persistent.Text;

/**
 * @author gbondarchuk
 */
public interface TextDao extends BrowsableDao<Text> {
    Text get(TextKey key);

    void evict(TextKey id);
}
