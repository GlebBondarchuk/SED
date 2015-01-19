package com.bsu.sed.controller.exception;

import com.bsu.sed.common.AbstractMockMvcIntegrationTest;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author gbondarchuk
 */
public class ExceptionHandlerControllerTest extends AbstractMockMvcIntegrationTest{
    @Test
    public void runtimeExceptionTest() throws Exception {
        mockMvc.perform(get("/789"))
                .andExpect(status().isNotFound());
    }
}
