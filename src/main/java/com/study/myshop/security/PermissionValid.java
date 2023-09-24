package com.study.myshop.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 验证,当前用户是否用于访问权限
 *
 * @author msl
 * @version 1.0
 * @create 2023-09-23 21:32
 */
@Component
public class PermissionValid implements AccessDecisionManager {
    /**
     * 认证成功 or 认证失败
     *
     * @param authentication 当前登录用户
     * @param o              请求对象
     * @param collection     当前URL对象的角色列表
     * @throws AccessDeniedException               AccessDeniedException
     * @throws InsufficientAuthenticationException InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        if (authentication == null) {
            throw new AccessDeniedException("权限认证失败!");
        }
        //角色列表
        for (ConfigAttribute attribute : collection) {
            String grantName = attribute.getAttribute();
            //判断 是否有默认授权(公共资源)
            if ("ROLE_LOGIN".equals(grantName)) {
                //判断用户是否登录
                if (authentication instanceof AnonymousAuthenticationToken){
                    throw  new BadCredentialsException("请先登录!");
                }else {
                    //登录了,可以访问公共资源
                    return;
                }
            }
        }
        //如果当前用户没有该url访问权限
        throw new AccessDeniedException("权限认证失败!");
    }

    /**
     * @param configAttribute
     * @return
     */
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    /**
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
