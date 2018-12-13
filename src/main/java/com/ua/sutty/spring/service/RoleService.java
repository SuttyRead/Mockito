package com.ua.sutty.spring.service;

import com.ua.sutty.spring.domain.Role;
import com.ua.sutty.spring.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void save(Role role) {
        if (role == null) {
            log.error("Role == null", new NullPointerException());
            throw new NullPointerException();
        }
        try {
            log.trace("Call method save");
            roleRepository.save(role);
        } catch (Exception e) {
            log.error("Error in time save role", e);
            throw e;
        }
    }

    public void delete(Role role) {
        if (role == null) {
            log.error("Role == null", new NullPointerException());
            throw new NullPointerException();
        }
        try {
            log.trace("Call method delete");
            roleRepository.delete(role);
        } catch (Exception e) {
            log.error("Error in time delete role", e);
            throw e;
        }
    }

    public Role findRoleByName(String name) {
        if (name == null) {
            log.error("Name == null", new NullPointerException());
            throw new NullPointerException();
        }
        try {
            log.trace("Call method findRoleByName");
            return roleRepository.findRoleByName(name);
        } catch (Exception e) {
            log.error("Error in time findRoleByName", e);
            throw e;
        }
    }

    public Role findRoleById(Long id) {
        if (id == null) {
            log.error("Role(id) == null", new NullPointerException());
            throw new NullPointerException();
        }
        try {
            log.trace("Call method findRoleById");
            return roleRepository.findRoleById(id);
        } catch (Exception e) {
            log.error("Error in time findRoleById", e);
            throw e;
        }
    }

}
