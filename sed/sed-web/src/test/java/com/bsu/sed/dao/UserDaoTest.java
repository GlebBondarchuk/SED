package com.bsu.sed.dao;

import com.bsu.sed.common.AbstractTransactionalIntegrationTest;
import com.bsu.sed.model.Role;
import com.bsu.sed.model.constraint.ConstraintConstants;
import com.bsu.sed.model.persistent.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

import static com.bsu.sed.utils.StringUtils.*;

/**
 * Tests for user dao.
 *
 * @author gbondarchuk
 */
@SuppressWarnings("unchecked")
public class UserDaoTest extends AbstractTransactionalIntegrationTest {

    @Autowired
    private UserDao userDao;

    private Validator validator;

    @Before
    public void before() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void userTest() {
        User user = createUser();
        userDao.create(user);
    }

    /**
     * ******************************************************************************
     * <p/>
     * User Name Tests.
     */

    @Test
    public void updateNameTest() {
        String validString = createValidString(ConstraintConstants.USER_NAME_MAX_LENGTH);
        User user = getUser();
        user.setName(validString);
        Set constraintViolations = validator.validate(user);
        printMessages(constraintViolations);
        Assert.assertEquals(constraintViolations.size(), 0);
    }

    @Test
    public void longNameTest() {
        String invalidString = createOverflowString(ConstraintConstants.USER_NAME_MAX_LENGTH);
        User user = getUser();
        user.setName(invalidString);
        Set constraintViolations = validator.validate(user);
        printMessages(constraintViolations);
        Assert.assertEquals(constraintViolations.size(), 1);
    }

    @Test
    public void emptyNameTest() {
        User user = getUser();
        user.setName("");
        Set constraintViolations = validator.validate(user);
        printMessages(constraintViolations);
        Assert.assertEquals(constraintViolations.size(), 1);
    }

    @Test
    public void nullNameTest() {
        User user = getUser();
        user.setName(null);
        Set constraintViolations = validator.validate(user);
        printMessages(constraintViolations);
        Assert.assertEquals(constraintViolations.size(), 1);
    }

    /**
     * ******************************************************************************
     * <p/>
     * User Login Tests.
     */

    @Test
    public void updateLoginTest() {
        String validString = "****************************************@gmail.com";
        User user = getUser();
        user.setLogin(validString);
        Set constraintViolations = validator.validate(user);
        printMessages(constraintViolations);
        Assert.assertEquals(constraintViolations.size(), 0);
    }

    @Test
    public void longLoginTest() {
        String invalidString = createOverflowString(ConstraintConstants.USER_LOGIN_MAX_LENGTH);
        User user = getUser();
        user.setLogin(invalidString);
        Set constraintViolations = validator.validate(user);
        printMessages(constraintViolations);
        Assert.assertEquals(constraintViolations.size(), 2);
    }

    @Test
    public void emptyLoginTest() {
        User user = getUser();
        user.setLogin("");
        Set constraintViolations = validator.validate(user);
        printMessages(constraintViolations);
        Assert.assertEquals(constraintViolations.size(), 2);
    }

    @Test
    public void nullLoginTest() {
        User user = getUser();
        user.setLogin(null);
        Set constraintViolations = validator.validate(user);
        printMessages(constraintViolations);
        Assert.assertEquals(constraintViolations.size(), 1);
    }

    @Test
    public void notMatchedLoginTest() {
        User user = getUser();
        user.setLogin("aaaaa");
        Set constraintViolations = validator.validate(user);
        printMessages(constraintViolations);
        Assert.assertEquals(constraintViolations.size(), 1);
    }


    /**
     * ******************************************************************************
     * <p/>
     * User Password Tests.
     */

    @Test
    public void updatePasswordTest() {
        String validString = createValidString(ConstraintConstants.USER_PASSWORD_MAX_LENGTH);
        User user = getUser();
        user.setPassword(validString);
        Set constraintViolations = validator.validate(user);
        printMessages(constraintViolations);
        Assert.assertEquals(constraintViolations.size(), 0);
    }

    @Test
    public void longPasswordTest() {
        String invalidString = createOverflowString(ConstraintConstants.USER_PASSWORD_MAX_LENGTH);
        User user = getUser();
        user.setPassword(invalidString);
        Set constraintViolations = validator.validate(user);
        printMessages(constraintViolations);
        Assert.assertEquals(constraintViolations.size(), 1);
    }

    @Test
    public void emptyPasswordTest() {
        User user = getUser();
        user.setPassword("");
        Set constraintViolations = validator.validate(user);
        printMessages(constraintViolations);
        Assert.assertEquals(constraintViolations.size(), 1);
    }

    @Test
    public void nullPasswordTest() {
        User user = getUser();
        user.setPassword(null);
        Set constraintViolations = validator.validate(user);
        printMessages(constraintViolations);
        Assert.assertEquals(constraintViolations.size(), 1);
    }

    /**
     * ******************************************************************************
     * <p/>
     * User Role Tests.
     */

    @Test
    public void nullRoleTest() {
        User user = getUser();
        user.setRole(null);
        Set constraintViolations = validator.validate(user);
        printMessages(constraintViolations);
        Assert.assertEquals(constraintViolations.size(), 1);
    }

    private User getUser() {
        List<User> users = userDao.getAll();
        return users.get(0);
    }

    /**
     * Create fake user.
     *
     * @return User object.
     */
    private User createUser() {
        User user = new User();
        user.setRole(Role.STUDENT);
        user.setName("Name");
        user.setLogin("login@login.com");
        user.setDisabled(false);
        user.setPassword("899cc984ac3801fa9cb12f1486264628");
        return user;
    }
}
