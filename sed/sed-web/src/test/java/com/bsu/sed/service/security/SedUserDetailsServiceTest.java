package com.bsu.sed.service.security;

import com.bsu.sed.common.AbstractNonTransactionalIntegrationTest;
import com.bsu.sed.security.SedUserDetailsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author gbondarchuk
 */
public class SedUserDetailsServiceTest extends AbstractNonTransactionalIntegrationTest {
    @Autowired
    private SedUserDetailsService userDetailsService;

    @Test(expected = UsernameNotFoundException.class)
    public void invalidUserNameTest() throws Exception {
        userDetailsService.loadUserByUsername("system1");
    }

    @Test(expected = DisabledException.class)
    public void disabledUserTest() throws Exception {
        userDetailsService.loadUserByUsername("sed.bsu@gmail.com");
    }
}
