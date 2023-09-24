package com.study.myshop.repository;

import com.study.myshop.po.RolePO;
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


    List<RolePO> selectRoleListByOperateUrl(@Param("operateUrl")String operateUrl);


}
