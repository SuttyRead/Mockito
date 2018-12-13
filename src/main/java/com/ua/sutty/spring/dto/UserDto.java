package com.ua.sutty.spring.dto;

import com.ua.sutty.spring.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String login;
    private String firstName;
    private String lastName;
    private Integer age;
    private Role role;

}
