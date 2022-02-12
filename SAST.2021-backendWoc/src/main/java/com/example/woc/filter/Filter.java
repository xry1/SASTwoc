package com.example.woc.filter;

import com.alibaba.fastjson.JSON;
import com.example.woc.entity.Account;
import com.example.woc.entity.Result;
import com.example.woc.entity.UserLoginInfo;
import com.example.woc.mapper.UserMapper;
import com.example.woc.util.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
@WebFilter("/*")
public class Filter implements javax.servlet.Filter {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器启动了...");
        javax.servlet.Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //返回信息
        Result result = new Result();
        ReturnData returnData = new ReturnData();
        // 基于HTTP请求
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        // 获取请求的路径
        String url = request.getRequestURI();

        // 1.对于指定页面放行（注册、登录页面 暂无）

        // 2.对于静态资源放行(image、js、css文件等 暂无)

        // 3.对于指定操作放行（无需登录即可执行的操作，例如：登录操作，注册操作）
        if (url.contains("/login") || url.contains("/register")) {
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        // 4.登录状态放行（判断session中用户信息是否为空）
        // 取得 HttpSession 对象
        HttpSession session = request.getSession();

        // 读取登录信息
        UserLoginInfo userLoginInfo = (UserLoginInfo)session.getAttribute("userLoginInfo");
        if (userLoginInfo == null) {
            // 未登录
            result = new Result(false,"未登录","401",null);
            returnData.returnJson(request,response,result);
            return;
        } else {
            // 已登录
        }

        if (url.contains("/admin")) {
            // 得到role的值
            String role = userLoginInfo.getUserId();
            // 根据role授权
            if (role.equals("1") || role.equals("2")){

            }else {
                // 权限不足
                result = new Result(false,"权限不足","403",null);
                returnData.returnJson(request,response,result);
                return;
            }
        }

        if (url.contains("/getAmount")){
            filterChain.doFilter(request,response);
            return;
        }

        if (url.contains("/deleteAccount")) {
            //得到需要删除的用户
            String username = request.getParameter("username");
            Account userToDelete = userMapper.queryByName(username);
            // 得到role的值
            Integer role =  Integer.valueOf( userLoginInfo.getUserId());
            // 根据role授权
            if (role > userToDelete.getRole()){
                //权限满足 放行
                result = new Result(true,"null","null","用户"+userToDelete.getUsername()+"已删除");
                returnData.returnJson(request,response,result);
                filterChain.doFilter(request,response);
                return;
            }else {
                // 权限不足
                result = new Result(false,"权限不足","403","null");
                returnData.returnJson(request,response,result);
            }
        }
        if (url.contains("/userAuthorization")) {
            //得到需要删除的用户
            String username = request.getParameter("username");
            Account userToDelete = userMapper.queryByName(username);
            // 得到role的值
            Integer role =  Integer.valueOf( userLoginInfo.getUserId());
            // 根据role授权
            if (role > userToDelete.getRole()){
                //权限满足 放行
                result = new Result(true,"null","null","用户"+userToDelete.getUsername()+"权限已修改");
                returnData.returnJson(request,response,result);
                filterChain.doFilter(request,response);
                return;
            }else {
                // 权限不足
                result = new Result(false,"权限不足","403","null");
                returnData.returnJson(request,response,result);
            }
        }


        // doFilter()放行方法前去做请求拦截的
        //filterChain.doFilter(servletRequest,servletResponse);
        // doFilter()放行方法后去做响应拦截的

    }

    @Override
    public void destroy() {
        javax.servlet.Filter.super.destroy();
    }


}
