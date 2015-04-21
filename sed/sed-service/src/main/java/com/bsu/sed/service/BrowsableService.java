package com.bsu.sed.service;

import com.bsu.sed.model.SortOrder;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface BrowsableService {
    String getJson(int limit, int offset, String search, String sort, SortOrder order);

    void delete(List<Long> ids);
}
