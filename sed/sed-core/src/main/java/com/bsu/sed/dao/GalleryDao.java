package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.GenericDao;
import com.bsu.sed.dao.generic.browsable.BrowsableDao;
import com.bsu.sed.model.persistent.Gallery;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface GalleryDao extends GenericDao<Gallery, Long> {
    List<Gallery> find(int limit, int offset);

    long count();
}
