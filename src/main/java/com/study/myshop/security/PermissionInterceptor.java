package com.study.myshop.security;

import com.study.myshop.po.RolePO;
import com.study.myshop.repository.RoleMapper;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 权限,拦截之后访问当前路径有哪些角色
 *
 * @author msl
 * @version 1.0
 * @create 2023-09-23 21:31
 */
@Component
public class PermissionInterceptor implements FilterInvocationSecurityMetadataSource {

    @Resource
    private RoleMapper roleMapper;

    /**
     * 获取权限
     *
     * @param o Object
     * @return Collection
     * @throws IllegalArgumentException IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取当前访问的url
        String requestURL = ((FilterInvocation) o).getRequestUrl();
        //当前路径,查询但当前角色有无权限
        List<RolePO> roleList = roleMapper.selectRoleListByOperateUrl(requestURL);
        if (!CollectionUtils.isEmpty(roleList)) {
          /*  String[] grantArray = new String[roleList.size()];
            roleList.stream().forEach(rolePO -> {
                // 获取 rolePO 中的授权信息，假设授权信息存储在某个属性中，比如 roleName
                String roleName = rolePO.getRoleName();

                // 将授权信息放入 grantArray 数组
                int index = roleList.indexOf(rolePO);
                grantArray[index] = roleName;
            });
            */
            Set<String> uniqueRoles = new LinkedHashSet<>();
            roleList.forEach(rolePO -> uniqueRoles.add(rolePO.getRoleName()));
            String[] grantArray = uniqueRoles.toArray(new String[0]);
            return SecurityConfig.createList(grantArray);
        } else {
            //查不到
            return SecurityConfig.createList("ROLE_LOGIN");
        }
    }

    /**
     * 获取集合  配置文件的参数
     *
     * @return Collection
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * 如果没有授权,提供一个默认的角色
     *
     * @param aClass 类
     * @return boolean
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
