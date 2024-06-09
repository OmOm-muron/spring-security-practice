package com.example.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.SecurityUser;
import com.example.mapper.SecurityUserMapper;

@Service
public class SecurityUserService {

    private final SecurityUserMapper securityUserMapper;

    public SecurityUserService(SecurityUserMapper securityUserMapper) {
        this.securityUserMapper = securityUserMapper;
    }

    public List<SecurityUser> getAllSecurityUsers() {
        return securityUserMapper.getAllSecurityUsers();
    }

    public void addSecurityUser(SecurityUser securityUser) {
        // Encrypt password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = securityUser.getPassword();
        securityUser.setPassword(encoder.encode(password));
        securityUserMapper.addSecurityUser(securityUser);
    }
}
