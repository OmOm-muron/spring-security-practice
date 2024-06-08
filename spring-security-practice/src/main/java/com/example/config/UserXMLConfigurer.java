package com.example.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserXMLConfigurer {
    public static List<UserDetails> getUsers(String xmlFileName) throws Exception {
        try {
            List<UserDetails> userList = new ArrayList<>();
            SAXBuilder saxBuilder = new SAXBuilder();
            File xmlFile = new File(xmlFileName);
            Document document = saxBuilder.build(xmlFile);
            
            Element root = document.getRootElement();
            List<Element> userElmList = root.getChildren("user");
            for (Iterator<Element> it = userElmList.iterator(); it.hasNext();) {
                // create User instance and add it to List
                // read user definition from XML
                Element userElm = it.next();
                String username = userElm.getChildText("username");
                String password = userElm.getChildText("password");
                String role = userElm.getChildText("role");
                // convert role to array
                String[] roleArray = role.split(",");
                // create encoder instance for password encryption
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                // call constructor and add it to List
                UserDetails user = User.builder()
                                                .username(username)
                                                .password(encoder.encode(password))
                                                .roles(roleArray)
                                                .build();
                userList.add(user);
            }
            return userList;
        } catch (Exception e) {
            throw e;
        }
    }
}
