package com.ua.sutty.spring.controller;

import com.ua.sutty.spring.app.Application;
import com.ua.sutty.spring.domain.Role;
import com.ua.sutty.spring.domain.User;
import com.ua.sutty.spring.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class EditControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EditController editController;

    @Autowired
    private UserService userService;

    @Test
    public void checkLoadEditController() throws Exception {
        assertThat(editController).isNotNull();
    }

    @Test
    @WithUserDetails(value = "admin")
    public void editPageWithAdmin() throws Exception {
        this.mockMvc.perform(get("/edit/2"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Login:")))
                .andExpect(content().string(containsString("Password:")))
                .andExpect(content().string(containsString("Confirm Password:")))
                .andExpect(content().string(containsString("Email:")))
                .andExpect(content().string(containsString("First Name:")))
                .andExpect(content().string(containsString("Last Name:")))
                .andExpect(content().string(containsString("Birthday:")))
                .andExpect(content().string(containsString("Role:")));
    }

    @Test
    @WithUserDetails(value = "SuttyRead1")
    public void editPageWithUser() throws Exception {
        this.mockMvc.perform(get("/edit/2"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isForbidden());
    }

    @Test
    public void editPageWithGuest() throws Exception {
        this.mockMvc.perform(get("/edit/2"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void deleteUnknownUser() throws Exception {
        this.mockMvc.perform(get("/edit/7"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home?unknownId"));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void postRequestEditTest() throws Exception {
        this.mockMvc.perform(post("/edit/2")
                .param("login", "SuttyRead1")
                .param("password", "SuttyRead1")
                .param("confirmPassword", "SuttyRead1")
                .param("email", "SuttyRead1@gmail.com")
                .param("firstName", "Suttyqqq")
                .param("lastName", "Readqqq")
                .param("birthday", "1987-04-06")
                .param("role", "2"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home?successfullyUpdated"));
        User suttyRead1 = userService.findUserById(2L);
        assertNotNull(suttyRead1);
        assertEquals(suttyRead1.getLogin(), "SuttyRead1");
        assertEquals(suttyRead1.getEmail(), "SuttyRead1@gmail.com");
        assertEquals(suttyRead1.getFirstName(), "Suttyqqq");
        assertEquals(suttyRead1.getLastName(), "Readqqq");
        assertEquals(suttyRead1.getBirthday(), Date.valueOf("1987-04-06"));
        assertEquals(suttyRead1.getRole(), new Role(2L, "USER"));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void postRequestEditInvalidDataTest() throws Exception {
        this.mockMvc.perform(post("/edit/2")
                .param("login", "SuttyRead1")
                .param("password", "Sut")
                .param("confirmPassword", "Sut")
                .param("email", "SuttyRead1@gmail.com")
                .param("firstName", "Suttyqqq")
                .param("lastName", "Readqqq")
                .param("birthday", "1987-04-06")
                .param("role", "2"))
                .andDo(print())
                .andExpect(status().isOk());
        User suttyRead1 = userService.findUserById(2L);
        assertNotNull(suttyRead1);
        assertEquals("Login don't change", suttyRead1.getLogin(), "SuttyRead1");
        assertEquals(suttyRead1.getEmail(), "SuttyRead1@gmail.com");
        assertNotEquals(suttyRead1.getFirstName(), "Suttyqqq");
        assertNotEquals(suttyRead1.getLastName(), "Readqqq");
        assertNotEquals(suttyRead1.getBirthday(), Date.valueOf("1987-04-06"));
        assertEquals(suttyRead1.getRole(), new Role(2L, "USER"));
    }

}
