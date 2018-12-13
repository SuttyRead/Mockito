package com.ua.sutty.spring.service;

import com.ua.sutty.spring.domain.User;
import com.ua.sutty.spring.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        if (user == null) {
            log.error("User == null", new NullPointerException());
            throw new NullPointerException();
        }
        try {
            log.trace("Call method save");
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Error in time save user", e);
            throw e;
        }
    }

    public void delete(User user) {
        if (user == null) {
            log.error("Role == null", new NullPointerException());
            throw new NullPointerException();
        }
        try {
            log.trace("Call method delete");
            userRepository.delete(user);
        } catch (Exception e) {
            log.error("Error in time delete user", e);
            throw e;
        }
    }

    public List<User> findAll() {
        try {
            log.trace("Call method findAll");
            return userRepository.findAll();
        } catch (Exception e) {
            log.error("Error in time findAll", e);
            throw e;
        }
    }

    public User findUserByEmail(String email) {
        if (email == null) {
            log.error("Email == null", new NullPointerException());
            throw new NullPointerException();
        }
        try {
            log.trace("Call method findUserByEmail");
            return userRepository.findUserByEmail(email);
        } catch (Exception e) {
            log.error("Error in time findUserByEmail", e);
            throw e;
        }
    }

    public User findUserByLogin(String login) {
        if (login == null) {
            log.error("Email == null", new NullPointerException());
            throw new NullPointerException();
        }
        try {
            log.trace("Call method findUserByLogin");
            return userRepository.findUserByLogin(login);
        } catch (Exception e) {
            log.error("Error in time findUserByLogin", e);
            throw e;
        }
    }

    public Optional<User> findOneByLogin(String login) {
        if (login == null) {
            log.error("Email == null", new NullPointerException());
            throw new NullPointerException();
        }
        try {
            log.trace("Call method findOneByLogin");
            return userRepository.findOneByLogin(login);
        } catch (Exception e) {
            log.error("Error in time findOneByLogin", e);
            throw e;
        }
    }

    public User findUserById(Long id) {
        if (id == null) {
            log.error("User(id) == null", new NullPointerException());
            throw new NullPointerException();
        }
        try {
            log.trace("Call method findUserById");
            return userRepository.findUserById(id);
        } catch (Exception e) {
            log.error("Error in time findUserById", e);
            throw e;
        }
    }

    public void deleteUserById(Long id) {
        if (id == null) {
            log.error("User(id) == null", new NullPointerException());
            throw new NullPointerException();
        }
        try {
            log.trace("Call method deleteUserById");
            userRepository.deleteUserById(id);
        } catch (Exception e) {
            log.error("Error in time deleteUserById", e);
            throw e;
        }
    }

}
