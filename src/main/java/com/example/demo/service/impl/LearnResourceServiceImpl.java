package com.example.demo.service.impl;

import com.example.demo.dao.LearnResourceMapper;
import com.example.demo.domain.LearnResource;
import com.example.demo.service.ILearnResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LearnResourceServiceImpl implements ILearnResourceService {
    @Autowired
    private LearnResourceMapper learnResourceMapper;

    @Override
    public int addLearnResource(LearnResource learnResource) {
        return learnResourceMapper.addLearnResource(learnResource);
    }

    @Override
    public LearnResource getLearnResourceById(int id) {
        return learnResourceMapper.getLearnResourceById(id);
    }

    @Override
    public int updateLearnResource(LearnResource learnResource) {
        return learnResourceMapper.updateLearnResource(learnResource);
    }
}
