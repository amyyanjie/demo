package com.example.service.impl;

import com.example.dao.UserMapper;
import com.example.domain.User;
import com.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public User getUserByUserId(int userId) {
        return userMapper.getUserByUserId(userId);
    }

    @Override
    public User getUserByMobile(String mobile){
        return userMapper.getUserByMobile(mobile);
    }

    @Override
    public List<User> getAllUserList(){
        return userMapper.getAllUserList();
    }
}
