package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.GenericDao;
import com.bsu.sed.model.persistent.People;

/**
 * @author gbondarchuk
 */
public interface PeopleDao extends GenericDao<People, Long> {
    People getHead();

    People getByLogin(String login);

    Long getUserIdByContentId(Long id);
}
