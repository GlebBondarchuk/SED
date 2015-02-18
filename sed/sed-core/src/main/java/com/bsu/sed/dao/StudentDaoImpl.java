package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.model.persistent.People;
import com.bsu.sed.model.persistent.Student;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author gbondarchuk
 */
@Repository
public class StudentDaoImpl extends AbstractDao<Student> implements StudentDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> getAll() {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("from Student student where student.user.disabled = false");
        return (List<Student>) query.list();
    }

    @Override
    public Student getByLogin(String login) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("from Student student where student.user.login = :login");
        query.setParameter("login", login);
        return (Student) query.uniqueResult();
    }
}
