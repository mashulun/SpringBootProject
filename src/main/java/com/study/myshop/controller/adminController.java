package com.study.myshop.controller;

import com.study.myshop.po.RolePO;
import com.study.myshop.service.IAdminService;
import com.study.myshop.service.IRoleService;
import com.study.myshop.vo.AddAdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


    /***
     * 添加用户页面
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(Model model){
        //获取所有的身份列表
        List<RolePO> roleList = iRoleService.getRoleList();
        model.addAttribute("roleList",roleList);
        return "admin/add";
    }


    /***
     * 获取表单提交:获取表单提交自动封装成的VO对象
     * @param adminVo vo
     * @return 员工列表页面
     */
    @RequestMapping(value = "/save")
    public String save(AddAdminVo adminVo){
        //担心数据在内存上泄露，这里加密
        //拿出原始的
        String adminPass = adminVo.getAdminPass();
        //加密:可以单独加载Security依赖,使用这个加密
        adminPass = new BCryptPasswordEncoder().encode(adminPass);
        adminVo.setAdminPass(adminPass);
        iAdminService.addAdminInfo(adminVo);
        //路径重定向(列表页)
        return "redirect:/admin/admin";
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
