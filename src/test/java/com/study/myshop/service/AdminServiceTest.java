package com.study.myshop.service;

import com.study.myshop.po.AdminPO;
import com.study.myshop.repository.AdminMapper;
import com.study.myshop.repository.RoleMapper;
import com.study.myshop.service.impl.AdminServiceImpl;
import com.study.myshop.vo.AddAdminVo;
import com.study.myshop.vo.AdminVo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * AdminServiceImpl 单元测试（Mockito，不依赖数据库）
 *
 * @author msl
 */
@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {

    @Mock
    private AdminMapper adminMapper;

    @Mock
    private RoleMapper roleMapper;

    @InjectMocks
    private AdminServiceImpl adminService;

    private AdminPO mockAdmin;

    @Before
    public void setUp() {
        mockAdmin = AdminPO.builder()
                .adminId(1)
                .adminName("testUser")
                .adminPass("encoded_password")
                .adminNickname("测试用户")
                .adminStatus(1)
                .build();
    }

    @Test
    public void getAdminCount_shouldReturnMapperCount() {
        when(adminMapper.selectAdminCount("test")).thenReturn(5);

        Integer count = adminService.getAdminCount("test");

        assertEquals(Integer.valueOf(5), count);
        verify(adminMapper).selectAdminCount("test");
    }

    @Test
    public void getAdminCount_withNullKeyword_shouldReturnMapperCount() {
        when(adminMapper.selectAdminCount(null)).thenReturn(10);

        Integer count = adminService.getAdminCount(null);

        assertEquals(Integer.valueOf(10), count);
    }

    @Test
    public void getAdminList_shouldClearPassword() {
        List<AdminPO> admins = Arrays.asList(mockAdmin);
        when(adminMapper.selectAllAdmin("test", 0, 3)).thenReturn(admins);

        List<AdminPO> result = adminService.getAdminList("test", 0, 3);

        assertEquals(1, result.size());
        assertNull("密码应在返回前被清除", result.get(0).getAdminPass());
    }

    @Test
    public void getAdminList_withEmptyResult_shouldReturnEmptyList() {
        when(adminMapper.selectAllAdmin("xxx", 0, 3)).thenReturn(Collections.emptyList());

        List<AdminPO> result = adminService.getAdminList("xxx", 0, 3);

        assertTrue(result.isEmpty());
    }

    @Test
    public void removeAdminByAdminId_shouldDeleteAdminAndRole() {
        when(adminMapper.deleteByAdminId(1)).thenReturn(true);
        when(roleMapper.deleteRoleByAdminId(1)).thenReturn(true);

        Boolean result = adminService.removeAdminByAdminId(1);

        assertTrue(result);
        verify(adminMapper).deleteByAdminId(1);
        verify(roleMapper).deleteRoleByAdminId(1);
    }

    @Test
    public void removeAdminByAdminId_whenAdminDeleteFails_shouldNotDeleteRole() {
        when(adminMapper.deleteByAdminId(1)).thenReturn(false);

        Boolean result = adminService.removeAdminByAdminId(1);

        assertFalse(result);
        verify(roleMapper, never()).deleteRoleByAdminId(anyInt());
    }

    @Test
    public void addAdminInfo_withRoles_shouldInsertAdminAndRoles() {
        AddAdminVo vo = new AddAdminVo(null, "newUser", "pass123", "新用户", new Integer[]{1, 2});
        doNothing().when(adminMapper).insertAdminByAddAdminVo(any());
        doNothing().when(adminMapper).insertAdminRoleAddByAddAdmin(any());

        adminService.addAdminInfo(vo);

        verify(adminMapper).insertAdminByAddAdminVo(vo);
        verify(adminMapper).insertAdminRoleAddByAddAdmin(vo);
    }

    @Test
    public void addAdminInfo_withoutRoles_shouldOnlyInsertAdmin() {
        AddAdminVo vo = new AddAdminVo(null, "newUser", "pass123", "新用户", null);
        doNothing().when(adminMapper).insertAdminByAddAdminVo(any());

        adminService.addAdminInfo(vo);

        verify(adminMapper).insertAdminByAddAdminVo(vo);
        verify(adminMapper, never()).insertAdminRoleAddByAddAdmin(any());
    }

    @Test
    public void getAdmin_shouldReturnAdminVo() {
        AdminVo expected = new AdminVo();
        when(adminMapper.selectAdminVo(1)).thenReturn(expected);

        AdminVo result = adminService.getAdmin(1);

        assertSame(expected, result);
        verify(adminMapper).selectAdminVo(1);
    }

    @Test
    public void putAdminByAdminVo_withRoles_shouldUpdateAndReassignRoles() {
        AddAdminVo vo = new AddAdminVo(1, "user", "pass", "昵称", new Integer[]{3});
        when(adminMapper.updateAdminByAddAdminVo(vo)).thenReturn(true);

        adminService.putAdminByAdminVo(vo);

        verify(adminMapper).updateAdminByAddAdminVo(vo);
        verify(adminMapper).deleteAdminRoleByAdminId(1);
        verify(adminMapper).insertAdminRoleAddByAddAdmin(vo);
    }

    @Test
    public void getAdminPageData_shouldCalculatePaginationCorrectly() {
        when(adminMapper.selectAdminCount("test")).thenReturn(10);
        List<AdminPO> page1 = Arrays.asList(mockAdmin);
        when(adminMapper.selectAllAdmin("test", 0, 3)).thenReturn(page1);

        Map<String, Object> result = adminService.getAdminPageData("test", 1);

        assertEquals(1, result.get("page"));
        assertEquals(4, result.get("allPage"));  // 10 / 3 = 3 余 1 → 4 页
        assertEquals("test", result.get("keyWords"));
        assertNotNull(result.get("adminList"));
    }

    @Test
    public void getAdminPageData_withZeroCount_shouldReturnPageOne() {
        when(adminMapper.selectAdminCount(null)).thenReturn(0);
        when(adminMapper.selectAllAdmin(null, 0, 3)).thenReturn(Collections.emptyList());

        Map<String, Object> result = adminService.getAdminPageData(null, 1);

        assertEquals(1, result.get("page"));
        assertEquals(1, result.get("allPage"));  // 0 条数据 → 1 页
    }

    @Test
    public void getAdminPageData_withInvalidPage_shouldResetToOne() {
        when(adminMapper.selectAdminCount("test")).thenReturn(3);
        when(adminMapper.selectAllAdmin("test", 0, 3)).thenReturn(Arrays.asList(mockAdmin));

        Map<String, Object> result = adminService.getAdminPageData("test", -5);

        assertEquals(1, result.get("page"));
    }

    @Test
    public void getAdminPageData_withPageExceedingTotal_shouldCapToLastPage() {
        when(adminMapper.selectAdminCount("test")).thenReturn(6);
        when(adminMapper.selectAllAdmin("test", 3, 3)).thenReturn(Arrays.asList(mockAdmin));

        Map<String, Object> result = adminService.getAdminPageData("test", 100);

        assertEquals(2, result.get("page"));  // 6 / 3 = 2 页，超过就取最后一页
    }
}
