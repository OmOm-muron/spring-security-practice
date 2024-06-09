package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.SecurityUser;
import com.example.service.RoleService;
import com.example.service.SecurityUserService;

/**
 * User Management Pages (add-user / userlist) are created to add test users which are used for login test.
 */
@Controller
public class UserManagerController {
    @Autowired
    private final SecurityUserService securityUserService;
    @Autowired
    private final RoleService roleService;

    public UserManagerController(SecurityUserService securityUserService, RoleService roleService) {
        this.securityUserService = securityUserService;
        this.roleService = roleService;
    }
    
    @GetMapping("/userlist")
    public String showUserList(Model model) {
        List<SecurityUser> userList = securityUserService.getAllSecurityUsers();
        model.addAttribute("securityUsers", userList);
        return "userlist";
    }
    
    @GetMapping("/add-user")
    public String showAddUserPage() {
        return "add-user";
    }
    
    @PostMapping("/add-user")
    public String addEmployee(@ModelAttribute SecurityUser securityUser) {
        securityUserService.addSecurityUser(securityUser);
        roleService.addRole(securityUser);
        return "redirect:/userlist";
    }
}
