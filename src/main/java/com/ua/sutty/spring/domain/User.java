package com.ua.sutty.spring.domain;

import com.ua.sutty.spring.dto.UserDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String login;
    private String password;

    @Transient
    private String confirmPassword;

    @Email
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private Date birthday;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    public UserDto toUserDto() {
        int yearBirthday = birthday.toLocalDate().getYear();
        int yearNow = LocalDate.now().getYear();
        return UserDto.builder()
                .id(id)
                .login(login)
                .firstName(firstName)
                .lastName(lastName)
                .age(yearNow - yearBirthday)
                .role(role)
                .build();
    }

}
