package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.GenericDao;
import com.bsu.sed.dao.generic.browsable.BrowsableDao;
import com.bsu.sed.model.Role;
import com.bsu.sed.model.persistent.Document;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface DocumentDao extends BrowsableDao<Document> {
    boolean exists(String name);

    List<Document> find(int limit, int offset);

    Role getRole(String fileName);
}
