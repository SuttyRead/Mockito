package com.ua.sutty.spring.controller;

import com.ua.sutty.spring.security.details.UserDetailsImpl;
import com.ua.sutty.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DeleteController {

    private final UserService userService;

    @Autowired
    public DeleteController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Authentication authentication) {
        if (userService.findUserById(id).equals(((UserDetailsImpl) authentication
                .getPrincipal()).getUser())) {
            return "redirect:/home?deleteYourself";
        }
        userService.deleteUserById(id);
        return "redirect:/home?successfullyDeleted";
    }

}
