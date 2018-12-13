package com.ua.sutty.spring.repository;

import com.ua.sutty.spring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);

    User findUserByLogin(String login);

    User findUserByEmail(String email);

    void deleteUserById(Long id);

    Optional<User> findOneByLogin(String login);

}
