package com.study.myshop.controller;

import com.study.myshop.po.RolePO;
import com.study.myshop.service.IAdminService;
import com.study.myshop.service.IRoleService;
import com.study.myshop.vo.AddAdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * 登录页面
 *
 * @author msl
 * @version 1.0
 * @create 2023-09-20 22:10
 */
@Controller
@RequestMapping(value = "/admin")
public class adminController {

    @Autowired
    private IAdminService iAdminService;


    @Autowired
    private IRoleService iRoleService;

    @RequestMapping(value = "/login")
    public String index(Model model) {
        model.addAttribute("news", "我的项目诞生了!");
        return "admin/login";
    }

    /**
     * 登灵提交方法对应表单控件name，
     * 接收用户名和密码
     * 返回 null 就可以了，会被Security拦截掉的
     *
     * @param username 用户名
     * @param password 密码
     * @return null
     */
    @RequestMapping(value = "/doLogin")
    public String doLogin(String username, String password) {
        return null;
    }

    @RequestMapping(value = "/admin")
    public String admin(Model model) {
        model.addAttribute("adminList", iAdminService.getAdminList());
        return "admin/admin";
    }



    @RequestMapping("/add")
    public String add(Model model, AddAdminVo adminVo){
        if (!StringUtils.isEmpty(adminVo.getAdminName())){
            System.out.println("有表单提交");
        }
        //获取所有的身份列表
        List<RolePO> roleList = iRoleService.getRoleList();
        model.addAttribute("roleList",roleList);
        return "admin/add";
    }


    /***
     * 删除用户
     * @param id 用户id
     * @param model  model
     * @return
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam("id")Integer id,Model model){
        Boolean removed = iAdminService.removeAdminByAdminId(id);
        model.addAttribute("removed",removed?"删除成功!":"删除失败!");
        return "redirect:/admin/admin";
    }
}
