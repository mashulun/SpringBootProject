package com.study.myshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页面
 * @author msl
 * @version 1.0
 * @create 2023-09-20 21:30
 */
@Controller
@RequestMapping(value = {"/","index"})
public class indexController {

    @RequestMapping(value = {"/","/index"})
    public String index(){
        return "index/index";
    }
}
