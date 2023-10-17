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
     * 获取员工的数据数量- 支持keyWords
     * @param keyWords 检索关键字
     * @return 数量
     */
    Integer getAdminCount(String keyWords);

    /**
     * 获取员工列表 -支持keyWords
     * @param keyWords 检索关键字
     * @param page 页数
     * @param rows 行数
     * @return 员工列表
     */
    List<AdminPO> getAdminList(String keyWords,Integer page,Integer rows);


    /***
     * 删除用户
     * @param id  用户id
     * @return boolean
     */
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
    void putAdminByAdminVo(AddAdminVo adminVo);


}
