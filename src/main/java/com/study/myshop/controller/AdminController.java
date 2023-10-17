package com.study.myshop.controller;

import com.study.myshop.po.AdminPO;
import com.study.myshop.po.RolePO;
import com.study.myshop.service.IAdminService;
import com.study.myshop.service.IRoleService;
import com.study.myshop.vo.AddAdminVo;
import com.study.myshop.vo.AdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class AdminController {

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
    public String admin(String keyWords,Integer page,Model model) {
        //页数
        if (page==null||page<1){
            //默认第一页
            page = 1;
        }
        //代入搜索关键字查询有多少数据
        int count = iAdminService.getAdminCount(keyWords);
        //计算总页数:每页三条
        int initial = 3;
        // 整数,商
        int allPage = count/initial;
        if(count%initial>0){
            allPage++;
        }
        if (page>allPage){
            page=allPage;
        }
        model.addAttribute("page",page);
        model.addAttribute("allPage",allPage);

        int start =(page-1)*initial;
        List<AdminPO> adminList = iAdminService.getAdminList(keyWords,start,initial);
        model.addAttribute("adminList", adminList);
        model.addAttribute("keyWords",keyWords);
        return "admin/admin";
    }


    /***
     * 添加用户页面
     * @param model 模型
     * @return 添加页面
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
     * @return 员工管理页面
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam("id")Integer id,Model model){
        Boolean removed = iAdminService.removeAdminByAdminId(id);
        model.addAttribute("removed",removed?"删除成功!":"删除失败!");
        return "redirect:/admin/admin";
    }


    @RequestMapping("/update")
    public  String update(Integer id,Model model){
        //获取用户部分信息同时需要所有的角色里列表以及当前用户有哪些角色
        //这里就是Vo 配 Vo 的概念
        //AdminRoleVo = 自己组合的列
        //UpdateAdminVo = 包括必要的属性 和 AdminRoleVo
        //第一步: 调用业务,转入指定vo
        AdminVo adminVo = iAdminService.getAdmin(id);
        //第二步: 调用业务,传入id,返回所有角色与拥有角色的vo
        adminVo.setAdminRoleVoList(iRoleService.getAdminRole(id));
        model.addAttribute("adminVo",adminVo);
        return "admin/update";
    }


    /***
     *  修改用户信息
     * @param adminVo adminVo
     * @return 重定向员工管理页面
     */
    @RequestMapping("/alter")
    public String alter(AddAdminVo adminVo){
        if (!StringUtils.isEmpty(adminVo.getAdminPass()))
        {
            //加密:可以单独加载Security依赖,使用这个加密
            adminVo.setAdminPass(new BCryptPasswordEncoder().encode(adminVo.getAdminPass()));
        }
        iAdminService.putAdminByAdminVo(adminVo);
        return "redirect:/admin/admin";
    }
}
