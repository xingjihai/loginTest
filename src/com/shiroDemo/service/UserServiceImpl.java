package com.shiroDemo.service;

import org.springframework.stereotype.Service;

import com.shiroDemo.User;

@Service
public class UserServiceImpl implements UserService{

    public User doUserLogin(User user) {
        if("admin".equals(user.getUsername())&&"admin".equals(user.getPassword())){
            User user1=new User();
            user1.setUsername("admin");
            user1.setPassword("admin");
            user1.setRole("add");
            return user1;
        }else{
            return null;
        }
    }

    public String getString() {
        return "getString success";
    }
}
