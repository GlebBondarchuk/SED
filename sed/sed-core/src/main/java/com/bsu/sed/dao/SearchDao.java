package com.bsu.sed.dao;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface SearchDao {
    List<Long> find(String search);
}
