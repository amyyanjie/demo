package com.example.service;

import com.example.domain.LearnResource;

public interface ILearnResourceService {
    int addLearnResource(LearnResource learnResource);

    LearnResource getLearnResourceById(long id);

    int updateLearnResource(LearnResource learnResource);

}
