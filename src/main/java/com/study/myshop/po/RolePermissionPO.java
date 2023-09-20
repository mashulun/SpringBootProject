package com.study.myshop.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 角色权限
 *
 * @author msl
 * @date 2023/09/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionPO implements Serializable {

    private static final long serialVersionUID = -8916024497983418194L;

    /**
     * 角色编号
     */
    private Integer roleId;
    /**
     * 角色编号
     */
    private Integer permissionId;
}
