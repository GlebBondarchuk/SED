package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.model.persistent.News;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author gbondarchuk
 */
@Repository
public class NewsDaoImpl extends AbstractDao<News> implements NewsDao {
    @Override
    public Long getNewsIdFromContentId(Long contentId) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("SELECT news.id from News news WHERE news.content.id = :id");
        query.setParameter("id", contentId);
        return (Long)query.uniqueResult();
    }
}
