package com.example.demo.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class LearnResource implements Serializable {
    private static final long serialVersionUID = 6141624625126734916L;

    public LearnResource(String author, String title, String url) {
        this.author = author;
        this.title = title;
        this.url = url;
    }

    private int id;
    private String author;
    private String title;
    private String url;
}
