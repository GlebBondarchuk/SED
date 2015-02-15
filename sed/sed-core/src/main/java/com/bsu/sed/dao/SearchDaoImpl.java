package com.bsu.sed.dao;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author gbondarchuk
 */
@Repository
public class SearchDaoImpl implements SearchDao {
    @PersistenceContext
    protected EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<Long> find(String search) {
        Session session = em.unwrap(Session.class);
        SQLQuery sqlQuery = session.createSQLQuery("select content.id as id from sed_content content where CAST(content.content AS CHAR(10000) CHARACTER SET utf8) like :search")
                .addScalar("id", LongType.INSTANCE);
        sqlQuery.setParameter("search", "%" + search + "%");

        return (List<Long>) sqlQuery.list();
    }
}
