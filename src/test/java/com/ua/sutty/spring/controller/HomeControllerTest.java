package com.ua.sutty.spring.controller;

import com.ua.sutty.spring.app.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HomeController homeController;

    @Test
    public void checkLoadMainController() throws Exception {
        assertThat(homeController).isNotNull();
    }

    @Test
    @WithUserDetails(value = "admin")
    public void homePageTestWithAdmin() throws Exception {
        this.mockMvc.perform(get("/home"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(xpath("//*[@id='navbar-name']").string("admin"))
                .andExpect(xpath("//*[@id=\"navbarSupportedContent\"]/a").string("Logout"))
                .andExpect(content().string(containsString("Hello, Admin")));
    }

    @Test
    @WithUserDetails(value = "SuttyRead1")
    public void homePageTestWithUser() throws Exception {
        this.mockMvc.perform(get("/home"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(xpath("//*[@id='navbar-name']").string("SuttyRead1"))
                .andExpect(xpath("//*[@id=\"navbarSupportedContent\"]/a").string("Logout"))
                .andExpect(content().string(containsString("Hello, User")));
    }

    @Test
    public void homePageWithGuest() throws Exception {
        this.mockMvc.perform(get("/home"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }



}
