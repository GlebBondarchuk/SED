package com.bsu.sed.dao;

import com.bsu.sed.common.AbstractTransactionalIntegrationTest;
import com.bsu.sed.model.persistent.Document;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author gbondarchuk
 */
public class DocumentDaoTest extends AbstractTransactionalIntegrationTest {
    @Autowired
    private DocumentDao documentDao;

    @Test
    public void getDocumentTest() {
        List<Document> documents = documentDao.getAll();
    }
}
