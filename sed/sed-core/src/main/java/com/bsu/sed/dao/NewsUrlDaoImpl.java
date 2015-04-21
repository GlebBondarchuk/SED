package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.dao.generic.browsable.AbstractBrowsableDao;
import com.bsu.sed.model.persistent.NewsUrl;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author gbondarchuk
 */
@Repository
public class NewsUrlDaoImpl extends AbstractBrowsableDao<NewsUrl> implements NewsUrlDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<NewsUrl> findEnabled() {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("from NewsUrl newUrl where newUrl.disabled = false");
        return (List<NewsUrl>) query.list();
    }

    @Override
    public void disable(Long id) {
        changeStatus(id, false);
    }

    @Override
    public void enable(Long id) {
        changeStatus(id, true);
    }

    private void changeStatus(Long id, boolean enable) {
        Session session = em.unwrap(Session.class);
        SQLQuery query = session.createSQLQuery("UPDATE sed_news_url SET disabled=:disabled WHERE id=:id");
        query.setParameter("id", id);
        query.setParameter("disabled", !enable);
        query.executeUpdate();
    }
}
