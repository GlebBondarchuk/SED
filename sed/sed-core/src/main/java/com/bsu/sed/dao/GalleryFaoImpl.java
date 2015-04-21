package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.dao.generic.browsable.AbstractBrowsableDao;
import com.bsu.sed.model.persistent.Gallery;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author gbondarchuk
 */
@Repository
public class GalleryFaoImpl extends AbstractDao<Gallery> implements GalleryDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<Gallery> find(int limit, int offset) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("SELECT gallery FROM Gallery gallery ORDER BY gallery.document.createdDate")
                .setMaxResults(limit)
                .setFirstResult(offset);
        return query.list();
    }

    @Override
    public long count() {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("SELECT count(gallery.id) FROM Gallery gallery");
        return (long) query.uniqueResult();
    }
}
