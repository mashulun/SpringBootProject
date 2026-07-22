package com.study.myshop.service;

import com.study.myshop.po.AdminPO;
import com.study.myshop.po.MenuPO;
import com.study.myshop.vo.AdminInfoVo;
import com.study.myshop.vo.MenuVo;

import java.util.List;

/**
 * @author msl
 * @version 1.0
 * @create 2023-09-26 11:40
 */
public interface IIndex {


    /***
     * 按照用户名获取菜单列表
     * @param userName 用户名
     * @return list
     */
    List<MenuPO> getMenuListByAdminName(String userName);

    /***
     *  按照用户名获取用户信息
     * @param userName 用户名
     * @return 用户信息
     */
    AdminPO getAdminByAdminName(String userName);


    /***
     * 按照用户名获取菜单树(一级菜单含子菜单)
     * @param userName 用户名
     * @return 菜单树列表
     */
    List<MenuVo> getMenuTreeByAdminName(String userName);

    /***
     * 按照用户名获取管理员信息VO
     * @param userName 用户名
     * @return 管理员信息VO
     */
    AdminInfoVo getAdminInfoByAdminName(String userName);

}
