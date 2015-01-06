package com.bsu.sed.dao;

import com.bsu.sed.common.AbstractMockMvcIntegrationTest;
import com.bsu.sed.common.AbstractTransactionalIntegrationTest;
import com.bsu.sed.model.SystemAttributeCategory;
import com.bsu.sed.model.SystemAttributeKey;
import com.bsu.sed.model.constraint.ConstraintConstants;
import com.bsu.sed.model.persistent.SystemAttribute;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static com.bsu.sed.utils.StringUtils.createOverflowString;
import static com.bsu.sed.utils.StringUtils.createValidString;

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

    @Test
    public void updateSystemAttribute() {
        SystemAttribute systemAttribute = getSystemAttribute();
        systemAttribute.setDescription("Description");
        systemAttribute.setCategory(SystemAttributeCategory.EMAIL);
        systemAttribute.setDisplayName("Display Name");
        systemAttribute.setKey(SystemAttributeKey.EMAIL);
        systemAttribute.setValue("Value");
        systemAttributeDao.refresh(systemAttribute);
    }

    /**
     * ******************************************************************************
     * <p/>
     * System Attribute Value Tests.
     */

    private void updateValue(String value) {
        SystemAttribute systemAttribute = getSystemAttribute();
        systemAttribute.setValue(value);
        systemAttributeDao.update(systemAttribute);
    }

    private void invalidValueTest(String value) {
        try {
            updateValue(value);
            Assert.fail("Exception must be thrown");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateValueTest() {
        String validString = createValidString(ConstraintConstants.SYSTEM_VALUE_MAX_LENGTH);
        updateValue(validString);
    }

    @Test
    public void longValueTest() {
        String overflowString = createOverflowString(ConstraintConstants.SYSTEM_VALUE_MAX_LENGTH);
        invalidValueTest(overflowString);
    }

    @Test
    public void emptyValueTest() {
        invalidValueTest("");
    }

    @Test
    public void nullValueTest() {
        invalidValueTest(null);
    }

    /**
     * ******************************************************************************
     * <p/>
     * System Attribute Description Tests.
     */

    private void updateDescription(String value) {
        SystemAttribute systemAttribute = getSystemAttribute();
        systemAttribute.setDescription(value);
        systemAttributeDao.update(systemAttribute);
    }

    private void invalidDescriptionTest(String value) {
        try {
            updateDescription(value);
            Assert.fail("Exception must be thrown");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateDescriptionTest() {
        String validString = createValidString(ConstraintConstants.SYSTEM_DESCRIPTION_MAX_LENGTH);
        updateDescription(validString);
    }

    @Test
    public void longDescriptionTest() {
        String overflowString = createOverflowString(ConstraintConstants.SYSTEM_DESCRIPTION_MAX_LENGTH);
        invalidDescriptionTest(overflowString);
    }

    @Test
    public void emptyDescriptionTest() {
        invalidDescriptionTest("");
    }

    @Test
    public void nullDescriptionTest() {
        invalidDescriptionTest(null);
    }


    @Test
    public void updateKeyTest() {
        try {
            SystemAttribute systemAttribute = getSystemAttribute();
            systemAttribute.setKey(SystemAttributeKey.EMAIL_PORT);
            systemAttributeDao.update(systemAttribute);
            Assert.fail("Exception must be thrown");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateDisplayValueTest() {
        try {
            SystemAttribute systemAttribute = getSystemAttribute();
            systemAttribute.setDisplayName("Another Display Name");
            systemAttributeDao.update(systemAttribute);
            Assert.fail("Exception must be thrown");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void updateCategoryTest() {
        try {
            SystemAttribute systemAttribute = getSystemAttribute();
            systemAttribute.setCategory(SystemAttributeCategory.ANOTHER);
            systemAttributeDao.update(systemAttribute);
            Assert.fail("Exception must be thrown");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Get any system attributes.
     */
    private SystemAttribute getSystemAttribute() {
        List<SystemAttribute> systemAttributes = systemAttributeDao.getAll();
        return systemAttributes.get(0);
    }
}
