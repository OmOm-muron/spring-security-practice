package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.entity.SecurityUser;

@Mapper
public interface RoleMapper {

    void addRole(SecurityUser securityUser);
}
