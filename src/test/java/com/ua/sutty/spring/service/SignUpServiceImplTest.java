package com.ua.sutty.spring.service;

import com.ua.sutty.spring.app.Application;
import com.ua.sutty.spring.domain.Role;
import com.ua.sutty.spring.domain.User;
import com.ua.sutty.spring.form.UserForm;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SignUpServiceImplTest {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    private UserForm userForm;
    private User user;

    @Before
    public void loadField() {
        userForm = new UserForm("login", "password", "password", "email@gmail.com",
                "firstName", "lastName", Date.valueOf("1995-10-20"));
        user = User.builder()
                .login(userForm.getLogin())
                .password(userForm.getPassword())
                .email(userForm.getEmail())
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .birthday(userForm.getBirthday())
                .role(new Role(2L, "USER"))
                .build();
    }

    @Test
    public void signUpTest() {
        signUpService.signUp(userForm);
        User userByLogin = userService.findUserByLogin(userForm.getLogin());
        assertEquals(userByLogin.getLogin(),user.getLogin());
        assertEquals(userByLogin.getEmail(),user.getEmail());
        assertEquals(userByLogin.getFirstName(),user.getFirstName());
        assertEquals(userByLogin.getLastName(),user.getLastName());
        assertEquals(userByLogin.getBirthday(),user.getBirthday());
    }

}
