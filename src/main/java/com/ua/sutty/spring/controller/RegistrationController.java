package com.ua.sutty.spring.controller;

import com.ua.sutty.spring.dto.CaptchaResponseDto;
import com.ua.sutty.spring.form.UserForm;
import com.ua.sutty.spring.service.SignUpService;
import com.ua.sutty.spring.service.UserService;
import com.ua.sutty.spring.validate.ValidateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@Controller
public class RegistrationController {

    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Value("${recaptcha.secret}")
    private String secret;

    private final RestTemplate restTemplate;

    private final SignUpService signUpService;

    private final UserService userService;

    @Autowired
    public RegistrationController(RestTemplate restTemplate, SignUpService signUpService, UserService userService) {
        this.restTemplate = restTemplate;
        this.signUpService = signUpService;
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String getLoginPage(Principal principal) {
        if (principal != null) {
            return "redirect:/home";
        }
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid UserForm userForm,
                               @RequestParam("g-recaptcha-response") String captchaResponse,
                               BindingResult bindingResult, Model model) {

        String url = String.format(CAPTCHA_URL, secret, captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(
                url, Collections.emptyList(), CaptchaResponseDto.class);

        if (!Objects.requireNonNull(response).isSuccess()) {
            model.addAttribute("captchaError", "Fill captcha");
        }

        ValidateForm form = new ValidateForm(model, userService, userForm);
        if (!form.checkAlreadyExist() || !form.checkIncorrectDate() || !response.isSuccess()
                || !form.checkMatchPassword() || bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("newUser", userForm);
            return "registration";
        }

        signUpService.signUp(userForm);
        return "redirect:/login?signUpSuccess";

    }

}
