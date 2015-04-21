package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.dao.generic.browsable.AbstractBrowsableDao;
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
public class NewsDaoImpl extends AbstractBrowsableDao<News> implements NewsDao {

    public static final int CATEGORIES_COUNT = 8;

    @Override
    public Long getNewsIdFromContentId(Long contentId) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("SELECT news.id from News news WHERE news.content.id = :id");
        query.setParameter("id", contentId);
        return (Long) query.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<News> find(int limit, int offset, String searchQuery, String category) {
        Session session = em.unwrap(Session.class);
        Query query;
        if (StringUtils.isBlank(searchQuery)) {
            if (StringUtils.isNotBlank(category)) {
                query = session.createQuery("select news from News news where category = :category order by fixed desc, news.createdDate desc");
                query.setParameter("category", category);
            } else {
                query = session.createQuery("select news from News news order by fixed desc, news.createdDate desc");
            }
        } else {
            if (StringUtils.isNotBlank(category)) {
                query = session.createQuery("select news from News news where news.content.name like :query and category = :category order by fixed desc, news.createdDate desc");
                query.setParameter("query", "%" + searchQuery + "%");
                query.setParameter("category", category);
            } else {
                query = session.createQuery("select news from News news where news.content.name like :query order by fixed desc, news.createdDate desc");
                query.setParameter("query", "%" + searchQuery + "%");
            }
        }

        return query.setMaxResults(limit)
                .setFirstResult(offset)
                .list();
    }

    @Override
    public long count(String searchQuery, String category) {
        Session session = em.unwrap(Session.class);
        Query query;
        if (StringUtils.isBlank(searchQuery)) {
            if (StringUtils.isNotBlank(category)) {
                query = session.createQuery("select count(news.id) from News news where category = :category order by news.createdDate desc");
                query.setParameter("category", category);
            } else {
                query = session.createQuery("select count(news.id) from News news order by news.createdDate desc");
            }
        } else {
            if (StringUtils.isNotBlank(category)) {
                query = session.createQuery("select count(news.id) from News news where news.content.name like :query and category = :category order by news.createdDate desc");
                query.setParameter("query", "%" + searchQuery + "%");
                query.setParameter("category", category);
            } else {
                query = session.createQuery("select count(news.id) from News news where news.content.name like :query order by news.createdDate desc");
                query.setParameter("query", "%" + searchQuery + "%");
            }
        }
        return (long) query.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public int deleteNewsToDate(Date to) {
        Session session = em.unwrap(Session.class);
        List<Long> ids = session.createQuery("select news.id from News news where news.createdDate < :to and fixed=false")
                .setParameter("to", to)
                .list();
        for (Long id : ids) {
            delete(id);
        }
        return ids.size();
    }

    @Override
    public boolean exists(String name) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("select id from News news where news.content.name = :name").setMaxResults(1);
        query.setParameter("name", name);
        return query.uniqueResult() != null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> getNewsCategories() {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("select news.category from News news group by news.category order by (news.creator.name), count(news.category) DESC, news.createdDate")
                .setMaxResults(CATEGORIES_COUNT);
        return query.list();
    }

    @Override
    public void fix(boolean fix, Long id) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("update News set fixed=:fixed where id=:id");
        query.setParameter("id", id);
        query.setParameter("fixed", fix);
        query.executeUpdate();
    }
}
