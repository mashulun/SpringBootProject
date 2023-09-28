package com.study.myshop.service.Impl;

import com.study.myshop.po.RolePO;
import com.study.myshop.repository.RoleMapper;
import com.study.myshop.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author msl
 * @version 1.0
 * @create 2023-09-27 16:24
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Resource
    private RoleMapper roleMapper;

    /***
     * 获取所有的角色列表
     * @return RoleList
     */
    @Override
    public List<RolePO> getRoleList() {
        return roleMapper.selectRoleList();
    }
}
