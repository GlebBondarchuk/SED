package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.dao.generic.browsable.AbstractBrowsableDao;
import com.bsu.sed.dao.generic.browsable.BrowsableDao;
import com.bsu.sed.model.ContentKey;
import com.bsu.sed.model.persistent.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Repository;

/**
 * @author gbondarchuk
 */
@Repository
public class ContentDaoImpl extends AbstractBrowsableDao<Content> implements ContentDao {
}
