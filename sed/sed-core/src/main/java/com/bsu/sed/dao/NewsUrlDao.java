package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.GenericDao;
import com.bsu.sed.dao.generic.browsable.BrowsableDao;
import com.bsu.sed.model.persistent.NewsUrl;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface NewsUrlDao extends BrowsableDao<NewsUrl> {
    List<NewsUrl> findEnabled();

    void disable(Long id);

    void enable(Long id);
}
