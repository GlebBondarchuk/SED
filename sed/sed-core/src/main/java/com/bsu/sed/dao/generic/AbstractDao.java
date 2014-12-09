package com.bsu.sed.dao.generic;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Abstract Generic Dao
 *
 * @author gbondarchuk
 */
public abstract class AbstractDao<T> implements GenericDao<T, Long> {

    @PersistenceContext
    protected EntityManager em;

    private Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public void create(T t) {
        em.persist(t);
    }

    @Override
    public void delete(Long id) {
        em.remove(em.getReference(type, id));
    }

    @Override
    public T load(Long id) {
        return (T) em.find(type, id);
    }

    @Override
    public T update(T t) {
        return em.merge(t);
    }

    @Override
    public void refresh(T t) {
        em.refresh(t);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        Session session = em.unwrap(Session.class);
        return session.createQuery("select entity from " + type.getSimpleName() + " entity").list();
    }
}
