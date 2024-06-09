package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.entity.SecurityUser;

@Mapper
public interface SecurityUserMapper {

    List<SecurityUser> getAllSecurityUsers();

    void addSecurityUser(SecurityUser securityUser);
}
