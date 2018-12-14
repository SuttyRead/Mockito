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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class DeleteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DeleteController deleteController;

    @Test
    public void checkLoadAddController() throws Exception {
        assertThat(deleteController).isNotNull();
    }

    @Test
    @WithUserDetails(value = "admin")
    public void deletePageWithAdmin() throws Exception {
        this.mockMvc.perform(get("/delete/2"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home?successfullyDeleted"));
    }

    @Test
    @WithUserDetails(value = "SuttyRead1")
    public void deletePageWithUser() throws Exception {
        this.mockMvc.perform(get("/delete/2"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void deletePageWithGuest() throws Exception {
        this.mockMvc.perform(get("/delete/2"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void deleteUnknownUser() throws Exception {
        this.mockMvc.perform(get("/delete/7"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home?unknownId"));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void deleteYourSelfUser() throws Exception {
        this.mockMvc.perform(get("/delete/1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home?deleteYourself"));
    }


}
