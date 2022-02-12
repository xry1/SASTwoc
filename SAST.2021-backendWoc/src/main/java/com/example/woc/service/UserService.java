package com.example.woc.service;

import com.example.woc.entity.Account;
import com.example.woc.mapper.UserMapper;
import com.example.woc.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 01:22
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    //示例
    public void test(String test) {
        userMapper.test(test);
    }

    //添加用户数据（注册）
    public int add(Account account) {
        Integer id = account.getId();
        String username = account.getUsername();
        String password = account.getPassword();
        //密码进行MD5加密
        MD5Utils md5Utils = new MD5Utils();
        String nPwd = md5Utils.md5(password);

        String email = account.getEmail();

        return userMapper.add(id,username,nPwd,email);
    }

    //根据用户名查询
    public Account queryByName(String username) {
        return userMapper.queryByName(username);
    }


}
