package com.ua.sutty.spring.service;

import com.ua.sutty.spring.domain.Role;
import com.ua.sutty.spring.domain.User;
import com.ua.sutty.spring.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Override
    public void signUp(UserForm userForm) {
        String hashPassword = passwordEncoder.encode(userForm.getPassword());
        Role role = new Role(2L, "USER");
        User user = User.builder()
                .login(userForm.getLogin())
                .password(hashPassword)
                .email(userForm.getEmail())
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .birthday(userForm.getBirthday())
                .role(role)
                .build();

        userService.save(user);
    }

}
