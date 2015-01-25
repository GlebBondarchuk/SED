package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.model.persistent.Document;
import org.springframework.stereotype.Repository;

/**
 * @author gbondarchuk
 */
@Repository
public class DocumentDaoImpl extends AbstractDao<Document> implements DocumentDao {
}
