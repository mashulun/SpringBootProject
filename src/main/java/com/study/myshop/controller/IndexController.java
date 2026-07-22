package com.study.myshop.controller;

import com.study.myshop.service.IIndex;
import com.study.myshop.vo.AdminInfoVo;
import com.study.myshop.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 主页面
 *
 * @author msl
 * @version 1.0
 * @create 2023-09-20 21:30
 */
@Controller
@RequestMapping(value = {"/", "index"})
public class IndexController {

    @Autowired
    private IIndex iIndex;


    /***
     * 主界面
     * @return 主页
     */
    @RequestMapping(value = {"/", "/index"})
    public String index(Authentication authentication, HttpSession session) {
        //获取当前登录用户的信息
        String userName = authentication.getName();
        // 菜单树构建逻辑已移至Service层
        List<MenuVo> menuVoList = iIndex.getMenuTreeByAdminName(userName);
        session.setAttribute("menuVo", menuVoList);
        // 管理员信息组装逻辑已移至Service层
        AdminInfoVo adminInfoVo = iIndex.getAdminInfoByAdminName(userName);
        session.setAttribute("adminInfo", adminInfoVo);
        return "index/index";
    }
}
