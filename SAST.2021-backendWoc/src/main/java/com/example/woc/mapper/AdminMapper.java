package com.example.woc.mapper;

import com.example.woc.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {
    //查询全部
    public List<Account> queryAll();
    //根据用户名删除
    public void deleteByName(@Param("username") String username);
    //根据用户名修改权限
    public void userAuthorization(@Param("username") String username,@Param("role") Integer role);
}
