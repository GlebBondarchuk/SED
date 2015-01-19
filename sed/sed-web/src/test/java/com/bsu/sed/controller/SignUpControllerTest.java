package com.bsu.sed.controller;

import com.bsu.sed.common.AbstractMockMvcIntegrationTest;
import com.bsu.sed.model.Role;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static com.bsu.sed.model.Tiles.SIGN_UP_PAGE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author gbondarchuk
 */
public class SignUpControllerTest extends AbstractMockMvcIntegrationTest {
    @Test
    public void getSignUpPage() throws Exception {
        mockMvc.perform(get("/signUp"))
                .andExpect(status().isOk())
                .andExpect(view().name(SIGN_UP_PAGE.getTileName()));
    }

    @Test
    public void getInvalidSignUpPage() throws Exception {
        mockMvc.perform(get("/signUp1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void postSignUpPage() throws Exception {
        ModelAndView modelAndView =  mockMvc.perform(post("/signUp")
                .param("name", "Test User")
                .param("login", "rrr@gmail.com")
                .param("password", "password")
                .param("confirmPassword", "password")
                .param("role", Role.STUDENT.name()))
                .andExpect(status().isOk()).andReturn().getModelAndView();
        Assert.assertFalse(modelAndView.getModelMap().containsAttribute("errors"));
    }

    @Test
    public void postSignUpPageEmptyName() throws Exception {
        ModelAndView modelAndView = mockMvc.perform(post("/signUp")
                .param("name", "")
                .param("login", "rrrl@gmail.com")
                .param("password", "password")
                .param("confirmPassword", "password")
                .param("role", Role.STUDENT.name()))
                .andExpect(status().isOk())
                .andReturn().getModelAndView();
        Assert.assertTrue(modelAndView.getModelMap().containsAttribute("errors"));
    }

    @Test
    public void postSignUpPageEmptyRole() throws Exception {
        ModelAndView modelAndView = mockMvc.perform(post("/signUp")
                .param("name", "Test User")
                .param("login", "rrr@gmail.com")
                .param("password", "password")
                .param("confirmPassword", "password")
                .param("role", ""))
                .andExpect(status().isOk())
                .andReturn().getModelAndView();
        Assert.assertTrue(modelAndView.getModelMap().containsAttribute("errors"));
    }

    @Test
    public void postSignUpPageEmptyLogin() throws Exception {
        ModelAndView modelAndView = mockMvc.perform(post("/signUp")
                .param("name", "Test User")
                .param("login", "")
                .param("password", "password")
                .param("confirmPassword", "password")
                .param("role", Role.STUDENT.name()))
                .andExpect(status().isOk())
                .andReturn().getModelAndView();
        Assert.assertTrue(modelAndView.getModelMap().containsAttribute("errors"));
    }

    @Test
    public void postSignUpPageEmptyPassword() throws Exception {
        ModelAndView modelAndView = mockMvc.perform(post("/signUp")
                .param("name", "Test User")
                .param("login", "rrr@gmail.com")
                .param("password", "")
                .param("confirmPassword", "password")
                .param("role", Role.STUDENT.name()))
                .andExpect(status().isOk())
                .andReturn().getModelAndView();
        Assert.assertTrue(modelAndView.getModelMap().containsAttribute("errors"));
    }

    @Test
    public void postSignUpPageNotMatchPassword() throws Exception {
        ModelAndView modelAndView = mockMvc.perform(post("/signUp")
                .param("name", "Test User")
                .param("login", "rrr@gmail.com")
                .param("password", "password")
                .param("confirmPassword", "password1")
                .param("role", Role.STUDENT.name()))
                .andExpect(status().isOk())
                .andReturn().getModelAndView();
        Assert.assertTrue(modelAndView.getModelMap().containsAttribute("errors"));
    }
}
