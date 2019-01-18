package com.example.demo.dao;

import com.example.demo.domain.LearnResource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
//@Component
@Repository
public interface LearnResourceMapper {
    int addLearnResource(LearnResource learnResource);

    LearnResource getLearnResourceById(int id);

    int updateLearnResource(LearnResource learnResource);

}
