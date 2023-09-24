package com.study.myshop.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * 员工表admin 的实体对象必须实现接口：UserDetails
 * Security是一套标准操作，但是每一个项目，员工表都不一样。
 * 为了统一表格和对象的关系，提供接口 UserDetails，
 * 同时提供了很多操作方法。

 * @author msl
 * @date 2023/09/19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AdminPO implements Serializable, UserDetails {

    private static final long serialVersionUID = -3624702394501215159L;
    /**
     *用户编号
     */
    private Integer adminId;


    /**
     *账户名称
     */
    private String adminName;


    /**
     *账户密码
     */
    private String adminPass;

    /**
     *用户昵称
     */
    private String adminNickname;

    /**
     * 用户状态：1启用
     */
    private Integer adminStatus;



    private List<RolePO> roleList = new ArrayList<>();


    /**
     * 获取当前用户对应的角色role列表
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //当前用户的角色列表
        return this.getRoleList();
    }

    /**
     */
    @Override
    public String getPassword() {
        return this.getAdminPass();
    }

    /**
     */
    @Override
    public String getUsername() {
        return this.getAdminName();
    }

    /**
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
