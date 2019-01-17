package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.domain.User;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
//    @Autowired
//    private UserMapper userMapper;
    @Override
    public int addUser(User user) {
        return 0;
    }

    @Override
    public User getUserByUserId(int userId) {
        return null;
    }
}
