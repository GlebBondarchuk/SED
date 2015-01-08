package com.bsu.sed.dao;

import com.bsu.sed.common.AbstractTransactionalIntegrationTest;
import com.bsu.sed.model.SystemAttributeKey;
import com.bsu.sed.model.constraint.ConstraintConstants;
import com.bsu.sed.model.persistent.SystemAttribute;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

import static com.bsu.sed.utils.StringUtils.createOverflowString;
import static com.bsu.sed.utils.StringUtils.createValidString;
import static com.bsu.sed.utils.StringUtils.printMessages;

/**
 * Test for SystemAttributeDao.
 *
 * @author gbondarchuk
 */
@SuppressWarnings("unchecked")
public class SystemAttributeDaoTest extends AbstractTransactionalIntegrationTest {
    @Autowired
    private SystemAttributeDao systemAttributeDao;

    private Validator validator;

    @Before
    public void before() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

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
        systemAttribute.setValue("Value");
        systemAttributeDao.refresh(systemAttribute);
    }

    /**
     * ******************************************************************************
     * <p/>
     * System Attribute Value Tests.
     */

    @Test
    public void updateValueTest() {
        String validValue = createValidString(ConstraintConstants.SYSTEM_VALUE_MAX_LENGTH);
        SystemAttribute systemAttribute = getSystemAttribute();
        systemAttribute.setValue(validValue);
        Set constraintViolations = validator.validate(systemAttribute);
        printMessages(constraintViolations);
        Assert.assertEquals(constraintViolations.size(), 0);
    }

    @Test
    public void longValueTest() {
        String overflowValue = createOverflowString(ConstraintConstants.SYSTEM_VALUE_MAX_LENGTH);
        SystemAttribute systemAttribute = getSystemAttribute();
        systemAttribute.setValue(overflowValue);
        Set constraintViolations = validator.validate(systemAttribute);
        printMessages(constraintViolations);
        Assert.assertEquals(constraintViolations.size(), 1);
    }

    @Test
    public void emptyValueTest() {
        SystemAttribute systemAttribute = getSystemAttribute();
        systemAttribute.setValue("");
        Set constraintViolations = validator.validate(systemAttribute);
        printMessages(constraintViolations);
        Assert.assertEquals(constraintViolations.size(), 1);
    }

    @Test
    public void nullValueTest() {
        SystemAttribute systemAttribute = getSystemAttribute();
        systemAttribute.setValue(null);
        Set constraintViolations = validator.validate(systemAttribute);
        printMessages(constraintViolations);
        Assert.assertEquals(constraintViolations.size(), 1);
    }

    /**
     * ******************************************************************************
     * <p/>
     * System Attribute Description Tests.
     */

    @Test
    public void updateDescriptionTest() {
        String validString = createValidString(ConstraintConstants.SYSTEM_DESCRIPTION_MAX_LENGTH);
        SystemAttribute systemAttribute = getSystemAttribute();
        systemAttribute.setDescription(validString);
        Set constraintViolations = validator.validate(systemAttribute);
        printMessages(constraintViolations);
        Assert.assertEquals(constraintViolations.size(), 0);
    }

    @Test
    public void longDescriptionTest() {
        String overflowString = createOverflowString(ConstraintConstants.SYSTEM_DESCRIPTION_MAX_LENGTH);
        SystemAttribute systemAttribute = getSystemAttribute();
        systemAttribute.setDescription(overflowString);
        Set constraintViolations = validator.validate(systemAttribute);
        printMessages(constraintViolations);
        Assert.assertEquals(constraintViolations.size(), 1);
    }

    @Test
    public void emptyDescriptionTest() {
        SystemAttribute systemAttribute = getSystemAttribute();
        systemAttribute.setDescription("");
        Set constraintViolations = validator.validate(systemAttribute);
        printMessages(constraintViolations);
        Assert.assertEquals(constraintViolations.size(), 1);
    }

    @Test
    public void nullDescriptionTest() {
        SystemAttribute systemAttribute = getSystemAttribute();
        systemAttribute.setDescription(null);
        Set constraintViolations = validator.validate(systemAttribute);
        printMessages(constraintViolations);
        Assert.assertEquals(constraintViolations.size(), 1);
    }

    @Test
    public void notNullKeys() {
        List<SystemAttribute> systemAttributes = systemAttributeDao.getAll();
        for(SystemAttribute systemAttribute : systemAttributes) {
            Assert.assertNotNull(systemAttribute.getKey());
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
