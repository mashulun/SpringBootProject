package com.study.myshop.controller;

import com.study.myshop.po.MenuPO;
import com.study.myshop.service.IIndex;
import com.study.myshop.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 主页面
 *
 * @author msl
 * @version 1.0
 * @create 2023-09-20 21:30
 */
@Controller
@RequestMapping(value = {"/", "index"})
public class indexController {

    @Autowired
    private IIndex iIndex;


    /***
     * 主界面
     * @return 主页
     */
    @RequestMapping(value = {"/", "/index"})
    public String index(Authentication authentication, HttpSession session) {
        //获取当前登录用户的信息
        //打开页面之前,已经Security已经执行过了
        //它里面有一个对象 Authentication
        //就是当前登录的对象,只有两个方法,用户名和密码
        String userName = authentication.getName();
        // 一次性查询用户对应的所有菜单
        List<MenuPO> menuList = iIndex.getMenuListByAdminName(userName);
        // 使用Map存储菜单ID和对应的菜单对象
        Map<Integer, MenuVo> menuMap = new HashMap<>();
        // 第一次遍历，创建一级菜单对象，并存入map中
        for (MenuPO menu : menuList) {
            if (menu.getPid() == null) {
                MenuVo menuVo = new MenuVo();
                menuVo.setMenu(menu);
                menuMap.put(menu.getMenuId(), menuVo);
            }
        }
        // 第二次遍历，将子菜单连接到对应的父菜单中
        for (MenuPO menu : menuList) {
            Integer parentId = menu.getPid();
            if (parentId != null) {
                MenuVo parentMenu = menuMap.get(parentId);
                if (parentMenu != null) {
                    parentMenu.getMenus().add(menu);
                }
            }
        }
        // 提取一级菜单作为最终的菜单列表
        List<MenuVo> menuVoList = new ArrayList<>(menuMap.values());
        session.setAttribute("menuVo", menuVoList);
        session.setAttribute("userName", userName);
        return "index/index";
    }
}
