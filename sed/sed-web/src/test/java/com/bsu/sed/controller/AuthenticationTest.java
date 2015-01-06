package com.bsu.sed.controller;

import com.bsu.sed.common.AbstractMockMvcIntegrationTest;
import com.bsu.sed.service.UserService;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Authentication users test.
 *
 * @author gbondarchuk
 */
public class AuthenticationTest extends AbstractMockMvcIntegrationTest {

    @Test
    public void successLoginTest() throws Exception {
        mockMvc.perform(
                post("/j_spring_security_check")
                        .param("j_username", "gleb.exadel@gmail.com")
                        .param("j_password", "gleb"))
                .andExpect(status().isFound()).andExpect(redirectedUrl("/"));
    }

    @Test
    public void failedLoginTest() throws Exception {
        mockMvc.perform(
                post("/j_spring_security_check")
                        .param("j_username", "gleb.exadel@gmail.com")
                        .param("j_password", "12345"))
                .andExpect(status().isFound()).andExpect(redirectedUrl("/login?error"));
    }

    @Test
    public void systemLoginTest() throws Exception {
        mockMvc.perform(
                post("/j_spring_security_check")
                        .param("j_username", UserService.SYSTEM_USER)
                        .param("j_password", "system"))
                .andExpect(status().isFound()).andExpect(redirectedUrl("/login?error"));
    }

    @Test
    public void logoutTest() throws Exception {
        mockMvc.perform(
                post("/j_spring_security_logout")
                        .param("j_username", UserService.SYSTEM_USER)
                        .param("j_password", "system"))
                .andExpect(status().isFound()).andExpect(redirectedUrl("/"));
    }
}
