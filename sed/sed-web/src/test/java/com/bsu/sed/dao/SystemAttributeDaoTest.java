package com.bsu.sed.dao;

import com.bsu.sed.common.AbstractTransactionalIntegrationTest;
import com.bsu.sed.model.SystemAttributeKey;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Test for SystemAttributeDao.
 *
 * @author gbondarchuk
 */
public class SystemAttributeDaoTest extends AbstractTransactionalIntegrationTest {
    @Autowired
    private SystemAttributeDao systemAttributeDao;

    @Test
    public void getAttributesTest() {
        SystemAttributeKey[] keys = SystemAttributeKey.values();
        for (SystemAttributeKey key : keys) {
            String value = systemAttributeDao.get(key);
            Assert.assertNotNull(value);
        }
    }
}
