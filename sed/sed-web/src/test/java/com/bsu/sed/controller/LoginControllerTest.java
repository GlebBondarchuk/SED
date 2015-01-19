package com.bsu.sed.controller;

import com.bsu.sed.common.AbstractMockMvcIntegrationTest;
import org.junit.Test;

import static com.bsu.sed.model.Tiles.LOGIN_PAGE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Test for LoginController
 *
 * @author gbondarchuk
 */
public class LoginControllerTest extends AbstractMockMvcIntegrationTest {
    @Test
    public void getLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name(LOGIN_PAGE.getTileName()));

    }

    @Test
    public void incorrectLoginPage() throws Exception {
        mockMvc.perform(get("/login5"))
                .andExpect(status().isNotFound());
    }
}
