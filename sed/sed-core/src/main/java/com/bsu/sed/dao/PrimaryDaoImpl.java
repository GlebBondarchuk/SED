package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.model.ContentKey;
import com.bsu.sed.model.persistent.Primary;
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
public class PrimaryDaoImpl extends AbstractDao<Primary> implements PrimaryDao {
    @Autowired
    private CacheManager cacheManager;

    @Override
    public Primary get(ContentKey key) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("select primary from Primary primary where primary.key = :key");
        query.setParameter("key", key);
        return (Primary) query.uniqueResult();
    }

    @Override
    public ContentKey get(Long contentId) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("select primary.key from Primary primary where primary.content.id = :contentId")
                .setParameter("contentId", contentId);
        Object result = query.uniqueResult();
        if(result == null) {
            return null;
        }
        return (ContentKey) result;
    }

    @Override
    public void evict(ContentKey key) {
        Cache cache = cacheManager.getCache("primaryCache");
        cache.evict(key);
    }
}
