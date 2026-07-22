package com.study.myshop.service.impl;

import com.study.myshop.po.AdminPO;
import com.study.myshop.po.MenuPO;
import com.study.myshop.po.RolePO;
import com.study.myshop.repository.AdminMapper;
import com.study.myshop.repository.MenuMapper;
import com.study.myshop.service.IIndex;
import com.study.myshop.vo.AdminInfoVo;
import com.study.myshop.vo.MenuVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author msl
 * @version 1.0
 * @create 2023-09-26 11:58
 */
@Service
public class IndexServiceImpl implements IIndex {


    @Resource
    private MenuMapper menuMapper;

    @Resource
    private AdminMapper adminMapper;

    /***
     *
     * @param userName 用户名
     * @return list
     */
    @Override
    public List<MenuPO> getMenuListByAdminName(String userName) {
        return menuMapper.selectMenuListByAdminName(userName);
    }

    /***
     *  按照用户名获取用户信息
     * @param userName 用户名
     * @return 用户信息
     */
    @Override
    public AdminPO getAdminByAdminName(String userName) {
        return adminMapper.getAdminByAdminName(userName);
    }

    /***
     * 按照用户名获取菜单树(一级菜单含子菜单)
     * 将菜单树构建逻辑从Controller移至Service层
     * @param userName 用户名
     * @return 菜单树列表
     */
    @Override
    public List<MenuVo> getMenuTreeByAdminName(String userName) {
        List<MenuPO> menuList = menuMapper.selectMenuListByAdminName(userName);
        // 使用Map存储菜单ID和对应的菜单对象
        Map<Integer, MenuVo> menuMap = new HashMap<>(menuList.size());
        // 第一次遍历，创建一级菜单对象，并存入map中
        for (MenuPO menu : menuList) {
            if (menu.getPid() == null) {
                MenuVo menuVo = new MenuVo();
                menuVo.setMenu(menu);
                menuMap.put(menu.getMenuId(), menuVo);
            }
        }
        // 第二次遍历，将子菜单连接到对应的父菜单中
        for (MenuPO menu : menuList) {
            Integer parentId = menu.getPid();
            if (parentId != null) {
                MenuVo parentMenu = menuMap.get(parentId);
                if (parentMenu != null) {
                    parentMenu.getMenus().add(menu);
                }
            }
        }
        // 提取一级菜单作为最终的菜单列表
        return new ArrayList<>(menuMap.values());
    }

    /***
     * 按照用户名获取管理员信息VO
     * 将管理员信息组装逻辑从Controller移至Service层
     * @param userName 用户名
     * @return 管理员信息VO
     */
    @Override
    public AdminInfoVo getAdminInfoByAdminName(String userName) {
        AdminPO adminPO = adminMapper.getAdminByAdminName(userName);
        AdminInfoVo adminInfoVo = new AdminInfoVo();
        adminInfoVo.setNickName(adminPO.getAdminNickname());
        for (RolePO role : adminPO.getRoleList()) {
            adminInfoVo.getRoles().add(role.getRoleName());
        }
        return adminInfoVo;
    }
}
