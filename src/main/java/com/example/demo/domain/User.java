package com.example.demo.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = -2505035389234495754L;
    private int userId;
    private String name;
    private String idCardNo;
    private String mobile;

    public User() {
    }

    public User(int userId) {
        this.userId = userId;
    }
}
