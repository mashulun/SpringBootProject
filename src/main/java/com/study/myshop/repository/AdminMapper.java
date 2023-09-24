package com.study.myshop.repository;

import com.study.myshop.po.AdminPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author msl
 * @version 1.0
 * @create 2023-09-23 20:14
 */
@Repository
public interface AdminMapper {


    AdminPO getAdminByAdminName(@Param("adminName") String adminName);
}
