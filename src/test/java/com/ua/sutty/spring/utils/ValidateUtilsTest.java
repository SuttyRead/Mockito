package com.ua.sutty.spring.utils;

import com.ua.sutty.spring.app.Application;
import com.ua.sutty.spring.form.UserForm;
import com.ua.sutty.spring.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import java.sql.Date;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ValidateUtilsTest {

    @Autowired
    private UserService userService;

    private ValidateUtis validateUtis;
    private Model model;
    private UserForm userForm;

    @Before
    public void loadField() {
        userForm = new UserForm("login", "password", "password",
                "email@gmail.com", "user", "user", Date.valueOf("1990-08-07"));
        validateUtis = new ValidateUtis(model, userService, userForm);

    }

    @Test
    public void checkMatchPasswordTest() {
        assertTrue(validateUtis.checkMatchPassword());
    }

    @Test
    public void checkAlreadyExistTest() {
        assertTrue(validateUtis.checkAlreadyExist());
    }

    @Test
    public void checkIncorrectDateTest() {
        assertTrue(validateUtis.checkIncorrectDate());
    }


}
