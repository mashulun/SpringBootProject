package com.study.myshop.repository;

import com.study.myshop.po.MenuPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author msl
 * @version 1.0
 * @create 2023-09-26 11:10
 */
@Repository
public interface MenuMapper {

    /***
     * 按照用户查询出当前用户拥有权限的所有菜单列表
     * @param userName  用户名称
     * @return List<MenuPO> 菜单列表
     */
    List<MenuPO> selectMenuListByAdminName(@Param("userName") String userName);
}
