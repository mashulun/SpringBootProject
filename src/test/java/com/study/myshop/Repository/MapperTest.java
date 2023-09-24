package com.study.myshop.Repository;

import com.study.myshop.ApplicationTest;
import com.study.myshop.po.AdminPO;
import com.study.myshop.po.RolePO;
import com.study.myshop.repository.AdminMapper;
import com.study.myshop.repository.RoleMapper;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author msl
 * @version 1.0
 * @create 2023-09-23 21:01
 */
public class MapperTest extends ApplicationTest {

    @Resource
   private RoleMapper roleMapper;

    @Resource
    private AdminMapper adminMapper;




    @Test
    public void test(){
        AdminPO adminByAdminName = adminMapper.getAdminByAdminName("admin");
        System.out.println(adminByAdminName.getRoleList());




    }

}
