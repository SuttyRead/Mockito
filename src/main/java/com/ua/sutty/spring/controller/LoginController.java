package com.ua.sutty.spring.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage(Authentication authentication,
                               ModelMap modelMap, HttpServletRequest request) {
        if (authentication != null) {
            return "redirect:/";
        }
        if (request.getParameterMap().containsKey("error")) {
            modelMap.addAttribute("error", true);
        }
        if (request.getParameterMap().containsKey("signUpSuccess")) {
            modelMap.addAttribute("signUpSuccess", true);
        }
        return "login";
    }

}
