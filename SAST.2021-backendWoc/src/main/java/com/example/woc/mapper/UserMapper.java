package com.example.woc.mapper;

import com.example.woc.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 01:22
 **/
@Mapper
@Repository
public interface UserMapper {
    //示例
    void test(@Param("value") String test);
    //添加用户数据(注册)
     int add(@Param("id") Integer id,@Param("username") String username,@Param("password") String password,@Param("email") String email);
    //根据用户名查询
     Account queryByName(@Param("username") String username);

}
