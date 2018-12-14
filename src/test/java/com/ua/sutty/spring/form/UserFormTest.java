package com.ua.sutty.spring.form;

import com.ua.sutty.spring.app.Application;
import com.ua.sutty.spring.domain.Role;
import com.ua.sutty.spring.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserFormTest {

    private UserForm userForm;
    private User user;

    @Before
    public void loadField(){
        userForm = new UserForm("login", "password", "password", "email",
                "firstName", "lastName", Date.valueOf("1997-03-06"));
        user = User.builder()
                .login("login")
                .password("password")
                .email("email")
                .firstName("firstName")
                .lastName("lastName")
                .birthday(Date.valueOf("1997-03-06"))
                .role(new Role(2L, "USER"))
                .build();
    }

    @Test
    public void toUserTest(){
        User userFromForm = userForm.toUser();
        Assert.assertEquals(user, userFromForm);
    }


}
