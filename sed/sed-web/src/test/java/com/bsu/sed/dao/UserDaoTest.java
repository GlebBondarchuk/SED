package com.bsu.sed.dao;

import com.bsu.sed.common.AbstractTransactionalIntegrationTest;
import com.bsu.sed.model.Role;
import com.bsu.sed.model.persistent.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Tests for user dao.
 *
 * @author gbondarchuk
 */
public class UserDaoTest extends AbstractTransactionalIntegrationTest {
    private static final String OVER_FIFTY_SYMBOLS_STRING = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

    @Autowired
    private UserDao userDao;

    @Test
    public void userTest() {
        User user = createUser();
        userDao.create(user);
    }

    @Test
    public void nullRoleTest() {
        try {
            User user = createUser();
            user.setRole(null);
            userDao.create(user);
            Assert.fail("Exception must be thrown");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void nullNameTest() {
        try {
            User user = createUser();
            user.setName(null);
            userDao.create(user);
            Assert.fail("Exception must be thrown");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void emptyNameTest() {
        try {
            User user = createUser();
            user.setName("");
            userDao.create(user);
            Assert.fail("Exception must be thrown");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void longNameTest() {
        try {
            User user = createUser();
            user.setName(OVER_FIFTY_SYMBOLS_STRING);
            userDao.create(user);
            Assert.fail("Exception must be thrown");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void nullPasswordTest() {
        try {
            User user = createUser();
            user.setPassword(null);
            userDao.create(user);
            Assert.fail("Exception must be thrown");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void emptyPasswordTest() {
        try {
            User user = createUser();
            user.setPassword("");
            userDao.create(user);
            Assert.fail("Exception must be thrown");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void longPasswordTest() {
        try {
            User user = createUser();
            user.setPassword(OVER_FIFTY_SYMBOLS_STRING);
            userDao.create(user);
            Assert.fail("Exception must be thrown");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void nullLoginTest() {
        try {
            User user = createUser();
            user.setLogin(null);
            userDao.create(user);
            Assert.fail("Exception must be thrown");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void emptyLoginTest() {
        try {
            User user = createUser();
            user.setLogin("");
            userDao.create(user);
            Assert.fail("Exception must be thrown");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void longLoginTest() {
        try {
            User user = createUser();
            user.setLogin(OVER_FIFTY_SYMBOLS_STRING);
            userDao.create(user);
            Assert.fail("Exception must be thrown");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Create fake user.
     *
     * @return User obejct.
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
