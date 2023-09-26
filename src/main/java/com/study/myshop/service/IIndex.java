package com.study.myshop.service;

import com.study.myshop.po.AdminPO;
import com.study.myshop.po.MenuPO;

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


}
