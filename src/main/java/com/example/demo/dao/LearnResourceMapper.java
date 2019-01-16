package com.example.demo.dao;

import com.example.demo.domain.LearnResource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LearnResourceMapper {
    int addLearnResource(LearnResource learnResource);

    LearnResource getLearnResourceById(int id);

    int updateLearnResource(LearnResource learnResource);

}
