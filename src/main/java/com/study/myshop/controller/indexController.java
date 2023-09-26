package com.study.myshop.controller;

import com.study.myshop.po.MenuPO;
import com.study.myshop.service.IIndex;
import com.study.myshop.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        // 按照这个信息查询出它的权限所对应的所有菜单
        List<MenuPO> menuList = iIndex.getMenuListByAdminName(userName);

        //通过代码组合自己需要的VO对象
        List<MenuVo> menuVoList = new ArrayList<>(menuList.size());
        //第二步:循环判断,创建一级菜单对象放入有
        for (MenuPO menu : menuList) {
            if (menu.getPid() == null) {
                MenuVo menuVo = new MenuVo();
                menuVo.setMenu(menu);
                menuVoList.add(menuVo);
            }
        }
        //循环遍历刚才Vo大集合,为每一个一级菜单放入二级菜单
        for (MenuVo mv : menuVoList) {
            //一级菜单的id
            Integer menuId = mv.getMenu().getMenuId();
            for (MenuPO m : menuList) {
                if (Objects.equals(m.getPid(), menuId)) {
                    mv.getMenus().add(m);
                }
            }
        }
        // 菜单放到 session 中，这样每一个控制器都可以使用了
        session.setAttribute("menuVo",menuVoList);
        session.setAttribute("userName",userName);
        return "index/index";
    }
}
