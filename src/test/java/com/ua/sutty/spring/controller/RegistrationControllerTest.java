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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RegistrationController registrationController;

    @Autowired
    private UserService userService;

    @Test
    public void checkLoadRegistrationController() throws Exception {
        assertThat(registrationController).isNotNull();
    }

    @Test
    @WithUserDetails(value = "admin")
    public void addPageWithAdmin() throws Exception {
        this.mockMvc.perform(get("/registration"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));

    }

    @Test
    @WithUserDetails(value = "SuttyRead1")
    public void addPageWithUser() throws Exception {
        this.mockMvc.perform(get("/registration"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }

    @Test
    public void registrationPageWithGuest() throws Exception {
        this.mockMvc.perform(get("/registration"))
                .andDo(print())
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Login:")))
                .andExpect(content().string(containsString("Password:")))
                .andExpect(content().string(containsString("Confirm Password:")))
                .andExpect(content().string(containsString("Email:")))
                .andExpect(content().string(containsString("First Name:")))
                .andExpect(content().string(containsString("Last Name:")))
                .andExpect(content().string(containsString("Birthday:")));
    }

    @Test
    public void postRequestRegistrationTest() throws Exception {
        this.mockMvc.perform(post("/registration")
                .param("login", "SuttyRead4")
                .param("password", "SuttyRead4")
                .param("confirmPassword", "SuttyRead4")
                .param("email", "SuttyRead4@gmail.com")
                .param("firstName", "Sutty")
                .param("lastName", "Read")
                .param("birthday", "1987-12-07")
                .param("role", "2")
                .param("g-recaptcha-response",""))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?signUpSuccess"));
        User suttyRead4 = userService.findUserByLogin("SuttyRead4");
        assertNotNull(suttyRead4);
        assertEquals(suttyRead4.getLogin(), "SuttyRead4");
        assertEquals(suttyRead4.getEmail(), "SuttyRead4@gmail.com");
        assertEquals(suttyRead4.getFirstName(), "Sutty");
        assertEquals(suttyRead4.getLastName(), "Read");
        assertEquals(suttyRead4.getBirthday(), Date.valueOf("1987-12-07"));
        assertEquals(suttyRead4.getRole(), new Role(2L, "USER"));
    }

    @Test
    public void postRequestRegistrationInvalidDataTest() throws Exception {
        this.mockMvc.perform(post("/registration")
                .param("login", "SuttyRead5")
                .param("password", "SuttyRead5")
                .param("confirmPassword", "SuttyRead5")
                .param("email", "SuttyRead5gmail.com")
                .param("firstName", "Sutty")
                .param("lastName", "Read")
                .param("birthday", "1987-12-07")
                .param("role", "2")
                .param("g-recaptcha-response",""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
        User suttyRead5 = userService.findUserByLogin("SuttyRead5");
        assertNull("We cannot registration user, because error in form ", suttyRead5);
    }

    @Test
    public void registrationAlreadyExistUserWithThisLoginTest() throws Exception {
        this.mockMvc.perform(post("/registration")
                .param("login", "SuttyRead1")
                .param("password", "SuttyRead2")
                .param("confirmPassword", "SuttyRead2")
                .param("email", "SuttyRead2@gmail.com")
                .param("firstName", "Suttyq")
                .param("lastName", "Readq")
                .param("birthday", "1987-04-07")
                .param("role", "2")
                .param("g-recaptcha-response",""))
                .andDo(print())
                .andExpect(status().isOk());
        User suttyRead1 = userService.findUserByLogin("SuttyRead1");
        assertNotNull(suttyRead1);
        assertEquals("Login should be match", suttyRead1.getLogin(), "SuttyRead1");
        assertNotEquals("Not equals because user with this login already" +
                        " exist and input login don't match with login in base",
                suttyRead1.getEmail(), "SuttyRead2@gmail.com");
        assertNotEquals(suttyRead1.getFirstName(), "Suttyq");
        assertNotEquals(suttyRead1.getLastName(), "Readq");
        assertNotEquals(suttyRead1.getBirthday(), Date.valueOf("1987-04-07"));
        assertEquals(suttyRead1.getRole(), new Role(2L, "USER"));
    }

}
