package com.example.demo.service;

import com.example.demo.domain.LearnResource;

public interface ILearnResourceService {
    int addLearnResource(LearnResource learnResource);

    LearnResource getLearnResourceById(long id);

    int updateLearnResource(LearnResource learnResource);

}
