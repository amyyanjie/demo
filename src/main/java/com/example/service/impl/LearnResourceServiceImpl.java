package com.example.service.impl;

import com.example.dao.LearnResourceMapper;
import com.example.domain.LearnResource;
import com.example.service.ILearnResourceService;
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
    public LearnResource getLearnResourceById(long id) {
        return learnResourceMapper.getLearnResourceById(id);
    }

    @Override
    public int updateLearnResource(LearnResource learnResource) {
        return learnResourceMapper.updateLearnResource(learnResource);
    }
}
