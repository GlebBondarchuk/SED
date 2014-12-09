package com.bsu.sed.dao.generic;

import java.util.List;

/**
 * Abstract base DAO methods.
 *
 * @author gbondarchuk
 */
public interface GenericDao<T, PK> {
    void create(T t);

    void delete(PK id);

    T load(PK id);

    T update(T t);

    void refresh(T t);

    List<T> getAll();
}
