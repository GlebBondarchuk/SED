package com.bsu.sed.common;

import com.bsu.sed.model.Role;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
@Transactional
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
                .alwaysDo(print())
                .build();
    }

    protected MockHttpServletRequestBuilder secureGet(String url, Role role) throws Exception {
        String login = getLoginByRole(role);
        ResultActions result = mockMvc.perform(post("/j_spring_security_check")
                .param("j_username", login)
                .param("j_password", login))
                .andExpect(status().isFound());

        MockHttpSession session = (MockHttpSession) result.andReturn().getRequest().getSession();

        return MockMvcRequestBuilders.get(url).session(session);
    }

    private String getLoginByRole(Role role) {
        switch (role.name()) {
            case "ADMIN":
                return "admin";
            case "TEACHER":
                return "teacher";
            case "STUDENT":
                return "student";
            default:
                throw new IllegalArgumentException("Role not found");
        }
    }
}
