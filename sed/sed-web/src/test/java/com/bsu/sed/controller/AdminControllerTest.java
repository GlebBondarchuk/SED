package com.bsu.sed.controller;

import com.bsu.sed.common.AbstractMockMvcIntegrationTest;
import org.junit.Test;

import static com.bsu.sed.model.Role.ADMIN;
import static com.bsu.sed.model.Tiles.LOGIN_PAGE;
import static com.bsu.sed.model.Tiles.SYSTEM_PAGE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author gbondarchuk
 */
public class AdminControllerTest extends AbstractMockMvcIntegrationTest {
    @Test
    public void getSystemPageWithoutLogin() throws Exception {
        mockMvc.perform(get("/admin/system"))
                .andExpect(status().isOk())
                .andExpect(view().name(LOGIN_PAGE.getTileName()));
    }

    @Test
    public void getSystemPageWithLogin() throws Exception {
        mockMvc.perform(secureGet("/admin/system", ADMIN))
                .andExpect(status().isOk())
                .andExpect(view().name(SYSTEM_PAGE.getTileName()));
    }

    @Test
    public void accessDeniedSystemPage() throws Exception {
        mockMvc.perform(get("/admin/system"))
                .andExpect(status().isOk())
                .andExpect(view().name(LOGIN_PAGE.getTileName()));
    }

    @Test
    public void getInvalidSystemPage() throws Exception {
        mockMvc.perform(get("/admin/system1"))
                .andExpect(status().isNotFound());
    }
}
