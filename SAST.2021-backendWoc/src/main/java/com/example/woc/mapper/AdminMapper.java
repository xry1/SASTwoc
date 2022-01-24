package com.example.woc.mapper;

import com.example.woc.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {
    //查询全部
    public List<Account> queryAll();
    //根据用户名删除
    public void deleteByName(String username);

}
