package com.ua.sutty.spring.utils;

import com.ua.sutty.spring.form.UserForm;
import com.ua.sutty.spring.service.UserService;
import org.springframework.ui.Model;

import java.time.LocalDate;

public class ValidateUtis {

    private Model model;
    private UserService userService;
    private UserForm userForm;

    public ValidateUtis(Model model, UserService userService, UserForm userForm) {
        this.model = model;
        this.userService = userService;
        this.userForm = userForm;
    }

    public Boolean checkAlreadyExist() {
        if (userService.findUserByLogin(userForm.getLogin()) == null) {
            return true;
        } else {
            model.addAttribute("loginAlreadyExist", "Login already exist");
            return false;
        }
    }

    public Boolean checkMatchPassword() {
        if (userForm.getPassword().equals(userForm.getConfirmPassword())) {
            return true;
        } else {
            model.addAttribute("passwordError", "Passwords are different!");
            return false;
        }
    }

    public Boolean checkIncorrectDate() {
        if (userForm.getBirthday().toLocalDate().isBefore(LocalDate.now())) {
            return true;
        } else {
            model.addAttribute("incorrectDate", "Incorrect birthday");
            return false;
        }
    }

}
