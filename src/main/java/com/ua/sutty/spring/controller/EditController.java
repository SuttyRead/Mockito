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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class EditController {

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    @Autowired
    public EditController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable Long id, Model model) {
        if (userService.findUserById(id) == null) {
            return "redirect:/home?unknownId";
        }
        model.addAttribute("userForEdit", userService.findUserById(id));
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid UserForm userForm, BindingResult bindingResult,
                       Model model, @PathVariable Long id) {
        ValidateForm form = new ValidateForm(model, userService, userForm);
        if (!form.checkIncorrectDate() | !form.checkMatchPassword()
                | bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            User userForEdit = userForm.toUser();
            userForEdit.setId(id);
            model.addAttribute("userForEdit", userForEdit);
            return "edit";
        }
        User user = userForm.toUser();
        user.setId(id);
        if (!user.getPassword().equals(userService.findUserById(id).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userService.save(user);
        return "redirect:/home?successfullyUpdated";
    }

}
