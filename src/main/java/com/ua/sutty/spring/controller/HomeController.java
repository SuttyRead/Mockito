package com.ua.sutty.spring.controller;

import com.ua.sutty.spring.domain.User;
import com.ua.sutty.spring.dto.UserDto;
import com.ua.sutty.spring.security.details.UserDetailsImpl;
import com.ua.sutty.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String getHomePage(Authentication authentication,
                              Model model, HttpServletRequest request) {

        User loggedInUser = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        if (loggedInUser.getRole().getId() == 1) {
            List<UserDto> userDto = userService.findAll().stream()
                    .map(User::toUserDto)
                    .collect(Collectors.toList());
            model.addAttribute("users", userDto);
            if (request.getParameterMap().containsKey("successfullyUpdated")) {
                model.addAttribute("successfullyUpdated", true);
            }
            if (request.getParameterMap().containsKey("successfullyAdded")) {
                model.addAttribute("successfullyAdded", true);
            }
            if (request.getParameterMap().containsKey("successfullyDeleted")) {
                model.addAttribute("successfullyDeleted", true);
            }
            if (request.getParameterMap().containsKey("unknownId")) {
                model.addAttribute("unknownId", true);
            }
            if (request.getParameterMap().containsKey("deleteYourself")) {
                model.addAttribute("deleteYourself", true);
            }
            return "admin";
        } else {
            return "user";
        }

    }

}
