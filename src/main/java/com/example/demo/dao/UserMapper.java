package com.example.demo.dao;

import com.example.demo.domain.User;
import org.springframework.stereotype.Component;

//@Mapper
//@Component
public interface UserMapper {

    int addUser(User user);

    User getUserByUserId(int userId);
}
