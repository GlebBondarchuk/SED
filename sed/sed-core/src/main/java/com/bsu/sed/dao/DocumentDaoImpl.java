package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.dao.generic.browsable.AbstractBrowsableDao;
import com.bsu.sed.model.Role;
import com.bsu.sed.model.persistent.Document;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author gbondarchuk
 */
@Repository
public class DocumentDaoImpl extends AbstractBrowsableDao<Document> implements DocumentDao {
    @Override
    public boolean exists(String name) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("SELECT count(*) FROM Document WHERE name = :name");
        query.setParameter("name", name);
        return (long) query.uniqueResult() != 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Document> find(int limit, int offset) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("SELECT doc FROM Document doc")
                .setMaxResults(limit)
                .setFirstResult(offset);
        return query.list();
    }

    @Override
    public Role getRole(String fileName) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("SELECT doc.role as role FROM Document doc WHERE doc.name = :fileName");
        query.setParameter("fileName", fileName);
        return (Role)query.uniqueResult();
    }
}
