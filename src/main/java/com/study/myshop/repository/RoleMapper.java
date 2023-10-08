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
     * @return list
     */
    List<RolePO> selectRolePoListByAdminId(@Param("adminId") Integer adminId);

    /**
     *
     * @param operateUrl
     * @return
     */
    List<RolePO> selectRoleListByOperateUrl(@Param("operateUrl")String operateUrl);


    Boolean deleteRoleByAdminId(@Param("adminId") Integer adminId);


    List<RolePO> selectRoleList();

    /***
     * 按照用户id获取所有角色身份与单前圆滚滚拥有的角色
     * @param adminId
     * @return
     */
    List<AdminRoleVo> selectAdminRoleVo(@Param("adminId") Integer adminId);

}
