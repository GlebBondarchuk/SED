package com.bsu.sed.dao.generic.browsable;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.model.Role;
import com.bsu.sed.model.SortOrder;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Property;
import org.hibernate.jpa.criteria.CriteriaQueryImpl;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.util.CollectionUtils;

/**
 * @author gbondarchuk
 */
public abstract class AbstractBrowsableDao<T> extends AbstractDao<T> implements BrowsableDao<T> {

    private static final String ID_FIELD = "id";

    @SuppressWarnings("unchecked")
    public AbstractBrowsableDao() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> find(List<String> searchFields, int limit, int offset, String search, String sort, SortOrder order) {
        Criteria criteria = getBaseCriteria(searchFields, search)
                .setMaxResults(limit)
                .setFirstResult(offset);
        if (StringUtils.isNotBlank(sort) && order != null) {
            if (SortOrder.asc.equals(order)) {
                criteria.addOrder(Order.asc(sort));
            } else {
                criteria.addOrder(Order.desc(sort));
            }
        }
        return criteria.list();
    }

    @Override
    public long count(List<String> searchFields, String search) {
        return (long) getBaseCriteria(searchFields, search).setProjection(Projections.rowCount()).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    private Criteria getBaseCriteria(List<String> searchFields, String search) {
        Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(type);
        List<Criterion> subCriterions = new ArrayList<>();
        if (StringUtils.isNotBlank(search)) {
            for (String searchField : searchFields) {
                String alias = null;
                String field = null;
                if (searchField.contains(".")) {
                    String[] values = searchField.split("\\.");
                    alias = values[0];
                    field = values[1];
                    criteria.createAlias(alias, alias);
                }
                try {
                    Class fieldClass;
                    if (alias == null) {
                        fieldClass = type.getDeclaredField(searchField).getType();
                    } else {
                        fieldClass = type.getDeclaredField(alias).getType();
                        fieldClass = fieldClass.getDeclaredField(field).getType();
                    }
                    Criterion expression = null;
                    if (fieldClass.equals(String.class)) {
                        expression = Restrictions.like(searchField, "%" + search + "%");
                    } else if (fieldClass.equals(Integer.class) || fieldClass.equals(Long.class) || fieldClass.equals(BigDecimal.class)) {
                        expression = Restrictions.eq(searchField, search);
                    } else if (fieldClass.equals(byte[].class)) {
                        Query query = session.createSQLQuery("SELECT content.id AS id FROM sed_content content " +
                                "WHERE CONVERT(content.content USING utf8) LIKE :search")
                                .addScalar("id", LongType.INSTANCE);
                        query.setParameter("search", "%" + search + "%");
                        List<Long> ids = query.list();
                        if (CollectionUtils.isEmpty(ids)) {
                            continue;
                        }
                        expression = Restrictions.in(ID_FIELD, ids);
                    }
                    subCriterions.add(expression);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
            criteria.add(Restrictions.or(subCriterions.toArray(new Criterion[subCriterions.size()])));
        }

        return criteria;
    }

    @Override
    public void delete(List<Long> ids) {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("DELETE FROM " + type.getSimpleName() + " WHERE id IN(:ids)");
        query.setParameterList("ids", ids);
        query.executeUpdate();
    }
}
