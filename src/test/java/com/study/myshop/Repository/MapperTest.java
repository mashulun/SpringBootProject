package com.study.myshop.Repository;

import com.study.myshop.ApplicationTest;
import com.study.myshop.po.AdminPO;
import com.study.myshop.repository.AdminMapper;
import com.study.myshop.repository.RoleMapper;
import com.study.myshop.vo.AdminVo;
import org.junit.Test;

import javax.annotation.Resource;


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


    @Test
    public void testAdminVo(){
        AdminVo adminVo = adminMapper.selectAdminVo(1);
        System.out.println(adminVo.toString());
    }

}
