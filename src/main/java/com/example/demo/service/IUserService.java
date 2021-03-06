package com.example.demo.service;

import com.example.demo.domain.User;

import java.util.List;

public interface IUserService {
    static final int i = 0;
    int addUser(User user);

    User getUserByUserId(int userId);

    User getUserByMobile(String mobile);

    List<User> getAllUserList();
}
