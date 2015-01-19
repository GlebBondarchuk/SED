package com.bsu.sed.controller;

import com.bsu.sed.common.AbstractMockMvcIntegrationTest;
import org.junit.Test;

import static com.bsu.sed.model.Tiles.MAIN_PAGE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author gbondarchuk
 */
public class RootControllerTest extends AbstractMockMvcIntegrationTest {
    @Test
    public void getRootPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name(MAIN_PAGE.getTileName()));

    }

    @Test
    public void getInvalidRootPage() throws Exception {
        mockMvc.perform(get("/r"))
                .andExpect(status().isNotFound());
    }
}
