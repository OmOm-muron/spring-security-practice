package com.example.entity;

import lombok.Data;

@Data
public class SecurityUser {
    private String username;
    private String password;
    private boolean enabled;
}
