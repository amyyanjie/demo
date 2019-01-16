package com.example.demo.service;

import com.example.demo.domain.User;

public interface IUserService {
    int addUser(User user);
    User getUserByUserId(int userId);
}
