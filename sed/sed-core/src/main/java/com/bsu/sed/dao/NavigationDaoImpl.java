package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.model.persistent.Navigation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gbondarchuk
 */
@Repository
public class NavigationDaoImpl extends AbstractDao<Navigation> implements NavigationDao {

    @Autowired
    private CacheManager cacheManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Navigation> getParents() {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("SELECT navigation FROM Navigation navigation WHERE navigation.parent is null ORDER BY navigation.listNumber DESC");
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Navigation> getParentCandidates() {
        Session session = em.unwrap(Session.class);
        Query sqlQuery = session.createSQLQuery("SELECT nav.id AS id FROM sed_navigation nav " +
                "LEFT JOIN sed_navigation first_parent ON first_parent.id = nav.parent_id " +
                "LEFT JOIN sed_navigation second_parent ON second_parent.id = first_parent.parent_id " +
                "LEFT JOIN sed_navigation third_parent ON third_parent.id = second_parent.parent_id " +
                "WHERE first_parent.id IS NULL OR second_parent.id IS NULL OR third_parent.id IS NULL").addScalar("id", LongType.INSTANCE);
        List ids = sqlQuery.list();
        if(CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }
        Query query = session.createQuery("SELECT navigation FROM Navigation navigation WHERE navigation.id in (:ids)");
        query.setParameterList("ids", sqlQuery.list());
        return query.list();
    }

    @Override
    public void evict() {
        Cache cache = cacheManager.getCache("siteMapCache");
        cache.clear();
    }
}
