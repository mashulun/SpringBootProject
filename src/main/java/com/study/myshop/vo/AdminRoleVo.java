package com.study.myshop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 所有身份列表以及当前员工拥有的
 * @author msl
 * @version 1.0
 * @create 2023-10-07 17:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminRoleVo implements Serializable {
    private static final long serialVersionUID = -2405638015303020356L;


    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 选中的角色ID
     */
    private Integer has;
}
