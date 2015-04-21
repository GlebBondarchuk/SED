package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.browsable.AbstractBrowsableDao;
import com.bsu.sed.model.TextKey;
import com.bsu.sed.model.persistent.Text;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Repository;

/**
 * @author gbondarchuk
 */
@Repository
public class TextDaoImpl extends AbstractBrowsableDao<Text> implements TextDao {
    @Autowired
    private CacheManager cacheManager;

    @Override
    public Text get(TextKey key) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("SELECT text FROM Text text WHERE text.key = :textKey")
                .setParameter("textKey", key);
        return (Text)query.uniqueResult();
    }

    @Override
    public void evict(TextKey textKey) {
        Cache cache = cacheManager.getCache("textCache");
        cache.evict(textKey);
    }
}
