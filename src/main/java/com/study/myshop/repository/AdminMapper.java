package com.study.myshop.repository;

import com.study.myshop.po.AdminPO;
import com.study.myshop.vo.AddAdminVo;
import com.study.myshop.vo.AdminVo;

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
      * @param keyWords 检索关键字
     * @param page 页数
     * @param rows 行数
     * @return 员工列表
     */
    List<AdminPO> selectAllAdmin(@Param("keyWords") String keyWords,@Param("page")Integer page,@Param("rows") Integer rows);

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
     *  添加用户角色
     * @param addAdminVo addAdminVo
     */
    void insertAdminRoleAddByAddAdmin(@Param("addAdminVo")AddAdminVo addAdminVo);

    /***
     * 查询用火信息
     * @param adminId 用户id
     * @return AdminVo
     */
    AdminVo selectAdminVo(@Param("adminId")Integer adminId);

    /***
     *  跟新用户信息
     * @param addAdminVo addAdminVo
     * @return boolean
     */
    boolean updateAdminByAddAdminVo(@Param("addAdminVo")AddAdminVo addAdminVo);


    /***
     *  删除用户角色权限
     * @param adminId 用户id
     */
    void deleteAdminRoleByAdminId(@Param("adminId") Integer adminId);

    /***
     * 查询员工信息的总数具行数
     * @param keyWords 搜索关键字
     * @return 行数
     */
    Integer selectAdminCount(@Param("keyWords") String keyWords);

}
