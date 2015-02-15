package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.model.persistent.News;
import org.springframework.stereotype.Repository;

/**
 * @author gbondarchuk
 */
@Repository
public class NewsDaoImpl  extends AbstractDao<News> implements NewsDao {
}
