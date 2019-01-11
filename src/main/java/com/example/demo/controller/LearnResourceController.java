package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping("/learn/resource")
public class LearnResourceController {

    @GetMapping("/")
    public ModelAndView getLearnResource(){
        return null;
    }
}
