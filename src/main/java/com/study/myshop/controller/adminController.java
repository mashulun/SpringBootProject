package com.study.myshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录页面
 * @author msl
 * @version 1.0
 * @create 2023-09-20 22:10
 */
@Controller
@RequestMapping(value = "/admin")
public class adminController  {


    @RequestMapping(value ="/login")
    public String index(Model model){
        model.addAttribute("news","我的项目诞生了!");
        return "admin/login";
    }


    /**
     * 登灵提交方法对应表单控件name，
     * 接收用户名和密码
     * 返回 null 就可以了，会被Security拦截掉的
     * @param username 用户名
     * @param password 密码
     * @return null
     */
    @RequestMapping(value = "/doLogin")
    public String doLogin(String username,String password){
        return null;
    }
}
