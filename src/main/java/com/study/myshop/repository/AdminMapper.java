package com.study.myshop.repository;

import com.study.myshop.po.AdminPO;
import com.study.myshop.vo.AddAdminVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author msl
 * @version 1.0
 * @create 2023-09-23 20:14
 */
@Repository
public interface AdminMapper {


    /***
     * 提供按照用户名获取用户对象的方法
     * 这个方法就是提供 AdminDetailService 使用的
     * @param adminName  用户名称
     * @return AdminPO
     */
    AdminPO getAdminByAdminName(@Param("adminName") String adminName);

    /***
     * 查询所有的员工列表
     * @return list
     */
    List<AdminPO> selectAllAdmin();

    /***
     * 按照ID删除用户:状态:1 在用 0:删除
     * @param id 用户id
     * @return boolean
     */
    Boolean deleteByAdminId(@Param("id") Integer id);


    /***
     * 添加用户
     * @param addAdminVo addAdminVo
     */
    void insertAdminByAddAdminVo(@Param("addAdminVo")AddAdminVo addAdminVo);

    /***
     *
     * @param addAdminVo addAdminVo
     */
    void insertAdminRoleAddByAddAdmin(@Param("addAdminVo")AddAdminVo addAdminVo);
}
