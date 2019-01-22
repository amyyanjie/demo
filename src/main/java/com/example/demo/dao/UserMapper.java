package com.example.demo.dao;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

//@Mapper
@Component
public interface UserMapper {

    @Insert("INSERT INTO user_info (name,idCardNo,mobile) VALUES (#{name},#{idCardNo},#{mobile})")
    @Options(useGeneratedKeys = true, keyProperty = "userId") //这样可以之后的java代码中获取该主键对应的对象的属性值（userId）
    int addUser(User user);

    @Select("select * from user_info where userId=#{userId}")
    User getUserByUserId(@Param("userId") int userId);

    @Select("select * from user_info where mobile=#{userId}")
    User getUserByMobile(@Param("userId") String mobile);

    @Select("select * from user_info")
    List<User> getAllUserList();
}
