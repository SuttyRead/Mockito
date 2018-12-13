package com.ua.sutty.spring.controller;

import com.ua.sutty.spring.domain.User;
import com.ua.sutty.spring.form.UserForm;
import com.ua.sutty.spring.service.UserService;
import com.ua.sutty.spring.validate.ValidateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class AddController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public String getAddPage() {
        return "add";
    }

    @PostMapping("/add")
    public String add(@Valid UserForm userForm, BindingResult bindingResult, Model model) {
        ValidateForm form = new ValidateForm(model, userService, userForm);
        if (!form.checkAlreadyExist() || !form.checkIncorrectDate()
                || !form.checkMatchPassword() || bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("newUser", userForm);
            return "add";
        }
        User user = userForm.toUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/home?successfullyAdded";
    }

}
