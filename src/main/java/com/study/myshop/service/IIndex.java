package com.study.myshop.service;

import com.study.myshop.po.MenuPO;

import java.util.List;

/**
 * @author msl
 * @version 1.0
 * @create 2023-09-26 11:40
 */
public interface IIndex {


    /***
     *
     * @param userName 用户名
     * @return list
     */
    List<MenuPO> getMenuListByAdminName(String userName);


}
