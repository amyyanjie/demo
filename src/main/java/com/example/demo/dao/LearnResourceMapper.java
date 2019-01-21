package com.example.demo.dao;

import com.example.demo.domain.LearnResource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.*;


//@Component
//@Repository
@Mapper
public interface LearnResourceMapper {
    int addLearnResource(LearnResource learnResource);

    LearnResource getLearnResourceById(Long id);

    int updateLearnResource(LearnResource learnResource);

}
