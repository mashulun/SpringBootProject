package com.study.myshop.service;

import com.study.myshop.po.RolePO;
import com.study.myshop.vo.AdminRoleVo;

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


    /***
     *  获取用户角色信息
     * @return AdminRoleVo
     */
    List<AdminRoleVo> getAdminRole(Integer adminId);
}
