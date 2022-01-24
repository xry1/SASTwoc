package com.example.woc.controller;

import com.example.woc.entity.Account;
import com.example.woc.service.UserService;
import com.example.woc.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 01:22
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //这是一个示例,以POST方法提交数据
    @PostMapping("/simple")
    public void simple(String test) {
        //按住ctrl键来看看具体调用的这个函数吧
        userService.test(test);
    }

    /**
     * 完成注册功能
     * 选做：对密码进行加密处理
     * @param account
     */
    @PostMapping("/register")
    public void uploadUsername(Account account) {

        //todo
        userService.add(account);
    }

    /**
     * 完成登录功能
     * @param account
     * @return 是否登录成功
     */
    @PostMapping("/login")
    public Boolean login(Account account) {
        //todo
        MD5Utils md5Utils = new MD5Utils();
        try {
            String username = account.getUsername();
            Account accountSelect = userService.queryByName(username);
            String pwd = accountSelect.getPassword();
            String pwdInput = md5Utils.md5(account.getPassword());
            if (pwd.equals(pwdInput)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}


