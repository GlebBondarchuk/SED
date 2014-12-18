package com.bsu.sed.common;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Basic configuration for running mockmvc integration tests.
 *
 * @author gbondarchuk
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//@DirtiesContext
@ContextConfiguration(locations = {
        "/spring/test-web-context.xml"
})
public abstract class AbstractMockMvcIntegrationTest {
    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilter(springSecurityFilterChain)
                .build();
    }
}
