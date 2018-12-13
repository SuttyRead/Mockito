package com.ua.sutty.spring.form;

import com.ua.sutty.spring.domain.Role;
import com.ua.sutty.spring.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserForm {

    @NotBlank(message = "Login must not be empty")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-_.]{1,20}$",
            message = "Login not pattern")
    private String login;

    @NotBlank(message = "Password must not be empty")
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",
            message = "Password not pattern")
    private String password;

    @NotBlank(message = "Confirm password must not be empty")
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",
            message = "Confirm password not pattern")
    private String confirmPassword;

    @NotBlank(message = "Email must not be empty")
    @Email(message = "Email not pattern")
    private String email;

    @NotBlank(message = "First name must not be empty")
    @Pattern(regexp = "^[A-Z]{1}[a-z]{1,25}", message = "FirstName not pattern")
    private String firstName;

    @NotBlank(message = "Last name must not be empty")
    @Pattern(regexp = "^[A-Z]{1}[a-z]{1,25}", message = "LastName not pattern")
    private String lastName;

    private Date birthday;

    public User toUser() {
        Role role = new Role(2L, "USER");
        return User.builder()
                .login(login)
                .password(password)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .birthday(birthday)
                .role(role)
                .build();
    }

}
