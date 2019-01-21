package com.example.demo.dao;

import com.example.demo.domain.LearnResource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.*;


@Component  //这里@Component注解无关紧要，加上后@Autowired不会有红色下划线
//@Mapper   //mapper上添加@Mapper，或在DemoApplication中添加@MapperScan注解
public interface LearnResourceMapper {
    int addLearnResource(LearnResource learnResource);

    LearnResource getLearnResourceById(Long id);

    int updateLearnResource(LearnResource learnResource);

}
