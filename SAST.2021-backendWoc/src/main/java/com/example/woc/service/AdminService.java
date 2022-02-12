package com.example.woc.service;


import com.example.woc.entity.Account;
import com.example.woc.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    //查询全部
    public List<Account> queryAll() {
        return adminMapper.queryAll();
    }

    //根据用户名删除
    public void deleteByName(String username) {
         adminMapper.deleteByName(username);
    }

    //根据用户名授予权限
    public void userAuthorization(String username,Integer role){
        adminMapper.userAuthorization(username,role);
    }
}
