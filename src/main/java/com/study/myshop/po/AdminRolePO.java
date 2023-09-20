package com.study.myshop.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 员工角色关联
 *
 * @author msl
 * @date 2023/09/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminRolePO implements Serializable {

    private static final long serialVersionUID = 2124181025158309422L;

    /**
     * 用户编号
     */
    private Integer adminId;

    /**
     * 角色编号
     */
    private Integer roleId;
}
