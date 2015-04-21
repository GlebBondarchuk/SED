package com.bsu.sed.dao.generic.browsable;

import com.bsu.sed.dao.generic.GenericDao;
import com.bsu.sed.model.SortOrder;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface BrowsableDao<T> extends GenericDao<T, Long> {
    List<T> find(List<String> searchFields, int limit, int offset, String search, String sort, SortOrder order);

    long count(List<String> searchFields, String search);

    void delete(List<Long> ids);
}
