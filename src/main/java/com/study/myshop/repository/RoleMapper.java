package com.study.myshop.repository;

import com.study.myshop.po.RolePO;
import com.study.myshop.vo.AdminRoleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author msl
 * @version 1.0
 * @create 2023-09-23 20:35
 */
@Repository
public interface RoleMapper {

    /***
     * 按照员工id查询角色列表
     *  提供给Admin持久层使用的
     * @param adminId 用户id
     * @return 角色
     */
    List<RolePO> selectRolePoListByAdminId(@Param("adminId") Integer adminId);


    /***
     * 选择按操作 URL 列出的角色列表
     * @param operateUrl 路径
     * @return 角色
     */
    List<RolePO> selectRoleListByOperateUrl(@Param("operateUrl")String operateUrl);


    /***
     * 按照用户Id删除角色
     * @param adminId 用户id
     * @return boolean
     */
    Boolean deleteRoleByAdminId(@Param("adminId") Integer adminId);


    /***
     *  查询所用的用户角色
     * @return list
     */
    List<RolePO> selectRoleList();

    /***
     * 按照用户id获取所有角色身份与单前圆滚滚拥有的角色
     * @param adminId 用户id
     * @return  所有身份列表以及当前员工拥有的角色
     */
    List<AdminRoleVo> selectAdminRoleVo(@Param("adminId") Integer adminId);

}
