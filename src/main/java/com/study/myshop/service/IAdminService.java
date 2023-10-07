package com.study.myshop.service;

import com.study.myshop.po.AdminPO;
import com.study.myshop.vo.AddAdminVo;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.List;

/**
 * @author msl
 * @version 1.0
 * @create 2023-09-27 10:26
 */
public interface IAdminService {


    /***
     * 获取用户列表
     * @return  list
     */
    List<AdminPO> getAdminList();


    @Transactional
    Boolean removeAdminByAdminId(Integer id);


    /**
     * 添加用户信息(增加权限)
     * @param adminVo  adminVo
     * @return  boolean
     */
    void addAdminInfo(AddAdminVo adminVo);


}
