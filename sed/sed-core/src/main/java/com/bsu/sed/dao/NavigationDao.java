package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.GenericDao;
import com.bsu.sed.model.persistent.Navigation;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface NavigationDao extends GenericDao<Navigation,  Long> {
    List<Navigation> getParents();

    List<Navigation> getParentCandidates();

    void evict();
}
