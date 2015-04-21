package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.model.persistent.People;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author gbondarchuk
 */
@Repository
public class PeopleDaoImpl extends AbstractDao<People> implements PeopleDao {
    @Override
    public People getByLogin(String login) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("from People people where people.user.login = :login");
        query.setParameter("login", login);
        return (People) query.uniqueResult();
    }

    @Override
    public Long getUserIdByContentId(Long id) {
        Session session = em.unwrap(Session.class);
        SQLQuery query = session.createSQLQuery("SELECT " +
                "people.user_id AS id " +
                "FROM sed_people people " +
                "LEFT JOIN sed_people_content people_content " +
                "ON people.id=people_content.people_id " +
                "WHERE people_content.content_id = :contentId")
                .addScalar("id", LongType.INSTANCE);
        query.setParameter("contentId", id);
        return (Long) query.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<People> getAll() {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("from People people where people.user.disabled = false");
        return (List<People>) query.list();
    }
}
