package com.study.myshop.service;

import com.study.myshop.po.RolePO;

import java.util.List;

/**
 * @author msl
 * @version 1.0
 * @create 2023-09-27 16:24
 */
public interface IRoleService {
    /***
     * 获取所有的角色列表
     * @return RoleList
     */
    List<RolePO> getRoleList();
}
