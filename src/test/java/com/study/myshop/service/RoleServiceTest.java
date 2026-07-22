package com.study.myshop.service;

import com.study.myshop.po.RolePO;
import com.study.myshop.repository.RoleMapper;
import com.study.myshop.service.impl.RoleServiceImpl;
import com.study.myshop.vo.AdminRoleVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * RoleServiceImpl 单元测试（Mockito，不依赖数据库）
 *
 * @author msl
 */
@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

    @Mock
    private RoleMapper roleMapper;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Test
    public void getRoleList_shouldReturnAllRoles() {
        RolePO role1 = new RolePO();
        RolePO role2 = new RolePO();
        when(roleMapper.selectRoleList()).thenReturn(Arrays.asList(role1, role2));

        List<RolePO> result = roleService.getRoleList();

        assertEquals(2, result.size());
        verify(roleMapper).selectRoleList();
    }

    @Test
    public void getRoleList_whenEmpty_shouldReturnEmptyList() {
        when(roleMapper.selectRoleList()).thenReturn(Collections.emptyList());

        List<RolePO> result = roleService.getRoleList();

        assertTrue(result.isEmpty());
    }

    @Test
    public void getAdminRole_shouldReturnRolesForAdmin() {
        AdminRoleVo vo = new AdminRoleVo();
        when(roleMapper.selectAdminRoleVo(1)).thenReturn(Arrays.asList(vo));

        List<AdminRoleVo> result = roleService.getAdminRole(1);

        assertEquals(1, result.size());
        verify(roleMapper).selectAdminRoleVo(1);
    }

    @Test
    public void getAdminRole_whenAdminHasNoRoles_shouldReturnEmpty() {
        when(roleMapper.selectAdminRoleVo(999)).thenReturn(Collections.emptyList());

        List<AdminRoleVo> result = roleService.getAdminRole(999);

        assertTrue(result.isEmpty());
    }
}
