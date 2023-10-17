package com.study.myshop.service.impl;

import com.study.myshop.po.AdminPO;
import com.study.myshop.repository.AdminMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 当前类自定义的，按照项目格式定义的名称
 * 实现接口 Security提供的，针对用户细节服务操作
 * 添加注解 @Service，当项目运行时IOC就可以读取运行
 * 目的是参照配置文件，拦截用户请求
 *
 * @author msl
 * @version 1.0
 * @create 2023-09-23 19:31
 */

@Service
public class AdminDetailServiceImpl implements UserDetailsService {


    @Resource
    private AdminMapper adminMapper;


    /**
     * 实现方法，加载用户对象数据，按照用户名
     * String S 就是拦截请求到的用户对应 name = "userName"
     *
     * @param s 用户名称
     * @return UserDetails 用户详细信息
     * @throws UsernameNotFoundException 用户名未找到异常
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //按照截取的用户名，通过repository获取指定名称的用户对象
        AdminPO admin = adminMapper.getAdminByAdminName(s);
        if (admin == null) {
            throw new UsernameNotFoundException("账户不存在!");
        }
        return admin;
    }
}
