package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.SecurityUser;
import com.example.mapper.RoleMapper;

@Service
public class RoleService {

    private final RoleMapper roleMapper;

    public RoleService(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public void addRole(SecurityUser securityUser) {
        roleMapper.addRole(securityUser);
    }
}
