package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.model.persistent.News;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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
        return (Long) query.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<News> getSortedNews(int limit, int offset, String searchQuery) {
        Session session = em.unwrap(Session.class);
        Query query;
        if (StringUtils.isBlank(searchQuery)) {
            query = session.createQuery("select news from News news order by news.createdDate desc");
        } else {
            query = session.createQuery("select news from News news where news.content.name like :query order by news.createdDate desc");
            query.setParameter("query", "%" + searchQuery + "%");
        }

        return query.setMaxResults(limit)
                .setFirstResult(offset)
                .list();
    }

    @Override
    public long count(String searchQuery) {
        Session session = em.unwrap(Session.class);
        Query query;
        if(StringUtils.isBlank(searchQuery)) {
            query = session.createQuery("select count(news.id) from News news");
        } else {
            query = session.createQuery("select count(news.id) from News news where news.content.name like :query");
            query.setParameter("query", "%" + searchQuery + "%");
        }
        return (long) query.uniqueResult();
    }

    @Override
    public void deleteNewsBeforeDate(Date date) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("delete from News where createdDate < :date");
        query.setParameter("date", date);
        query.executeUpdate();
    }
}
