package com.study.myshop.service;

import com.study.myshop.po.AdminPO;
import com.study.myshop.vo.AddAdminVo;
import com.study.myshop.vo.AdminVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author msl
 * @version 1.0
 * @create 2023-09-27 10:26
 */
public interface IAdminService {


    /***
     * 获取用户列表
     * @return  list
     */
    List<AdminPO> getAdminList(String keyWords);


    @Transactional
    Boolean removeAdminByAdminId(Integer id);


    /**
     * 添加用户信息(增加权限)
     * @param adminVo  adminVo
     */
    void addAdminInfo(AddAdminVo adminVo);


    /****
     * 获取用户信息
     * @param adminId 用户id
     * @return AdminVo
     */
    AdminVo getAdmin(Integer adminId);


    /***
     * 修改用户信息和角色信息
     * @param adminVo adminVo
     */
    @Transactional
    void putAdminByAdminVo(AddAdminVo adminVo);


}
