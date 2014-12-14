package com.bsu.sed.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Login test.
 *
 * @author gbondarchuk
 */
public class LoginTest  {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy filterChain;

    private MockMvc mockMvc;


    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }


    public void loginTest() {

    }
}
